package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.VisitHistory;

import java.util.List;
import java.util.Optional;

public interface VisitHistoryService {

    List<VisitHistory> getAllVisitHistories();

    // Lấy VisitHistory theo id
    Optional<List<VisitHistory>> getVisitHistoryByRfid(RFID id);

    // Tạo mới hoặc cập nhật VisitHistory
    VisitHistory saveVisitHistory(VisitHistory visitHistory);

    // Xóa VisitHistory theo id
    void deleteVisitHistoryById(String id);

    Optional<VisitHistory> getVisitHistoryById(String id);
}
