package com.example.myproject.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("dob")
    private String dob;

    private String address;

    @JsonProperty("gender")
    private String gender;
}
