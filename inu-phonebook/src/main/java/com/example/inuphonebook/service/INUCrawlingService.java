package com.example.inuphonebook.service;

import com.example.inuphonebook.common.Util.WebDriverUtil;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class INUCrawlingService {
    private final EmployeeRepository employeeRepository;
    private static final String url = "https://www.inu.ac.kr/inu/742/subview.do?enc=Zm5jdDF8QEB8JTJGc3RhZmZTZWFyY2glMkZpbnUlMkZzcmNoVmlldy5kbyUzRnNyY2hEZXB0VHlwZSUzRGFsbCUyNnNyY2hEZXB0JTNEJTI2bmFtZSUzRCUyNSUyNSUyNg%3D%3D";

    public void getCrawlingDatas() throws IOException, InterruptedException {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        List<Employee> employees = webDriverUtil.useDriver(url);
        for (Employee employee : employees
        ) {
            Employee member = employeeRepository.findByNameAndDepartment(employee.getName(), employee.getDepartment())
                    .orElse(null);
            if (member != null) {
                employees.remove(employee);
            }
        }
        employeeRepository.saveAll(employees);
    }

}
