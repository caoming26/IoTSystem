package edu.tdtu.iot.iotsystem.DTO;

import edu.tdtu.iot.iotsystem.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDTO {
    String token;
    Authentication authentication;
    User user;
}
