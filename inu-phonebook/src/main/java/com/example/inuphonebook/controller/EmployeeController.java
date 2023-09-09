package com.example.inuphonebook.controller;


import com.example.inuphonebook.dto.ResponseDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeDetailRespDto;
import com.example.inuphonebook.dto.employee.EmployeeRespDto.EmployeeListRespDto;
import com.example.inuphonebook.service.EmployeeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "직원 리스트조회", description = "요청 파라미터로 String 값(이름,전화번호,학과)을 보내주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "상세조회 성공", content = @Content(schema = @Schema(implementation = EmployeeListRespDto.class)))
    })
    @io.swagger.annotations.ApiResponses(
            @io.swagger.annotations.ApiResponse(code = 200,message = "ok,",response = EmployeeListRespDto.class)
    )
    @GetMapping
    public ResponseEntity<?> getEmployeeList(@RequestParam String employeeSearchReqDto){
        EmployeeListRespDto employeeListRespDto = employeeService.findAllEmployee(employeeSearchReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원리스트조회 성공", employeeListRespDto), HttpStatus.OK);
    }

    @Operation(summary = "직원 상세조회", description = "경로변수에 id 값을 보내주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "상세조회 성공", content = @Content(schema = @Schema(implementation = EmployeeDetailRespDto.class)))
    })
    @io.swagger.annotations.ApiResponses(
            @io.swagger.annotations.ApiResponse(code = 200,message = "ok,",response = EmployeeDetailRespDto.class)
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        EmployeeDetailRespDto employeeDetailRespDto = employeeService.findOneEmployee(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "직원상세조회 성공", employeeDetailRespDto), HttpStatus.OK);
    }


}
