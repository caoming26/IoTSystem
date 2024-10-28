package com.example.myproject.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {

    private String username;
    private String password;
}