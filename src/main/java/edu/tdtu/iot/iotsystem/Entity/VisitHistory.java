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

    @Id
    private String id;
    private RFID rfid;
    private Date timeAt;

}
