package rs.digitize.backend.util;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.domain.RecordStatus;
import rs.digitize.backend.search.GenericSpecificationConverter;
import rs.digitize.backend.search.SearchOperation;

import static lombok.AccessLevel.PRIVATE;
import static rs.digitize.backend.search.SearchOperation.EQUALITY;

@NoArgsConstructor(access = PRIVATE)
public class SpecificationUtil {
	public static  <T> Specification<T> combineSpecificationFor(Specification<T> specification, RecordStatus recordStatus) {
		return combineSpecificationFor(specification, "recordStatus", EQUALITY, recordStatus.name());
	}

	public static  <T> Specification<T> combineSpecificationFor(Specification<T> specification, String prop, Object val) {
		return combineSpecificationFor(specification, prop, EQUALITY, val);
	}

	public static  <T> Specification<T> combineSpecificationFor(Specification<T> specification, String prop, SearchOperation searchOperation) {
		return combineSpecificationFor(specification, prop, searchOperation, "");
	}

	public static  <T> Specification<T> combineSpecificationFor(Specification<T> specification, String prop, SearchOperation searchOperation, Object val) {
		Specification<T> spec = (Specification<T>) new GenericSpecificationConverter().convert(String.format("%s%s%s", prop, searchOperation.toString(), val.toString()));
		if (specification == null)
			return spec;

		return specification.and(spec);
	}
}
