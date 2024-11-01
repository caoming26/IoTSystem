package edu.tdtu.iot.iotsystem.Mapper;
import edu.tdtu.iot.iotsystem.DTO.VisitHistoryDTO;
import edu.tdtu.iot.iotsystem.Entity.RFID;
import edu.tdtu.iot.iotsystem.Entity.VisitHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VisitHistoryMapper {
    VisitHistoryMapper INSTANCE = Mappers.getMapper(VisitHistoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rfid", target = "rfid")
    @Mapping(source = "timeAt", target = "timeAt")
    VisitHistoryDTO RFIDToRfidDTO(VisitHistory visitHistory);
    VisitHistory RfidDTOToRFID(VisitHistoryDTO visitHistoryDTO);
}
