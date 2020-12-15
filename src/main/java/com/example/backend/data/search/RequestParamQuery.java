package com.example.backend.data.search;

import lombok.Data;

import java.util.List;

@Data
public class RequestParamQuery {
	private List<SearchCriteria> criteria;
	private QueryOp op;
}
