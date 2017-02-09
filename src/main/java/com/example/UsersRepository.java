package com.example;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
 
public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
    List<Users> findByName(String name);
    
    public List<Projects> findByProject(Integer id); 
    
    Page<Users> findAll(Pageable pageable);
}
