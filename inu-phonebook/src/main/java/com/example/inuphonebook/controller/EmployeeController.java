package com.example.inuphonebook.controller;


import com.example.inuphonebook.dto.ResponseDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeDetailRespDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeListRespDto;
import com.example.inuphonebook.service.EmployeeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "직원 리스트조회", description = "바디에 String 값(이름,전화번호,학과)를 json 형식으로 보내주세요")
    @ApiResponse(code = 200, message = "ok", response = EmployeeListRespDto.class)
    @GetMapping
    public ResponseEntity<?> getEmployeeList(@RequestParam String employeeSearchReqDto){
        EmployeeListRespDto employeeListRespDto = employeeService.findAllEmployee(employeeSearchReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원리스트조회 성공", employeeListRespDto), HttpStatus.OK);
    }

    @Operation(summary = "직원 상세조회", description = "경로변수에 id 값을 보내주세요")
    @ApiResponse(code = 200, message = "ok", response = EmployeeDetailRespDto.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        EmployeeDetailRespDto employeeDetailRespDto = employeeService.findOneEmployee(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원상세조회 성공", employeeDetailRespDto), HttpStatus.OK);
    }


}
