package rs.digitize.backend.bean.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortConverter implements Converter<String, Sort> {
    @Override
    public Sort convert(String queryString) {
        if (queryString.isEmpty())
            return Sort.unsorted();

        String[] attrs = queryString.split(",");
        List<Sort.Order> orders = Arrays.stream(attrs)
                .map(attr -> {
                    if (attr.startsWith("^")) {
                        return new Sort.Order(Sort.Direction.ASC, attr.substring(1));
                    } else {
                        return new Sort.Order(Sort.Direction.DESC, attr);
                    }
                })
                .collect(Collectors.toList());

        return Sort.by(orders);
    }
}
