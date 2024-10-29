package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data@Builder
@Document("reports")
public class Report {
    private String id;
    private Date time;
    private String type;
    private Double energyConsumption;
    private Double temperature;
    private Double humidity;
    private Double dust;
}
