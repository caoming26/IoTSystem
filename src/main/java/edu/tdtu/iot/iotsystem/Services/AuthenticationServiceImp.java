package com.example.myproject.Services;

import com.example.myproject.Config.CustomUserDetails;
import com.example.myproject.Config.JwtService;
import com.example.myproject.DTO.AuthenticationDTO;
import com.example.myproject.DTO.LoginDTO;
import com.example.myproject.DTO.UserDTO;
import com.example.myproject.Exceptions.DuplicateException;
import com.example.myproject.Model.User;
import com.example.myproject.Reporsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public User register(UserDTO userDTO) {

        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());
        if (userOptional.isPresent()){
            throw new DuplicateException("Username is exist!");
        }

        User user = User.builder()
                .fullname(userDTO.getFullname())
                .username(userDTO.getUsername())
                .phone(userDTO.getPhone())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .dob(userDTO.getDob())
                .status(true)
                .gender(userDTO.getGender())
                .role("USER")
                .build();
        userRepository.save(user);

        return user;
    }

    @Override
    public AuthenticationDTO login(LoginDTO loginDTO) {

//      Kiểm tra đăng nhập, nếu sai sẽ ném ra AuthenticationException
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow();

//        Tạo token

        var jwt = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationDTO.builder()
                .token(jwt)
                .authentication(authentication)
                .build();
    }

    @Override
    public boolean existsByUsername(String username) {
        return !userRepository.existsByUsername(username);
    }
}


