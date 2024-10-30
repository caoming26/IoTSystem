package edu.tdtu.iot.iotsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter@Setter
public class ReportDTO {
    private Date time;
    private String type;
    private Double energyConsumption;
    private Double temperature;
    private Double humidity;
    private Double dust;
}
