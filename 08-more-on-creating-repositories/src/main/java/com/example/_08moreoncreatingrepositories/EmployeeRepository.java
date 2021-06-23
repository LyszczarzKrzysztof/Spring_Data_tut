package com.example._08moreoncreatingrepositories;


import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Long> {

//    ciekawostka przy rozszerzeniu extends Repository - repozytorium nie ma żadnych metod - w sensie interfejs Repository nie ma żadnych metod - JEST PUSTY -
//    i nic po nim nie implementujemy
//    - a wystarczy wpisać tylko definicje metody jak poniżej (bez implementacji w jakiejs warstwie serwisowej) a metoda działa jak np w JpaReposiotry w którym JEST zdefiniowana
    Employee save(Employee employee);
    List<Employee> findAll();
}

