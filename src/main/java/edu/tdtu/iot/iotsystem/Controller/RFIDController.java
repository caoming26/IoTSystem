package edu.tdtu.iot.iotsystem.Controller;

import edu.tdtu.iot.iotsystem.DTO.RfidDTO;
import edu.tdtu.iot.iotsystem.Mapper.RFIDMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import edu.tdtu.iot.iotsystem.Services.RfidService;


@Controller
@RequiredArgsConstructor
public class RFIDController {

    private final RfidService rfidService;

    @PostMapping("/api/add_rfid")
    public ResponseEntity<?> addRFID(@RequestBody RfidDTO request) {

        boolean result = rfidService.addRFID(RFIDMapper.INSTANCE.RfidDTOToRFID(request));

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/update_rfid")
    public ResponseEntity<?> updateRFIDName(@RequestBody RfidDTO rfidDTO){
        return ResponseEntity.ok(rfidService.updateRFIDName(RFIDMapper.INSTANCE.RfidDTOToRFID(rfidDTO)));
    }
}
