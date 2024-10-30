package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.DTO.WarningThresholdDTO;
import edu.tdtu.iot.iotsystem.Entity.WarningThreshold;
import edu.tdtu.iot.iotsystem.Exceptions.BadRequestException;
import edu.tdtu.iot.iotsystem.Exceptions.DuplicateException;
import edu.tdtu.iot.iotsystem.Mapper.WarningThresholdMapper;
import edu.tdtu.iot.iotsystem.Repository.WarningThresholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static edu.tdtu.iot.iotsystem.Constant.Constant.getTypes;

@Service
@RequiredArgsConstructor
public class WarningThresholdServiceImp implements WarningThresholdService{
    private final WarningThresholdRepository warningThresholdRepository;

    @Override
    public List<WarningThresholdDTO> getWarningThreshold(){
        List<WarningThreshold> warningThresholds = warningThresholdRepository.findAll();
        if (warningThresholds.isEmpty()){
            return null;
        }
        List<WarningThresholdDTO> warningThresholdDTOS = new ArrayList<>();
        for (WarningThreshold threshold:warningThresholds) {
            warningThresholdDTOS.add(
                    WarningThresholdMapper
                            .INSTANCE
                            .warningThresholdToWarningThresholdDTO(threshold) //convert entity to dto
            );
        }
        return warningThresholdDTOS;
    }

    @Override
    public List<WarningThresholdDTO> addWarningThreshold(WarningThresholdDTO warningThresholdDTO){
        if (!getTypes().contains(warningThresholdDTO.getType())){
            throw new BadRequestException("Type is not exist! Check your type!");
        }
        List<WarningThreshold> warningThresholds = warningThresholdRepository.findAll();
        if (!warningThresholds.isEmpty()){
            for (WarningThreshold threshold: warningThresholds) {
                if (threshold.getType().equals(warningThresholdDTO.getType())){
                    throw new DuplicateException("Threshold for this type is exits!");
                }
            }
        }

        warningThresholdRepository.save(
                WarningThresholdMapper
                        .INSTANCE
                        .warningThresholdDTOToWarningThreshold(warningThresholdDTO) //convert dto to entity
        );
        return getWarningThreshold();
    }

    @Override
    public List<WarningThresholdDTO> updateWarningThreshold(WarningThresholdDTO warningThresholdDTO){
        WarningThreshold warningThreshold = warningThresholdRepository.findByType(warningThresholdDTO.getType())
                .orElseThrow(()->new BadRequestException("Type is not exist!"));

        warningThreshold.setType(warningThresholdDTO.getType());
        warningThreshold.setThreshold(warningThresholdDTO.getThreshold());

        warningThresholdRepository.save(warningThreshold);

        return getWarningThreshold();
    }

    @Override
    public List<WarningThresholdDTO> deleteWarningThreshold(WarningThresholdDTO warningThresholdDTO){
        WarningThreshold warningThreshold = warningThresholdRepository.findByType(warningThresholdDTO.getType())
                .orElseThrow(()->new BadRequestException("Type is not exist!"));

        warningThresholdRepository.delete(warningThreshold);

        return getWarningThreshold();
    }

}
