package com.example._09modifying;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("update Employee e set e.salary = :newSalary")
    int setSalaryForAll(@Param("newSalary") BigDecimal newSalary);
}

