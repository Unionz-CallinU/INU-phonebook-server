package com.example.inuphonebook.service.humanities;

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

import java.io.IOException;
import java.util.Iterator;

@RequiredArgsConstructor
@Service
public class KoreanDepartment implements ImageCrawlingService {

    private final EmployeeRepository employeeRepository;

    private static String url = "https://korean.inu.ac.kr/user/indexSub.do?codyMenuSeq=1774553&siteId=korean";

    @Override
    public void getCrawlingData() throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div[id=\"professor_wrap\"]");

        Elements imageTags = elements.select("img[src]");
        Elements emailLinks = elements.select("a[href^=mailto]");

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();

        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {
            String srcValue = imageTagIterator.next().attr("src");
            String hrefValue = emailLinkIterator.next().attr("href");
            String emailAddress = hrefValue.substring(7);

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("해당 이메일을 가진 직원이 존재하지 않습니다."));
            employeePS.setImageByCrawling("https://korean.inu.ac.kr/"+srcValue);
//            System.out.println("테스트 : employee" + employeePS.getName());
//            System.out.println("테스트 : employee.imageURl" + employeePS.getImageUrl());

        }
    }

}
