package edu.tdtu.iot.iotsystem.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WarningThresholdDTO {
    @JsonProperty("type")
    private String type;
    @JsonProperty("threshold")
    private double threshold;
}
