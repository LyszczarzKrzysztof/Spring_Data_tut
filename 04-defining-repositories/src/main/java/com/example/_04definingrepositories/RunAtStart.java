package com.example._04definingrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class RunAtStart {
    private final EmployeeRepositoryCrud employeeRepositoryCrud;
    private final EmployeeRepositoryJpa employeeRepositoryJpa;

    @Autowired
    public RunAtStart(EmployeeRepositoryCrud employeeRepositoryCrud, EmployeeRepositoryJpa employeeRepositoryJpa) {
        this.employeeRepositoryCrud = employeeRepositoryCrud;
        this.employeeRepositoryJpa = employeeRepositoryJpa;
    }


/**
 * Rózne typy repozytoriow rozniace sie iloscia metod i typami zwracanymi
 * */

    @PostConstruct
    public void runAtStart() {
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Nowak");

    /* tak jak ponizej cos do konca mu nie pasuje
    employee.setSalary(new BigDecimal(3000.0));
    */
        employee.setSalary(new BigDecimal("3000.0"));
        employee.setEmploymentDate(LocalDate.of(2016, 1, 1));

        employeeRepositoryCrud.save(employee);

        Iterable<Employee> jansCrudRepo = employeeRepositoryCrud.findByFirstName("Jan");
        Employee janCrudRepo = jansCrudRepo.iterator().next();
        System.out.println("Janek: " + janCrudRepo);

        List<Employee> jansJpaRepo = employeeRepositoryJpa.findByFirstName("Jan");
        Employee janJpaRepo = jansJpaRepo.get(0);

        List<Employee> allEmployees = employeeRepositoryJpa.findAll();
    }
}
