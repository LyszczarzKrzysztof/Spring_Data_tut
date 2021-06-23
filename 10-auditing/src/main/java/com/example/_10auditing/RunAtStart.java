package com.example._10auditing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = LoggerFactory.getLogger(RunAtStart.class);


    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(employeeGenerator.generate());
        }
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(employee -> logger.info(employee.toString()));
    }
}
