package rs.digitize.backend.search;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.search.specification.GenericSpecification;
import rs.digitize.backend.search.specification.GenericSpecificationBuilder;

import java.util.Deque;

@NoArgsConstructor
public class GenericSpecificationConverter implements Converter<String, Specification<?>> {

    @Override
    public Specification<?> convert(String str) {
        if (str.isEmpty())
            return null;

        try {
            Deque<?> deque = new CriteriaParser().parse(str);
            GenericSpecificationBuilder<User> specificationBuilder = new GenericSpecificationBuilder<>();
            return specificationBuilder.build(deque, GenericSpecification::new);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
