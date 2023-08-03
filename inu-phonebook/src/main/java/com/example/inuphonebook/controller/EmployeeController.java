package com.example.inuphonebook.controller;


import com.example.inuphonebook.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


}
