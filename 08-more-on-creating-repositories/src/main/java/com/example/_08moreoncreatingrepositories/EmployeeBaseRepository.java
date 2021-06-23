package com.example._08moreoncreatingrepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//przy tej adnotacji jest to tzw repozytorium bazowe - nie mozna z niego stworzyc beana - ewentualnie poprzez
// stworzenie nowego repozytorium na podstawie tego repozytorium
@NoRepositoryBean
public interface EmployeeBaseRepository extends JpaRepository<Employee, Long> {
}
