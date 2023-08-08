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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;

@RequiredArgsConstructor
@Transactional
@Service
public class HumanityDepartmentService implements ImageCrawlingService {

    private final EmployeeRepository employeeRepository;
    private static String url = "https://";
    private static String url2 = ".inu.ac.kr/user/indexSub.do?codyMenuSeq";

    // e.g. https://korean.inu.ac.kr/user/indexSub.do?codyMenuSeq=1774553&siteId=korean
    // department 가 korean, siteId 도 같은 경우가 있는 방면

    // e.g. https://math.inu.ac.kr/user/indexSub.do?codyMenuSeq=1777697&siteId=isu
    // department 가 math, siteId 가 isu 로 다른경우가 있음
    @Override
    public void getCrawlingData(String departmentType) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get();
        Elements elements = document.select("div[id=\"professor_wrap\"]");

//        System.out.println("테스트 : elements " + elements);

        Elements imageTags = elements.select("p.pro_img img"); // 추가 : img 태그에서 일관적으로 가져와지지 않아서 명시적으로 수정
        Elements emailLinks = elements.select("a[href^=mailto]");
        Elements nameElements = document.select("li:has(strong img[src='/Web-home/wizUI/imgUI/professor/list_title_01.gif'][alt='이름'])");

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();
        Iterator<Element> nameIterator = nameElements.iterator();

        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("src");
            String hrefValue = emailLinkIterator.next().attr("href");
            String emailAddress = hrefValue.substring(7);

            String name = nameIterator.next().ownText();
//            System.out.println("테스트 : src " + srcValue);
//            System.out.println("테스트 : name " + name);
//            System.out.println("테스트 : email " + emailAddress);

            // 추가 : 학교사이트 와 학과사이트 정보가 맞지 않아서 학교사이트 정보로 업데이트
            if (name.equals("이용화") && emailAddress.equals("ylee@inu.ac.kr")) {
                emailAddress = "ylee@incheon.ac.kr";
            }
            if (name.equals("남상욱") && emailAddress.equals("indimina@inu.ac.kr")) {
                emailAddress = "indimina@incheon.ac.kr";
            }

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            // 수정 : 오타, .이 빠졌음
            employeePS.setImageByCrawling("https://"+departmentType+".inu.ac.kr" + srcValue);

        }
    }

    private String checkDepartmentType(String departmentType) {

        String siteId;
        String URI = null;
        if (departmentType == "korean") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=1774553&siteId=" + siteId;
        } else if(departmentType == "english") {
            siteId = "ui";
            URI = url + departmentType + url2 + "=85166&siteId=" + siteId;
        } else if(departmentType == "german") {
            siteId = departmentType;
            URI = url + departmentType + url2 + "=229036&siteId=" + siteId;
        } else if (departmentType == "uifrance") {
            siteId = "inufrance";
            URI = url + departmentType + url2 + "=465824&siteId=" + siteId;
        } else if (departmentType == "uijapan") {
            siteId = "unjapan";
            URI = url + departmentType + url2 + "=1795743&siteId=" + siteId;
        } else if (departmentType == "uichina") {
            siteId = "inuchina";
            URI = url + departmentType + url2 + "=2305900&siteId=" + siteId;
        }

        return URI;
    }
}
