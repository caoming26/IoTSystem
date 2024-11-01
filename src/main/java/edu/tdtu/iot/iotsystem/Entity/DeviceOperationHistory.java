package edu.tdtu.iot.iotsystem.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document("device_operation_history")
public class DeviceOperationHistory {
    @Id
    private String id;
    private String name;
    private Date timeOn;
    private Date timeOff;
    private boolean working;
}
