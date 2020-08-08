package com.example.backend.entity.dto.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientError {
	private String error;

	private ClientError() {
	}

	public ClientError(String error) {
		this.error = error;
	}
}
