package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("rfid")
public class RFID {

    @Id
    private String id;
    private String rfid;
    private String name;
}
