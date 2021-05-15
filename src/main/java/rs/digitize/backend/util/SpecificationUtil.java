package rs.digitize.backend.util;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.domain.RecordStatus;
import rs.digitize.backend.search.GenericSpecificationConverter;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class SpecificationUtil {
	public static  <T> Specification<T> combineSpecificationFor(Specification<T> specification, RecordStatus recordStatus) {
		Specification<T> spec = (Specification<T>) new GenericSpecificationConverter().convert(String.format("recordStatus:%s", recordStatus.name()));
		if (specification == null)
			return spec;

		return specification.and(spec);
	}
}
