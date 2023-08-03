package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findEmployeeList(String search);
}
