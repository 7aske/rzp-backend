package com.example.backend.adapter;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserCommentDTO;
import com.example.backend.entity.dto.UserDTO;

public class UserDTOAdapter {
	public static UserCommentDTO adapt(User user) {
		UserCommentDTO userDTO = new UserCommentDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setUserUsername(user.getUserUsername());
		userDTO.setUserEmail(user.getUserEmail());
		userDTO.setUserFirstName(user.getUserFirstName());
		userDTO.setUserLastName(user.getUserLastName());
		userDTO.setUserDisplayName(user.getUserDisplayName());
		return userDTO;
	}
}
