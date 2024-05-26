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

    @Value("${location.url}") //
    private String url;
    @Value("${location.url2}")
    private String url2;
    @Value("${location.url3}")
    private String url3;

    @Override
    public void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawling(departmentType, employeeRepository);
    }



    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "me") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3001" + url3;
        } else if (departmentType == "meca") {
            siteId = departmentType;
            URI = url + "bio-robot" + url2 + siteId + "/3041" + url3;
        } else if (departmentType == "elec") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3334" + url3;
        } else if (departmentType == "ee") {
            siteId = "electron";
            URI = url + departmentType + url2 + siteId + "/3383" + url3;
        } else if (departmentType == "ime") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3090" + url3;
        } else if (departmentType == "mse") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3158" + url3;
        } else if (departmentType == "safety") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3217" + url3;
        } else if (departmentType == "energy") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3278" + url3;
        }
        return URI;
    }
}



