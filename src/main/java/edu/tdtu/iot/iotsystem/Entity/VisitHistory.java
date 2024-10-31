package edu.tdtu.iot.iotsystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

@Data
@Builder
@Document("visit_history")
@AllArgsConstructor
@NoArgsConstructor
public class VisitHistory {
<<<<<<< HEAD
=======

>>>>>>> 678e4d5aeef9ef0e192d04bbda51962549f0b0a6
    @Id
    private String id;
    private RFID rfid;
    private Date timeAt;

}
