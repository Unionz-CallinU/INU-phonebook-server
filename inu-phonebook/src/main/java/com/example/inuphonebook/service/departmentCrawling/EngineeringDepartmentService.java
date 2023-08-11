package com.example.inuphonebook.service.departmentCrawling;


import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.ImageCrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Transactional
@Service
public class EngineeringDepartmentService implements ImageCrawlingService {

    @Value("${location.url}")
    private String url;
    @Value("${location.url2}")
    private String url2;
    @Value("${location.url2_WWW}")
    private String url2_WWW;

    @Override
    public void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawling(departmentType, employeeRepository);
    }

    @Override
    public void getCrawling_Mechanical(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawling_Mechanical(departmentType, employeeRepository);
    }

    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "me") {
            URI = url + "www.inu.ac.kr/cop/schoolDepartmentIntroduction/meEmpList.do";
        } else if (departmentType == "meca") {
            siteId = departmentType;
            URI = url + url2_WWW + "=118632&siteId=" + siteId;
        } else if (departmentType == "elec") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1784455&siteId=" + siteId;
        } else if (departmentType == "ee") {
            siteId = "electron";
            URI = url + departmentType + url2 + "=1784460&siteId=" + siteId;
        } else if (departmentType == "ime") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1783283&siteId=" + siteId;
        } else if (departmentType == "mse") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1784347&siteId=" + siteId;
        } else if (departmentType == "safety") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1784443&siteId=" + siteId;
        } else if (departmentType == "echeme") {
            siteId = "energy";
            URI = url + departmentType + url2 + "=1784450&siteId=" + siteId;
        }
        return URI;
    }
}



