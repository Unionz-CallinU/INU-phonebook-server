package com.example.inuphonebook.service.departmentCrawling;

import com.example.inuphonebook.common.Constants;
import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.ImageCrawlingService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;

@RequiredArgsConstructor
@Transactional
@Service
public class NaturalScienceDepartmentService implements ImageCrawlingService {

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
