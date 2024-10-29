package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("warning_threshold")
public class WarningThreshold {
    private String id;
    private String type;
    private double threshold;
}
