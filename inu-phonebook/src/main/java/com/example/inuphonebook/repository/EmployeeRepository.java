package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
