package edu.tdtu.iot.iotsystem.Repository;

import edu.tdtu.iot.iotsystem.Entity.RFID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RfidRepository extends MongoRepository<RFID, String> {

    Optional<RFID> findByRfid(String rfid);

    RFID save(RFID rfid);

}
