package com.demo.service;

import com.demo.domain.entities.Employee;
import com.demo.domain.models.service.EmployeeServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface EmployeeService {


    List<EmployeeServiceModel> getAllEmployees();

    EmployeeServiceModel saveEmployee(EmployeeServiceModel employeeServiceModel);

  EmployeeServiceModel getEmployeeById(String id);

    void deleteEmployeeById(String id);


    EmployeeServiceModel updateEmployee(String id, EmployeeServiceModel employeeServiceModel);




    Page<Employee> findPaginated(int pageNo, int pageSize);
}
