package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.LoginDTO;
import com.example.backend.entity.dto.RegisterDTO;
import com.example.backend.service.impl.AuthServiceImpl;

public interface AuthService {
	String login(LoginDTO loginDTO) throws AuthServiceImpl.LoginException;
	User register(RegisterDTO registerDTO);
}
