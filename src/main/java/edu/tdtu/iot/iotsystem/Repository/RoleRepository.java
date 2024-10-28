package edu.tdtu.iot.iotsystem.Repository;

import edu.tdtu.iot.iotsystem.Entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
    Role getRoleByName(String name);
}
