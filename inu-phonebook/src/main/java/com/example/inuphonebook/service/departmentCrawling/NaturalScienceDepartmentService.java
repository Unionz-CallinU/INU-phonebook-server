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
            URI = url + departmentType + url2 + "=1777697&siteId=" + siteId;
        } else if (departmentType == "physics") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1775746&siteId=" + siteId;
        } else if (departmentType == "chem") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1777737&siteId=" + siteId;
        } else if (departmentType == "uifashion") {
            siteId = "uipashion";
            URI = url + departmentType + url2 + "=1777712&siteId=" + siteId;
        } else if (departmentType == "marine") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1777732&siteId=" + siteId;
        }
        return URI;
    }

}
