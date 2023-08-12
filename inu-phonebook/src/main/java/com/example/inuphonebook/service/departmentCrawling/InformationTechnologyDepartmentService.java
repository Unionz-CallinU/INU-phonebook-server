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
public class InformationTechnologyDepartmentService implements ImageCrawlingService {

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
    public void getCrawlingEmbeddedMore(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawlingEmbeddedMore(departmentType, employeeRepository);
    }

    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "computer") {
            siteId = "isis";
            URI = url + url2_WWW + "=2236524&siteId=" + siteId;
        } else if (departmentType == "communication") {
            siteId = "ite";
            URI = url + url2_WWW + "=1785787&siteId=" + siteId;
        } else if (departmentType == "ese") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1784839&siteId=" + siteId;
        }

        return URI;
    }
}
