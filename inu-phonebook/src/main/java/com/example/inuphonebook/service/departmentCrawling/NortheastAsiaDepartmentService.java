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
public class NortheastAsiaDepartmentService implements ImageCrawlingService {

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
        if (departmentType == "sonas") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1787884&siteId=" + siteId;
        }
        return URI;
    }
}
