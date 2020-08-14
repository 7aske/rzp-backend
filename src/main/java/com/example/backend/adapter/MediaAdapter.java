package com.example.backend.adapter;

import com.example.backend.entity.Media;
import com.example.backend.entity.dto.MediaDTO;

import java.util.HashMap;
import java.util.Map;

public class MediaAdapter {
	public static MediaDTO adapt(Media media) {
		MediaDTO mediaDTO = new MediaDTO();
		Map<String, String> data = new HashMap<>();
		data.put("filePath", media.getMediaFilepath());
		mediaDTO.setData(data);
		return mediaDTO;
	}
}
