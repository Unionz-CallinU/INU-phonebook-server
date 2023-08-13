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
        if (departmentType == "urban") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1795447&siteId=" + siteId;
        } else if (departmentType == "civil") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=42098&siteId=" + siteId;
        } else if (departmentType == "et") {
            siteId = departmentType;
            URI = url + url2_WWW + "=1717072&siteId=" + siteId;
        } else if (departmentType == "ucv") {
            siteId = departmentType;
            URI = url + url2_WWW + "=1795420&siteId=" + siteId;
        } else if (departmentType == "archi") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1795388&siteId=" + siteId;
        }

        return URI;
    }
}
