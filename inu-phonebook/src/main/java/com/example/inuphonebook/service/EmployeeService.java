package com.example.inuphonebook.service;

import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.dto.employee.EmployeeReqDto.EmployeeSearchReqDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeDetailRespDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeListRespDto;
import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeListRespDto findAllEmployee(EmployeeSearchReqDto employeeSearchReqDto) {
        List<Employee> employeeList = employeeRepository.findEmployeeList(employeeSearchReqDto.getSearch());
        return new EmployeeListRespDto(employeeList);

    }

    public EmployeeDetailRespDto findOneEmployee(Long id) {
        Employee employeePS = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 id의 직원이 존재하지 않습니다."));
        return new EmployeeDetailRespDto(employeePS);
    }
}
