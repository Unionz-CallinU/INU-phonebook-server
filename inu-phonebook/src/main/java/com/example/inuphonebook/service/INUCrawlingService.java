package com.example.inuphonebook.service;

import com.example.inuphonebook.common.Util.UrlEncoder;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class INUCrawlingService {
    private final EmployeeRepository employeeRepository;

    private final String baseUrl = "https://www.inu.ac.kr/inu/742/subview.do?enc=";
    private final String pageUrl = "/staffSearch/inu/srchView.do?srchDept=&srchDeptType=all&name=%%&page=";
    public void getCrawlingDatas() throws IOException, InterruptedException {
        long page = 1;
        try {
            while (true) {
                UrlEncoder Encoder = new UrlEncoder(pageUrl + page);
                String encodingUrl = "Zm5jdDF8QEB8" + Encoder.getEncodingUrl();

                String searchUrl = baseUrl + encodingUrl;
                log.info(searchUrl);

                URL url = new URL(searchUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET"); // GET 요청 보내기

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                // 연결 종료
                conn.disconnect();

                // 데이터 처리 (파싱)
                String htmlContent = content.toString();
                Document document = Jsoup.parse(htmlContent);

                // 원하는 요소 선택 및 내용 추출
                ArrayList<Employee> employee = new ArrayList<>();

                Elements elements = document.select("div[class=\"func-table\"]");

                for (Element element : elements.select("tr")
                ) {
                    Elements select = element.select("td");
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
                        employee.add(member);
                    }
                }
                employeeRepository.saveAll(employee);
                employee.clear();
                page += 1;
            }
        } catch (IndexOutOfBoundsException e) {
            log.info("크롤링할 페이지가 더이상 존재하지 않습니다.");
        }
        log.info("[INU homepage] crawling 성공");
    }

}
