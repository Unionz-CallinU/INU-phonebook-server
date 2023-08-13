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
public class LifeScienceDepartmentService implements ImageCrawlingService {

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
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "life") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1041100&siteId=" + siteId;
        } else if (departmentType == "bioeng") {
            siteId = "engineeringlife";
            URI = url + departmentType + url2 + "=108378&siteId=" + siteId;
        } else if (departmentType == "nanobio") {
            siteId = "nano";
            URI = url + departmentType + url2 + "=1872176&siteId=" + siteId;
        }
        return URI;
    }
}
