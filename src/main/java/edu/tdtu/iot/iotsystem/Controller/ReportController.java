package edu.tdtu.iot.iotsystem.Controller;

import edu.tdtu.iot.iotsystem.DTO.ReportDTO;
import edu.tdtu.iot.iotsystem.Entity.Report;
import edu.tdtu.iot.iotsystem.Services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.tdtu.iot.iotsystem.Constant.Constant.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // API để lấy tất cả các báo cáo theo thứ tự thời gian
    @GetMapping("")
    public ResponseEntity<List<Report>> getReports() {
        List<Report> reports = reportService.getReports();
        return new ResponseEntity<>(reports, HttpStatus.OK); // Trả về danh sách với HTTP 200 OK
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<Report>> getTemperatureReports() {
        List<Report> reports = reportService.getReportsByType(TEMPERATURE);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/humidity")
    public ResponseEntity<List<Report>> getHumidityReports() {
        List<Report> reports = reportService.getReportsByType(HUMIDITY);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
    @GetMapping("/dust")
    public ResponseEntity<List<Report>> getDustReports() {
        List<Report> reports = reportService.getReportsByType(DUST);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
    @GetMapping("/energy_consumption")
    public ResponseEntity<List<Report>> getEnergyConsumptionReports() {
        List<Report> reports = reportService.getReportsByType(ENERGY_CONSUMPTION);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // API để thêm một báo cáo mới
    @PostMapping("/add")
    public ResponseEntity<List<Report>> addReport(@RequestBody ReportDTO reportDTO) {
        List<Report> updatedReports = reportService.addReport(reportDTO);
        return new ResponseEntity<>(updatedReports, HttpStatus.CREATED); // Trả về danh sách mới với HTTP 201 Created
    }
}
