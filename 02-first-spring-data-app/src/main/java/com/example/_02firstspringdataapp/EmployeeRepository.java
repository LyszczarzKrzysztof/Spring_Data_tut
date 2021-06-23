package com.example._02firstspringdataapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee findByFirstName(String firstName);
}
