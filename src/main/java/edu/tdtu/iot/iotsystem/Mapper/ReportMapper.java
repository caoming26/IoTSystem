package edu.tdtu.iot.iotsystem.Mapper;

import edu.tdtu.iot.iotsystem.DTO.ReportDTO;
import edu.tdtu.iot.iotsystem.Entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    // Ánh xạ từ Report sang ReportDTO
    @Mapping(source = "time", target = "time")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "energyConsumption", target = "energyConsumption")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    @Mapping(source = "dust", target = "dust")
    ReportDTO reportToReportDTO(Report report);

    // Ánh xạ từ ReportDTO sang Report
    @Mapping(source = "time", target = "time")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "energyConsumption", target = "energyConsumption")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    @Mapping(source = "dust", target = "dust")
    Report reportDTOToReport(ReportDTO reportDTO);
}