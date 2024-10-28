package com.example.myproject.Services;

import com.example.myproject.DTO.AuthenticationDTO;
import com.example.myproject.DTO.LoginDTO;
import com.example.myproject.DTO.UserDTO;
import com.example.myproject.Model.User;

public interface AuthenticationService {
    User register(UserDTO userDTO);
    AuthenticationDTO login(LoginDTO loginDTO);
    boolean existsByUsername(String username);

}
