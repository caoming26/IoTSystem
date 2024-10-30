package edu.tdtu.iot.iotsystem.Mapper;
import edu.tdtu.iot.iotsystem.DTO.RfidDTO;
import edu.tdtu.iot.iotsystem.Entity.RFID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RFIDMapper {
    RFIDMapper INSTANCE = Mappers.getMapper(RFIDMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rfid", target = "rfid")
    @Mapping(source = "name", target = "name")
    RfidDTO RFIDToRfidDTO(RFID rfid);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rfid", target = "rfid")
    @Mapping(source = "name", target = "name")
    RFID RfidDTOToRFID(RfidDTO rfidDTO);
}
