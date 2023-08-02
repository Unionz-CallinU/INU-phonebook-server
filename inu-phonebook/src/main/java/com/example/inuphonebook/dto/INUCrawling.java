package com.example.inuphonebook.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class INUCrawling {
    private String college;
    private String department;
    private String position;
    private String name;
    private String role;
    private String phoneNumber;
    private String email;
}
