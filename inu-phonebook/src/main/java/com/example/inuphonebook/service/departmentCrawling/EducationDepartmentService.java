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
public class EducationDepartmentService implements ImageCrawlingService {
    @Value("${location.url}")
    private String url;
    @Value("${location.url2}")
    private String url2;
    @Value("${location.url3}")
    private String url3;
    @Value("${location.url2_WWW}")
    private String url2_WWW;
    @Override
    public void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawling(departmentType, employeeRepository);
    }

    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "edukorean") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4244" + url3;
        } else if (departmentType == "eduenglish") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4418" + url3;
        } else if (departmentType == "edujapanese") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4613" + url3;
        } else if (departmentType == "mathedu") {
            siteId = "edumath";
            URI = url + departmentType + url2 + siteId + "/4315" + url3;
        } else if (departmentType == "eduphysical") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4669" + url3;
        } else if (departmentType == "ece") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4498" + url3;
        } else if (departmentType == "eduhistory") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/7986" + url3;
        } else if (departmentType == "eduethics") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4559" + url3;
        }
        return URI;
    }
}
