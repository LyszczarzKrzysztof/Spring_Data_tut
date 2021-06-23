package com.example._05pagingandsorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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

        logger.info("ALL UNSORTED: ");
        printAll(findAllUnsorted());

        logger.info("SORTED BY FIRST NAME: ");
        printAll(getSortedByFirstName());

        logger.info("SORTED BY LAST AND FIRST NAME: ");
        printAll(getSortedByLastAndFirstName());

        Page<Employee> page = employeeRepositoryJpa.findAll(PageRequest.of(2, 10));
        logger.info("TOTAL NUMBER OF EMPLOYEES: " + page.getTotalElements());
        logger.info("TOTAL NUMBER OF PAGES: " + page.getTotalPages());
        logger.info("NUMBER OF CURRENT PAGE: " + page.getNumber());
        printAll(page.getContent());

    }

    private List<Employee> getSortedByLastAndFirstName() {
        return employeeRepositoryJpa.findAll(Sort.by(
                new Sort.Order(Sort.Direction.ASC, "lastName"),
                new Sort.Order(Sort.Direction.ASC, "firstName")
        ));
    }

    private List<Employee> getSortedByFirstName() {
        return employeeRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    private List<Employee> findAllUnsorted() {
        return employeeRepositoryJpa.findAll();
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
