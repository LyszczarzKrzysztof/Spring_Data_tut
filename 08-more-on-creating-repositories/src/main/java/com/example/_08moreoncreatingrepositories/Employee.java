package com.example._08moreoncreatingrepositories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;

//IMPORTANT: w name przed nazwą query musi być nazwa encji i kropka - bo wyskakuje blad ze query nie moze powiazac z encja
//wynika z tego podobno ze to samo query moze byc w wielu encjach i zeby bylo wiadomo na ktorej wywolujemy
@Entity
@NamedQuery(
        name = "Employee.findAllWithSalariesBetweenSomeValues",
        query = "select e from Employee e where salary between ?1 and ?2"
)
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate employmentDate;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", employmentDate=" + employmentDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }
}
