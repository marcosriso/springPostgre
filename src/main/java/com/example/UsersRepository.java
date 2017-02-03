package com.example;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
 
public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findByName(String name);
}
