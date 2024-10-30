package edu.tdtu.iot.iotsystem.Services;


import edu.tdtu.iot.iotsystem.Entity.RFID;

import java.util.Optional;

public interface RfidService {
    boolean checkRFID(RFID rfid);

    boolean addRFID(RFID rfid);

    boolean updateRFIDName(RFID rfid);

    Optional<RFID> getRfidById(String id);
}
