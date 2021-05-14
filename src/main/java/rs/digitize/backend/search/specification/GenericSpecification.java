package rs.digitize.backend.search.specification;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.search.SearchCriteria;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static rs.digitize.backend.search.SearchOperation.IS_NULL;
import static rs.digitize.backend.search.SearchOperation.NOT_NULL;


@AllArgsConstructor
public class GenericSpecification<T extends Serializable> implements Specification<T>, Serializable {
    private final transient SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        String[] params = searchCriteria.getKey().split("\\.");

        From<T, ?> joinOrRoot = root;
        String base = params[0];

        for (int i = 0; i < params.length - 1; i++) {
            joinOrRoot = joinOrRoot.join(base);
            base = params[i + 1];
        }

        Object value = searchCriteria.getValue();
        if (value instanceof String && value.toString().equals("null")) {
            searchCriteria.setOperation(IS_NULL);
        }

        if (value instanceof String && value.toString().equals("notnull")) {
            searchCriteria.setOperation(NOT_NULL);
        }

        // ENUM
        if (joinOrRoot.get(base).getJavaType().isEnum()) {
            value = Enum.valueOf(joinOrRoot.<Enum>get(base).getJavaType(), ((String) value));
            return getPredicate(builder, joinOrRoot, base, value);
        }

        // LOCALDATETIME
        if (joinOrRoot.get(base).getJavaType().equals(LocalDateTime.class)) {
            LocalDateTime localDateTime = LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(Long.parseLong((String) value)), TimeZone.getDefault().toZoneId());
            return getLocalDateTimePredicate(builder, joinOrRoot, base, localDateTime);
        }

        // LOCALDATE
        if (joinOrRoot.get(base).getJavaType().equals(LocalDate.class)) {
            LocalDate localDate = LocalDate
                    .ofInstant(Instant.ofEpochMilli(Long.parseLong((String) value)), TimeZone.getDefault().toZoneId());
            return getLocalDatePredicate(builder, joinOrRoot, base, localDate);
        }

        // DEFAULT
        return getPredicate(builder, joinOrRoot, base, value);
    }

    private Predicate getLocalDateTimePredicate(CriteriaBuilder builder, From<T, ?> root, String attr, LocalDateTime value) {
        switch (searchCriteria.getOperation()) {
            case GREATER_THAN:
                return builder.greaterThan(root.get(attr), value);
            case LESS_THAN:
                return builder.lessThan(root.get(attr), value);
            default:
                return getPredicate(builder, root, attr, value);
        }
    }

    private Predicate getLocalDatePredicate(CriteriaBuilder builder, From<T, ?> root, String attr, LocalDate value) {
        switch (searchCriteria.getOperation()) {
            case GREATER_THAN:
                return builder.greaterThan(root.get(attr), value);
            case LESS_THAN:
                return builder.lessThan(root.get(attr), value);
            default:
                return getPredicate(builder, root, attr, value);
        }
    }

    private Predicate getPredicate(CriteriaBuilder builder, From<T, ?> root, String attr, Object value) {
        switch (searchCriteria.getOperation()) {
            case GREATER_THAN:
                return builder.greaterThan(root.get(attr), value.toString());
            case LESS_THAN:
                return builder.lessThan(root.get(attr), value.toString());
            case NEGATION:
                return builder.notEqual(root.get(attr), value);
            case EQUALITY:
                return builder.equal(root.get(attr), value);
            case CONTAINS:
            case LIKE:
                return builder.like(builder.lower(root.get(attr)), "%" + value + "%");
            case STARTS_WITH:
                return builder.like(builder.lower(root.get(attr)), "%" + value);
            case ENDS_WITH:
                return builder.like(builder.lower(root.get(attr)), value + "%");
            case IS_NULL:
                return builder.isNull(root.get(attr));
            case NOT_NULL:
                return builder.isNotNull(root.get(attr));
            default:
                return null;
        }
    }
}
