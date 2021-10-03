package com.demo.repository;


import com.demo.domain.entities.Employee;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByOrderByStartDataAsc();

  //  Page< Employee > findAll(Pageable pageable);

}
