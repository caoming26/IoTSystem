package edu.tdtu.iot.iotsystem.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitHistoryDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("rfid")
    private RFID rfid;

    @JsonProperty("timeAt")
    private Date timeAt;
}
