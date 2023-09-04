package com.example.inuphonebook.service.departmentCrawling;


import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.ImageCrawlingService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;

@RequiredArgsConstructor
@Transactional
@Service
public class HumanityDepartmentService implements ImageCrawlingService {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    public String checkDepartmentType(String departmentType) {

        String siteId;
        String URI = null;
        if (departmentType == "korean") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/1783" + url3;
        } else if(departmentType == "english") {
            siteId = "ui";
            URI = url + departmentType + url2 + siteId + "/1985" + url3;
        } else if(departmentType == "german") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/1853" + url3;
        } else if (departmentType == "inufrance") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/1927" + url3;
        } else if (departmentType == "unjapan") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2048" + url3;
        } else if (departmentType == "inuchina") {
            siteId = departmentType;
            URI = url + departmentType + url2 + siteId + "/2109" + url3;
        }
            return URI;
        }
}
