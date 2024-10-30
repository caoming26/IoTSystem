package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data@Builder
@Document("reports")
public class Report {
<<<<<<< HEAD
=======

>>>>>>> 678e4d5aeef9ef0e192d04bbda51962549f0b0a6
    @Id
    private String id;
    private Date time;
    private String type;
    private Double energyConsumption;
    private Double temperature;
    private Double humidity;
    private Double dust;
}
