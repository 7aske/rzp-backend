package com.example.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T readValue(HttpServletRequest request, Class<T> clazz) {
		try {
			return clazz.cast(objectMapper.readValue(request.getInputStream(), clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> void writeValue(HttpServletResponse response, T object) {
		try {
			response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			objectMapper.writeValue(response.getWriter(), object);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
