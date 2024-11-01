package edu.tdtu.iot.iotsystem.Services;

import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Repository.RfidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RfidServiceImp implements RfidService{

    private final RfidRepository RfidRepo;

    @Override
    public boolean checkRFID(RFID rfid) {
        return RfidRepo.findById(rfid.getId()).isPresent();
    }

    @Override
    public boolean addRFID(RFID rfid) {

        RFID r = RfidRepo.save(rfid);

        return !r.getId().isEmpty();

    }

    @Override
    public boolean updateRFIDName(RFID rfid){

        RFID r = RfidRepo.save(rfid);

        return !r.getId().isEmpty();
    }

    @Override
    public Optional<RFID> getRfidById(String id){
        return RfidRepo.findByRfid(id);
    }

}
