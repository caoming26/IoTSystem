package edu.tdtu.iot.iotsystem.Services;


import edu.tdtu.iot.iotsystem.DTO.ReportDTO;
import edu.tdtu.iot.iotsystem.Entity.Report;
import edu.tdtu.iot.iotsystem.Exceptions.BadRequestException;
import edu.tdtu.iot.iotsystem.Mapper.ReportMapper;
import edu.tdtu.iot.iotsystem.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static edu.tdtu.iot.iotsystem.Constant.Constant.TEMPERATURE;
import static edu.tdtu.iot.iotsystem.Constant.Constant.getTypes;

@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService{

    private final ReportRepository reportRepository;

    @Override
    public List<Report> getReports(){
        return reportRepository.findAll(Sort.by(Sort.Direction.ASC, "time"));
    }

    @Override
    public List<Report> getReportsByType(String type){
        return reportRepository.findByType(type);
    }

    @Override
    public List<Report> addReport(ReportDTO reportDTO){

        if (!getTypes().contains(reportDTO.getType())){
            throw new BadRequestException("Type is not exist, please check your type!");
        }

        //If system has saved 50 reports, system will clean all and start over
        if (deleteIfFull(reportDTO.getType())){
            System.out.println("System has started over!");
        }

        reportDTO.setTime(new Date(System.currentTimeMillis()));

        reportRepository.save(
                ReportMapper
                        .INSTANCE
                        .reportDTOToReport(reportDTO) //convert dto to entity
        );
        return getReports();
    }

    public boolean deleteIfFull(String type){
        if (reportRepository.countByType(type) == 50){
            reportRepository.deleteByType(type);
            return true;
        }
        return false;
    }
}
