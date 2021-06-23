package com.example._05pagingandsorting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstName(String firstName);
}
