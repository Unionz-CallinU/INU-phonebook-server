//package com.example.inuphonebook.service;
//
//import com.example.inuphonebook.common.Util.WebDriverUtil;
//import com.example.inuphonebook.domain.Employee;
//import com.example.inuphonebook.repository.EmployeeRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//class INUCrawlingServiceTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    private static final String url = "https://www.inu.ac.kr/inu/742/subview.do?enc=Zm5jdDF8QEB8JTJGc3RhZmZTZWFyY2glMkZpbnUlMkZzcmNoVmlldy5kbyUzRnNyY2hEZXB0VHlwZSUzRGFsbCUyNnNyY2hEZXB0JTNEJTI2bmFtZSUzRCUyNSUyNSUyNg%3D%3D";
//    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
//    public static final String WEB_DRIVER_PATH = "/Users/eunmi/Desktop/chromedriver-mac-arm64/chromedriver"; //드라이버 경로
//
//
//    @Test
//    void getCrawlingDatas() throws InterruptedException {
//
//        List<Employee> employee = new ArrayList<>();
//
//        WebDriverUtil webDriverUtil = new WebDriverUtil();
//
//        List<Employee> employees = webDriverUtil.useDriver(url);
//        employeeRepository.saveAll(employees);
//
//    }
//}