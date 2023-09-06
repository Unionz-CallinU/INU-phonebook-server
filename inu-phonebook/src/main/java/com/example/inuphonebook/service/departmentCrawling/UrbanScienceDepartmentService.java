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
public class UrbanScienceDepartmentService implements ImageCrawlingService {

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
        if (departmentType == "urban") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4895" + url3;
        } else if (departmentType == "civil") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4701" + url3;
        } else if (departmentType == "et") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/7716" + url3;
        } else if (departmentType == "scity") {
            siteId = "ucv";
            URI = url + departmentType + url2 + siteId + "/4754" + url3;
        } else if (departmentType == "archi") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/4830" + url3;
        }

        return URI;
    }
}
