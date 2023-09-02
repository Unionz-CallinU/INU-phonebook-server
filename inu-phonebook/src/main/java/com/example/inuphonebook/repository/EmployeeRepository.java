package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByNameAndDepartment(String name, String department);
}
