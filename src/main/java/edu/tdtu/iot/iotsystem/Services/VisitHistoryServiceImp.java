package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.VisitHistory;
import edu.tdtu.iot.iotsystem.Repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitHistoryServiceImp implements VisitHistoryService {

    private final VisitHistoryRepository visitHistoryRepository;


    // Lấy tất cả bản ghi VisitHistory
    @Override
    public List<VisitHistory> getAllVisitHistories() {
        return visitHistoryRepository.findAll();
    }

    // Lấy VisitHistory theo id
    public Optional<List<VisitHistory>> getVisitHistoryByRfid(RFID id) {
        return visitHistoryRepository.findByRfid(id);
    }

    // Tạo mới hoặc cập nhật VisitHistory
    public VisitHistory saveVisitHistory(VisitHistory visitHistory) {
        return visitHistoryRepository.save(visitHistory);
    }

    // Xóa VisitHistory theo id
    public void deleteVisitHistoryById(String id) {
        visitHistoryRepository.deleteById(id);
    }

    @Override
    public Optional<VisitHistory> getVisitHistoryById(String id) {
        return visitHistoryRepository.findById(id);
    }
}
