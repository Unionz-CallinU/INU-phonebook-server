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
public class SocialScienceDepartmentService implements ImageCrawlingService {

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
    public void getIframe(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        ImageCrawlingService.super.getIframe(departmentType, employeeRepository);
    }

    @Override
    public String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "dsw") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2486" + url3;
        } else if (departmentType == "newdays") {
            siteId = "shinbang";
            URI = url + departmentType + url2 + siteId + "/2528" + url3;
        } else if (departmentType == "cls") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2458" + url3;
        } else if (departmentType == "hrd") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2601" + url3;
        }

        return URI;
    }


}
