package com.example.backend.adapter;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserCommentDTO;
import com.example.backend.entity.dto.UserDTO;

import java.util.stream.Collectors;

public class UserAdapter {
	public static UserDTO adapt(User user) {
		if (user == null) {
			return null;
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setUserUsername(user.getUserUsername());
		userDTO.setUserEmail(user.getUserEmail());
		userDTO.setUserFirstName(user.getUserFirstName());
		userDTO.setUserLastName(user.getUserLastName());
		userDTO.setUserAddress(user.getUserAddress());
		userDTO.setUserAbout(user.getUserAbout());
		userDTO.setUserDisplayName(user.getUserDisplayName());
		userDTO.setUserDateCreated(user.getUserDateCreated());
		userDTO.setUserRoles(user.getUserRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));
		return userDTO;
	}

}
