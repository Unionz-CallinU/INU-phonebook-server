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
public class AdministrationDepartmentService implements ImageCrawlingService {

    @Value("${location.url}")
    private String url;

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
        if (departmentType == "management") {
            siteId = departmentType;
            URI = url + url2_WWW + "=1787857&siteId=" + siteId;
        } else if (departmentType == "tax") {
            siteId = departmentType;
            URI = url + url2_WWW + "=1787871&siteId=" + siteId;
        }
        return URI;
    }
}
