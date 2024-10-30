package edu.tdtu.iot.iotsystem.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.User;
import edu.tdtu.iot.iotsystem.Entity.VisitHistory;
import edu.tdtu.iot.iotsystem.Mapper.RFIDData;
import edu.tdtu.iot.iotsystem.Services.AuthenticationService;
import edu.tdtu.iot.iotsystem.Services.RfidService;
import edu.tdtu.iot.iotsystem.Services.VisitHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visit-history")
@RequiredArgsConstructor
public class VisitHistoryController {

    private final VisitHistoryService visitHistoryService;
    
    private final RfidService rfidService;

    private final AuthenticationService authenticationService;

    // API lấy tất cả bản ghi VisitHistory
    @GetMapping
    public ResponseEntity<List<VisitHistory>> getAllVisitHistories() {
        List<VisitHistory> visitHistories = visitHistoryService.getAllVisitHistories();
        return ResponseEntity.ok(visitHistories);
    }

    // API lấy VisitHistory theo ID
    @GetMapping("/{rfid}")
    public ResponseEntity<List<VisitHistory>> getVisitHistoryByRfid(@PathVariable String id) {
        Optional<RFID> r = rfidService.getRfidById(id);
        return r.map(rfid -> visitHistoryService.getVisitHistoryByRfid(rfid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build())).orElseGet(() -> (ResponseEntity<List<VisitHistory>>) ResponseEntity.notFound());
    }

    // API tạo mới VisitHistory
    @PostMapping
    public ResponseEntity<VisitHistory> createVisitHistory(@RequestBody String rfid) throws JsonProcessingException {
        System.out.println(rfid);

        ObjectMapper mapper = new ObjectMapper();

        // Chuyển JSON thành đối tượng Java
        RFIDData data = mapper.readValue(rfid, RFIDData.class);
        System.out.println("RFID: " + data.getRfid());

        Optional<RFID> r = rfidService.getRfidById(data.getRfid());

        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        VisitHistory visithis = new VisitHistory();
        System.out.println(r.toString());
        r.ifPresent(visithis::setRfid);
        visithis.setTimeAt(date);

        VisitHistory savedVisitHistory = visitHistoryService.saveVisitHistory(visithis);
        return ResponseEntity.ok(savedVisitHistory);
    }

    // API cập nhật VisitHistory
    @PutMapping("/{id}")
    public ResponseEntity<VisitHistory> updateVisitHistory(
            @PathVariable String id,
            @RequestBody VisitHistory visitHistory) {
        if (visitHistoryService.getVisitHistoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        visitHistory.setId(id);
        VisitHistory updatedVisitHistory = visitHistoryService.saveVisitHistory(visitHistory);
        return ResponseEntity.ok(updatedVisitHistory);
    }

    // API xóa VisitHistory theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitHistoryById(@PathVariable String id) {
        if (!visitHistoryService.getVisitHistoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        visitHistoryService.deleteVisitHistoryById(id);
        return ResponseEntity.noContent().build();
    }
}
