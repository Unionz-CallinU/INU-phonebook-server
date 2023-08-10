package com.example.inuphonebook.service.departmentCrawling;

import com.example.inuphonebook.common.Constants;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.ImageCrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Transactional
@Service
public class GlobalLandscapeDepartmentService implements ImageCrawlingService {

    public static final String url = Constants.url;
    public static final String url2 = Constants.url2;

    @Override
    public void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getCrawling(departmentType, employeeRepository);
    }

    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "uipa") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1781452&siteId=" + siteId;
        } else if (departmentType == "politics") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1781438&siteId=" + siteId;
        } else if (departmentType == "econ") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1780420&siteId=" + siteId;
        } else if (departmentType == "trade") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1781298&siteId=" + siteId;
        } else if (departmentType == "ccs") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1781409&siteId=" + siteId;
        }
        return URI;
    }
}