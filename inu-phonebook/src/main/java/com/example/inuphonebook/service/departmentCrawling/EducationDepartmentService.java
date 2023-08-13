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
            URI = url + url2_WWW + "=717794&siteId=" + siteId;
        } else if (departmentType == "eduenglish") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1688933&siteId=" + siteId;
        } else if (departmentType == "edujapanese") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=717910&siteId=" + siteId;
        } else if (departmentType == "edumath") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=717852&siteId=" + siteId;
        } else if (departmentType == "eduphysical") {
            siteId = departmentType;
            URI = url + url2_WWW + "=1795517&siteId=" + siteId;
        } else if (departmentType == "ece") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1795478&siteId=" + siteId;
        } else if (departmentType == "eduhistory") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=93579&siteId=" + siteId;
        } else if (departmentType == "eduethics") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1233109&siteId=" + siteId;
        }
        return URI;
    }
}
