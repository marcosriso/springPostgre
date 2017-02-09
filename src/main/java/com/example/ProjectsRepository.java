package com.example;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
 
public interface ProjectsRepository extends CrudRepository<Projects, Integer> {
    List<Projects> findByDescription(String description);
}
