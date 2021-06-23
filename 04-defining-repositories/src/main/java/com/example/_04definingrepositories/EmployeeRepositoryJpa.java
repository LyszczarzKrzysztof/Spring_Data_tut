package com.example._04definingrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositoryJpa extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstName(String firstName);
}
