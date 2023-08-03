package com.example.inuphonebook.dto.employee;

import lombok.Getter;

public class EmployeeReqDto {

    @Getter
    public static class EmployeeSearchReqDto{
        private String search;
    }
}
