package edu.tdtu.iot.iotsystem.Mapper;

import edu.tdtu.iot.iotsystem.DTO.WarningThresholdDTO;
import edu.tdtu.iot.iotsystem.Entity.WarningThreshold;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarningThresholdMapper {


    WarningThresholdMapper INSTANCE = Mappers.getMapper(WarningThresholdMapper.class);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "threshold", target = "threshold")
    WarningThresholdDTO warningThresholdToWarningThresholdDTO(WarningThreshold warningThreshold);

    @Mapping(source = "type", target = "type")
    @Mapping(source = "threshold", target = "threshold")
    WarningThreshold warningThresholdDTOToWarningThreshold(WarningThresholdDTO warningThresholdDTO);
}

