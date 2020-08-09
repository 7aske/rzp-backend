package com.example.backend.entity.dto.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientMessage {
	private String message;

	private ClientMessage() {
	}

	public ClientMessage(String message) {
		this.message = message;
	}
}
