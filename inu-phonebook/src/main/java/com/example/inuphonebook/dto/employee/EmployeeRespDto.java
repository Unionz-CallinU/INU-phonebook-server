package com.example.inuphonebook.dto.employee;

import com.example.inuphonebook.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeRespDto {

    public static class EmployeeListRespDto {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        public EmployeeListRespDto(List<Employee> employeeList) {
            this.employeeDtoList = employeeList.stream()
                                    .map(e -> new EmployeeDto(e))
                                    .collect(Collectors.toList());
        }

        public class EmployeeDto {
            private Long id;
            private String college;
            private String department;
            private String name;
            private String phoneNumber;
            private String imageUrl;

            public EmployeeDto(Employee employee) {
                this.id = employee.getId();
                this.college = employee.getCollege();
                this.department = employee.getDepartment();
                this.name = employee.getName();
                this.phoneNumber = employee.getPhoneNumber();
                this.imageUrl = employee.getImageUrl();
            }
        }
    }

    public static class EmployeeDetailRespDto {
        private Long id;
        private String college;
        private String department;
        private String name;
        private String phoneNumber;
        private String imageUrl;

        public EmployeeDetailRespDto(Employee employee) {
            this.id = employee.getId();
            this.college = employee.getCollege();
            this.department = employee.getDepartment();
            this.name = employee.getName();
            this.phoneNumber = employee.getPhoneNumber();
            this.imageUrl = employee.getImageUrl();
        }
    }
}
