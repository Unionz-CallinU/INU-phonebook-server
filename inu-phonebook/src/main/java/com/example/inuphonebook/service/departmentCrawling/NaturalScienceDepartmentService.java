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
public class NaturalScienceDepartmentService implements ImageCrawlingService {

    @Value("${location.url}")
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
        if (departmentType == "math") {
            siteId = "isu";
            URI = url + departmentType + url2 + siteId + "/2227" + url3;
        } else if (departmentType == "physics") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2161" + url3;
        } else if (departmentType == "chem") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2376" + url3;
        } else if (departmentType == "uifashion") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2278" + url3;
        } else if (departmentType == "marine") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2313" + url3;
        }
        return URI;
    }

}
