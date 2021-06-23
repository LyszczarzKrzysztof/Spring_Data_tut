package com.example._07namedqueries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RunAtStart {
    private final EmployeeRepositoryJpa employeeRepositoryJpa;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = LoggerFactory.getLogger(RunAtStart.class);


    @Autowired
    public RunAtStart(EmployeeRepositoryJpa employeeRepositoryJpa, EmployeeGenerator employeeGenerator) {
        this.employeeRepositoryJpa = employeeRepositoryJpa;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

//        List<Employee> allWithSalariesBetweenSomeValues = employeeRepositoryJpa.findAllWithSalariesBetweenSomeValues(
//                new BigDecimal("1000"), new BigDecimal("2000"));
//        printAll(allWithSalariesBetweenSomeValues);

//        List<Employee> guyWithTheHighestSalary = employeeRepositoryJpa.findGuyWithTheHighestSalary();
//        printAll(guyWithTheHighestSalary);
//
//        Employee onlyOneGuyWithTheHighestSalary = employeeRepositoryJpa.findOnlyOneGuyWithTheHighestSalary();
//        logger.info(""+onlyOneGuyWithTheHighestSalary);

        List<Employee> nativelyWithSalaryBetween = employeeRepositoryJpa.findNativelyWithSalaryBetween(
                new BigDecimal("1000"), new BigDecimal("2000"));
        printAll(nativelyWithSalaryBetween);
    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepositoryJpa.save(employeeGenerator.generate());
        }
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(employee -> logger.info(employee.toString()));
    }
}
