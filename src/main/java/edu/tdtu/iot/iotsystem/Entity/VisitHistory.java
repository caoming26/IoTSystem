package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document("visit_history")
public class VisitHistory {
    private String id;
    private RFID rfid;
    private User user;
    private Date visitAt;
}
