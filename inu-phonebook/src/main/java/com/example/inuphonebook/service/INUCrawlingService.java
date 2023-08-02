package com.example.inuphonebook.service;

import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class INUCrawlingService {
    private final EmployeeRepository employeeRepository;
    private static String url = "https://www.inu.ac.kr/cop/haksaStaffSearch/staffSearchView.do?id=inu_011001000000&section=all&select1=&name=";

    public void getCrawlingDatas() throws IOException {
        List<Employee> employee = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div[class=\"tbList mgt20\"]");

        for (Element content : elements.select("tr")
        ) {
            Elements select = content.select("td");
            if (select.size() != 0) {
                Employee member = Employee.builder()
                        .college(select.get(0).text())
                        .department(select.get(1).text())
                        .position(select.get(2).text())
                        .name(select.get(3).text())
                        .role(select.get(4).text())
                        .phoneNumber(select.get(5).text())
                        .email(select.get(6).text())
                        .build();
                System.out.println(member.getRole());
                employee.add(member);
            }
        }
        employeeRepository.saveAll(employee);
        log.info("[INU homepage] crawling 성공");
    }
}
