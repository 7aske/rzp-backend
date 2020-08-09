package com.example.backend.adapter;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserDTO;

public class UserAdapter {
	public static UserDTO adapt(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setUserUsername(user.getUserUsername());
		userDTO.setUserEmail(user.getUserEmail());
		userDTO.setUserFirstName(user.getUserFirstName());
		userDTO.setUserLastName(user.getUserLastName());
		userDTO.setUserAddress(user.getUserAddress());
		userDTO.setUserAbout(user.getUserAddress());
		userDTO.setUserDisplayName(user.getUserDisplayName());
		userDTO.setUserDateCreated(user.getUserDateCreated());
		userDTO.setUserRoles(user.getUserRoles());
		return userDTO;
	}
}
