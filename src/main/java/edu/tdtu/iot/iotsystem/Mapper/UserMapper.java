package edu.tdtu.iot.iotsystem.Mapper;


import edu.tdtu.iot.iotsystem.DTO.UserDTO;
import edu.tdtu.iot.iotsystem.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);



}
