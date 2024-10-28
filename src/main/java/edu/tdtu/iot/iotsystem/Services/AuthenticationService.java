package edu.tdtu.iot.iotsystem.Services;


import edu.tdtu.iot.iotsystem.DTO.AuthenticationDTO;
import edu.tdtu.iot.iotsystem.DTO.LoginDTO;
import edu.tdtu.iot.iotsystem.DTO.UserDTO;
import edu.tdtu.iot.iotsystem.Entity.User;

public interface AuthenticationService {
    User register(UserDTO userDTO);
    AuthenticationDTO login(LoginDTO loginDTO);
//    boolean existsByUsername(String username);

}
