package edu.tdtu.iot.iotsystem.Controller;

import edu.tdtu.iot.iotsystem.DTO.WarningThresholdDTO;
import edu.tdtu.iot.iotsystem.Services.WarningThresholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/warning-threshold")
@RequiredArgsConstructor
public class WarningThresholdController {
    private final WarningThresholdService warningThresholdService;

    @GetMapping("")
    public ResponseEntity<List<WarningThresholdDTO>> getWarningThresholds() {
        List<WarningThresholdDTO> warningThresholds = warningThresholdService.getWarningThreshold();
        if (warningThresholds == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(warningThresholds, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<WarningThresholdDTO>> addWarningThreshold(@RequestBody WarningThresholdDTO warningThresholdDTO) {
        List<WarningThresholdDTO> updatedThresholds = warningThresholdService.addWarningThreshold(warningThresholdDTO);
        return new ResponseEntity<>(updatedThresholds, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<List<WarningThresholdDTO>> updateWarningThreshold(@RequestBody WarningThresholdDTO warningThresholdDTO) {
        List<WarningThresholdDTO> updatedThresholds = warningThresholdService.updateWarningThreshold(warningThresholdDTO);
        return new ResponseEntity<>(updatedThresholds, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<WarningThresholdDTO>> deleteWarningThreshold(@RequestBody WarningThresholdDTO warningThresholdDTO) {
        List<WarningThresholdDTO> updatedThresholds = warningThresholdService.deleteWarningThreshold(warningThresholdDTO);
        return new ResponseEntity<>(updatedThresholds, HttpStatus.OK);
    }
}
