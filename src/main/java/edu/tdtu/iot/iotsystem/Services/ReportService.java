package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.DTO.ReportDTO;
import edu.tdtu.iot.iotsystem.Entity.Report;

import java.util.List;

public interface ReportService {
    List<Report> getReports();


    List<Report> getReportsByType(String type);

    List<Report> addReport(ReportDTO reportDTO);
}
