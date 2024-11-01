package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("warning_threshold")
public class WarningThreshold {
    @Id
    private String id;
    private String type;
    private double threshold;
}
