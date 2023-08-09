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
public class SocialScienceDepartmentService implements ImageCrawlingService {

    public static final String url = Constants.url;
    public static final String url2 = Constants.url2;

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
        if (departmentType == "socialwelfare") {
            siteId = "dsw";
            URI = url + departmentType + url2 + "=1779852&siteId=" + siteId;
        } else if (departmentType == "mediaCommunication") {
            URI = url + "www.inu.ac.kr/newdays/sub/introduce/professor.do";
        }

        return URI;
    }


}
