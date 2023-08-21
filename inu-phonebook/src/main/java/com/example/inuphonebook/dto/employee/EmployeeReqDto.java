package com.example.inuphonebook.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class EmployeeReqDto {

    @Getter
    public static class EmployeeSearchReqDto{
        @Schema(example = "박정준")
        private String search;
    }
}
