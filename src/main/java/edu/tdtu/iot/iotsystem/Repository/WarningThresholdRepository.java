package edu.tdtu.iot.iotsystem.Repository;

import edu.tdtu.iot.iotsystem.Entity.WarningThreshold;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarningThresholdRepository extends MongoRepository<WarningThreshold, String> {

    Optional<WarningThreshold> findByType(String type);
}
