package edu.tdtu.iot.iotsystem.Repository;

import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.VisitHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VisitHistoryRepository extends MongoRepository<VisitHistory, String> {

    Optional<List<VisitHistory>> findByRfid(RFID rfid);
}
