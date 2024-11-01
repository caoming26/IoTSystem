package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.DTO.WarningThresholdDTO;

import java.util.List;

public interface WarningThresholdService {
    List<WarningThresholdDTO> getWarningThreshold();

    List<WarningThresholdDTO> addWarningThreshold(WarningThresholdDTO warningThresholdDTO);

    List<WarningThresholdDTO> updateWarningThreshold(WarningThresholdDTO warningThresholdDTO);

    List<WarningThresholdDTO> deleteWarningThreshold(WarningThresholdDTO warningThresholdDTO);
}
