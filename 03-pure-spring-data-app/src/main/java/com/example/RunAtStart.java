package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart(){
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Nowak");
//        tak jak ponizej cos do konca mu nie pasuje
//        employee.setSalary(new BigDecimal(3000.0));
        employee.setSalary(new BigDecimal("3000.0"));
        employeeRepository.save(employee);
        Employee jan = employeeRepository.findByFirstName("Jan");
        System.out.println("Janek: "+jan);
    }
}
