package rs.digitize.backend.util;

import org.springframework.data.domain.Sort;

public class Sorts {
	public static final Sort CREATED_DATE_SORT = Sort.by("createdDate").descending();
}
