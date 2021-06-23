package com.example._04definingrepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryCrud extends CrudRepository<Employee,Long> {
    Iterable<Employee> findByFirstName(String firstName);
}
