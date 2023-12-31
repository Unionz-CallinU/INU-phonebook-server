package com.example.inuphonebook.dto.employee;

import com.example.inuphonebook.domain.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.*;

public class EmployeeRespDto {

    @Getter
    @Setter
    public static class EmployeeListRespDto {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        public EmployeeListRespDto(List<Employee> employeeList) {
            this.employeeDtoList = employeeList.stream()
                                    .map(e -> new EmployeeDto(e))
                                    .collect(Collectors.toList());
        }

        @Getter
        public class EmployeeDto {
            @ApiModelProperty(example = "1")
            private Long id;
            @ApiModelProperty(example = "정보기술대학")
            private String college;
            @ApiModelProperty(example = "컴퓨터공학과")
            private String department;
            @ApiModelProperty(example = "교수")
            private String position;
            @ApiModelProperty(example = "구준형")
            private String name;
            @ApiModelProperty(example = "01040363457")
            private String phoneNumber;
            @ApiModelProperty(example = "/9ydfe4254...")
            private String imageUrl;
            @ApiModelProperty(example = "교수")
            private String role;
            @ApiModelProperty(example = "rnwnsgud90@naver.com")
            private String email;

            public EmployeeDto(Employee employee) {
                this.id = employee.getId();
                this.college = ofNullable(employee.getCollege()).filter(s -> !s.isEmpty()).orElse(null);
                this.department = ofNullable(employee.getDepartment()).filter(s -> !s.isEmpty()).orElse(null);
                this.position = ofNullable(employee.getPosition()).filter(s -> !s.isEmpty()).orElse(null);
                this.name = ofNullable(employee.getName()).filter(s -> !s.isEmpty()).orElse(null);
                this.phoneNumber = ofNullable(employee.getPhoneNumber()).filter(s -> !s.isEmpty()).orElse(null);
                this.imageUrl = ofNullable(employee.getImageUrl()).filter(s -> !s.isEmpty()).orElse(null);
                this.role = ofNullable(employee.getRole()).filter(s -> !s.isEmpty()).orElse(null);
                this.email = ofNullable(employee.getEmail()).filter(s -> !s.isEmpty()).orElse(null);
            }
        }
    }

    @Getter
    @Setter
    public static class EmployeeDetailRespDto {
        @ApiModelProperty(example = "1")
        private Long id;
        @ApiModelProperty(example = "정보기술대학")
        private String college;
        @ApiModelProperty(example = "컴퓨터공학과")
        private String department;
        @ApiModelProperty(example = "교수")
        private String position;
        @ApiModelProperty(example = "구준형")
        private String name;
        @ApiModelProperty(example = "01040363457")
        private String phoneNumber;
        @ApiModelProperty(example = "/9ydfe4254...")
        private String imageUrl;
        @ApiModelProperty(example = "인문대학 관리")
        private String role;
        @ApiModelProperty(example = "rnwnsgud90@naver.com")
        private String email;


        public EmployeeDetailRespDto(Employee employee) {
            this.id = employee.getId();
            this.college = ofNullable(employee.getCollege()).filter(s -> !s.isEmpty()).orElse(null);
            this.department = ofNullable(employee.getDepartment()).filter(s -> !s.isEmpty()).orElse(null);
            this.position = ofNullable(employee.getPosition()).filter(s -> !s.isEmpty()).orElse(null);
            this.name = ofNullable(employee.getName()).filter(s -> !s.isEmpty()).orElse(null);
            this.phoneNumber = ofNullable(employee.getPhoneNumber()).filter(s -> !s.isEmpty()).orElse(null);
            this.imageUrl = ofNullable(employee.getImageUrl()).filter(s -> !s.isEmpty()).orElse(null);
            this.role = ofNullable(employee.getRole()).filter(s -> !s.isEmpty()).orElse(null);
            this.email = ofNullable(employee.getEmail()).filter(s -> !s.isEmpty()).orElse(null);
        }
    }
}
