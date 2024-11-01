package edu.tdtu.iot.iotsystem.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RfidDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("rfid")
    private String rfid;

    @JsonProperty("name")
    private String name;
}
