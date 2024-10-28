package edu.tdtu.iot.iotsystem.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("roles")
public class Role {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    @Id
    private String id;
    private String name;
}