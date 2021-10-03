package com.demo.service;

import com.demo.domain.entities.Employee;
import com.demo.domain.models.service.EmployeeServiceModel;
import com.demo.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {



    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;



    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        return  this.employeeRepository.findAllByOrderByStartDataAsc()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());

    }






    @Override
    public EmployeeServiceModel saveEmployee(EmployeeServiceModel employeeServiceModel) {
         Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);

         return this.modelMapper.map(this.employeeRepository.saveAndFlush(employee),EmployeeServiceModel.class);

    }

    @Override
    public EmployeeServiceModel getEmployeeById(String id) {
  Employee employee = this.employeeRepository.findById(id).get();

  return this.modelMapper.map(employee, EmployeeServiceModel.class);


    }

    @Override
    public void deleteEmployeeById(String id) {
       Employee employee = this.employeeRepository.findById(id).get();
      this.employeeRepository.delete(employee);
    }





    @Override
    public EmployeeServiceModel updateEmployee(String id, EmployeeServiceModel employeeServiceModel) {
       Employee employee = this.employeeRepository.findById(id).get();
        employee.setFirstName(employeeServiceModel.getFirstName());
        employee.setLastName(employeeServiceModel.getLastName());
        employee.setPosition(employeeServiceModel.getPosition());
        employee.setEmail(employeeServiceModel.getEmail());
        employee.setAddress(employeeServiceModel.getAddress());

        return this.modelMapper.map(this.employeeRepository.saveAndFlush(employee), EmployeeServiceModel.class);
    }




    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return this.employeeRepository.findAll(pageable);
    }


}








