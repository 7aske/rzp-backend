package com.example.backend.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @NoArgsConstructor
public class TagStatsDTO {
	Map<String, Number> stats;
}
