package com.fullstack.Service;



import com.fullstack.Exception.UserNotFoundException;
import com.fullstack.Model.Employee;
import com.fullstack.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    @Transactional
    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) throws UserNotFoundException {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow( () -> new UserNotFoundException("User by id " + id + " was not found") );
    }

    @Transactional
    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }

}
