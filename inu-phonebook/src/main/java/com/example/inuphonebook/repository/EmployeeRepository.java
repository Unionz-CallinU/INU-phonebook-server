package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
