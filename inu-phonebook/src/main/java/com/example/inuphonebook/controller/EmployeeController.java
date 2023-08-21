package com.example.inuphonebook.controller;


import com.example.inuphonebook.dto.ResponseDto;
import com.example.inuphonebook.dto.employee.EmployeeReqDto.EmployeeSearchReqDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeDetailRespDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeListRespDto;
import com.example.inuphonebook.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> getEmployeeList(@RequestBody EmployeeSearchReqDto employeeSearchReqDto){
        EmployeeListRespDto employeeListRespDto = employeeService.직원리스트조회(employeeSearchReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원리스트조회 성공", employeeListRespDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        EmployeeDetailRespDto employeeDetailRespDto = employeeService.직원상세조회(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원상세조회 성공", employeeDetailRespDto), HttpStatus.OK);
    }


}
