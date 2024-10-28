package com.gaoming.MyJavaWebChat.Repository;

import com.gaoming.MyJavaWebChat.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role getRoleByName(String name);
}
