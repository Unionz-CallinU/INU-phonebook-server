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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;

@RequiredArgsConstructor
@Transactional
@Service
public class NaturalScienceDepartmentService implements ImageCrawlingService {

    private final EmployeeRepository employeeRepository;
    private static String url = "https://";
    private static String url2 = ".inu.ac.kr/user/indexSub.do?codyMenuSeq";

    @Override
    public void getCrawlingData(String departmentType) throws IOException {
        System.out.println("테스트 : cron 실행");
        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get();
        Elements elements = document.select("div[id=\"professor_wrap\"]");

        Elements imageTags = elements.select("p.pro_img img"); // 추가 : img 태그에서 일관적으로 가져와지지 않아서 명시적으로 수정
        Elements emailLinks = elements.select("a[href^=mailto]");

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();

        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("src");
            String hrefValue = emailLinkIterator.next().attr("href");
            String emailAddress = hrefValue.substring(7);

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling("https://"+departmentType+".inu.ac.kr" + srcValue);

        }
    }

    private String checkDepartmentType(String departmentType) {
        String siteId;
        String URI = null;
        if (departmentType == "math") {
            siteId = "isu";
            URI = url + departmentType + url2 + "=1777697&siteId=" + siteId;
        }

        return URI;
    }
}
