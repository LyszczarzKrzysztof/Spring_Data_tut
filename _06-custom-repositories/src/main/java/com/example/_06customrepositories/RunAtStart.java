package com.example._06customrepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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

//        logger.info("ALL EMPLOYEES");
//        printAll(employeeRepositoryJpa.findByFirstNameIgnoreCase("nOAH"));
//        printAll(employeeRepositoryJpa.findByLastNameOrderByFirstNameDesc("TayLOR"));
//        printAll(employeeRepositoryJpa.findBySalaryBetween(new BigDecimal("1000"), new BigDecimal("2000")));

//        logger.info("FIRST LUCAS: "+employeeRepositoryJpa.findFirstByFirstName("Lucas"));
//        logger.info("FIRST JOHN: "+employeeRepositoryJpa.findFirstByFirstName("John"));
//        printAll(employeeRepositoryJpa.findTop3ByFirstName("Lucas"));
//        printAll(employeeRepositoryJpa.findFirst3ByFirstName("Lucas"));
//        logger.info("Number of Lucas Smith's: " + employeeRepositoryJpa.countByFirstNameAndLastNameIgnoreCase("Lucas", "Smith"));
//        Page<Employee> lucasPage = employeeRepositoryJpa.findByFirstName("Lucas", PageRequest.of(1,3));
//        printAll(lucasPage.getContent());
//        logger.info("Total number of pages: "+lucasPage.getTotalPages());

//  TODO - problem z transakcją - krzyczy, że (chyba) stream wywołuje na sobie close() i nie mozna go dalej uzywać
//        metoda z findTop5ByFirstNameTransactional(); ze zdefiniowaną transakcją i blokeim try-with-resources nie dziala
//        a wszedzie ja opisuja jako rozwiazanie, podobno da się zrobic zeby streamy dzialaly tu bez transakcji

//        findTop5ByFirstNameTransactional();

//        employeeRepositoryJpa.findFirstByFirstNameIgnoreCase("Lucas").thenAccept(lucas -> {
//            logger.info("Lucas: " + lucas);
//        });
    }

    @Transactional(readOnly = true)
    public void findTop5ByFirstNameTransactional() {
        try (Stream<Employee> lucasStream = employeeRepositoryJpa.findTop5ByFirstName("Lucas")) {
            List<String> lucasesLastNamesList = lucasStream.map(Employee::getLastName).collect(Collectors.toList());
            for (String s : lucasesLastNamesList) {
                logger.info(s);
            }
        }
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
