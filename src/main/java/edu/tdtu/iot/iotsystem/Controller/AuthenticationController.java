package edu.tdtu.iot.iotsystem.Controller;



import edu.tdtu.iot.iotsystem.DTO.AuthenticationDTO;
import edu.tdtu.iot.iotsystem.DTO.LoginDTO;
import edu.tdtu.iot.iotsystem.DTO.UserDTO;
import edu.tdtu.iot.iotsystem.Entity.User;
import edu.tdtu.iot.iotsystem.JWT.JwtService;
import edu.tdtu.iot.iotsystem.Services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static edu.tdtu.iot.iotsystem.Constant.Constant.MAX_AGE_COOKIE;


@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

//    @PostMapping("/check-username")
//    @ResponseBody
//    public Map<String, Boolean> checkUsername(@RequestBody Map<String, String> body) {
//        String username = body.get("username");
//        boolean isAvailable = authenticationService.existsByUsername(username);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("available", isAvailable);
//        return response;


    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody UserDTO request, HttpServletResponse response) {
        User user = authenticationService.register(request);

        //Generate token
        String token = jwtService.generateToken(user);


        // Add token to cookie to login
        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setMaxAge(MAX_AGE_COOKIE);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }
    @PostMapping("/api/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO request, HttpServletResponse response) {
        //Generate token
        AuthenticationDTO authenticationDTO = authenticationService.login(request);
        String token = authenticationDTO.getToken();
        User user = authenticationDTO.getUser();

        // Add token to cookie to login
        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setMaxAge(MAX_AGE_COOKIE);
        cookie.setPath("/");
        response.addCookie(cookie);

//        System.out.println("Tooi la: "+modelMapper.map(((CustomUserDetails) authentication.getPrincipal()).getUser(), UserDTO.class).getFullname());
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }
}

