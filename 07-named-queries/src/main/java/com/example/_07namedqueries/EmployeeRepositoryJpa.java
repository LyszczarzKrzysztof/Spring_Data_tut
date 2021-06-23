package com.example._07namedqueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<Employee, Long> {
    List<Employee> findAllWithSalariesBetweenSomeValues(BigDecimal from, BigDecimal to);

    @Query(value = "SELECT e FROM Employee e where e.salary = (select max(em.salary) from Employee em)")
    List<Employee> findGuyWithTheHighestSalary();

    @Query(value = "select * from employee where salary = (select max(salary) from employee ) limit 1", nativeQuery = true)
    Employee findOnlyOneGuyWithTheHighestSalary();

//    IMPORTANT: w tutorialu wymagalo nativeQuery = true oraz adnotacji @Param:   - ale byl starszy spring i starszy mysql
//@Query(value = "select e from Employee e where salary between :from and :to")
//List<Employee> findNativelyWithSalaryBetween(@Param("from") BigDecimal from, @Param("to") BigDecimal to);


//    przeszlo bez nativeQuery = true, pytanie czy ze wzgledu na wersje springa/mysqla czy ze wzgledu na cos innego
    @Query(value = "select e from Employee e where salary between :from and :to")
    List<Employee> findNativelyWithSalaryBetween(BigDecimal from, BigDecimal to);

    //    zapytanie jpql:
//    "select e from Employee e where salary between :from and :to"
//    zapytanie natywne:
//    "select * from employee where salary between :from and :to"
//    wyglada na to ze w zapytaniu natywnym nie uzywamy encji tylko nazw tabel (np. employee z malej litery)
}

