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
    @Value("${location.url3}")
    private String url3;


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
        if (departmentType == "cse") {
            siteId = "isis";
            URI = url + departmentType + url2 + siteId + "/3547" + url3;
        } else if (departmentType == "ite") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3484" + url3;
        } else if (departmentType == "ese") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/3419" + url3;
        }

        return URI;
    }
}
