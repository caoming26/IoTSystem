package edu.tdtu.iot.iotsystem.Services;


import edu.tdtu.iot.iotsystem.DTO.AuthenticationDTO;
import edu.tdtu.iot.iotsystem.DTO.LoginDTO;
import edu.tdtu.iot.iotsystem.DTO.UserDTO;
import edu.tdtu.iot.iotsystem.Entity.User;
import edu.tdtu.iot.iotsystem.Exceptions.BadRequestException;
import edu.tdtu.iot.iotsystem.Exceptions.DuplicateException;
import edu.tdtu.iot.iotsystem.JWT.JwtService;
import edu.tdtu.iot.iotsystem.Repository.RoleRepository;
import edu.tdtu.iot.iotsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static edu.tdtu.iot.iotsystem.Constant.Constant.USER_ROLE;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public User register(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new DuplicateException("Email have been used!");
        }
        if (userRepository.findByPhone(userDTO.getPhone()).isPresent()){
            throw new DuplicateException("Phone have been used!");
        }


        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(roleRepository.getRoleByName(USER_ROLE))
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

        User user = userRepository.findByEmail(loginDTO.getUsername())
                .or(()->userRepository.findByPhone(loginDTO.getUsername()))
                .orElseThrow(() -> new BadRequestException("Username is no exist!"));

//        Tạo token

        var jwt = jwtService.generateToken(user);
        return AuthenticationDTO.builder()
                .token(jwt)
                .authentication(authentication)
                .user(user)
                .build();
    }

//    @Override
//    public boolean existsByUsername(String username) {
//        return !userRepository.existsByUsername(username);
//    }
}

