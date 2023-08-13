package com.example.inuphonebook.service;


import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface ImageCrawlingService {

    default void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
//        System.out.println("테스트 : document" + document);
        Elements elements = document.select("div[id=\"professor_wrap\"]");
//        System.out.println("테스트 : elements" + elements);

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

    String checkDepartmentType(String departmentType);

    default void getIframe(String departmentType, EmployeeRepository employeeRepository) throws IOException{
        String URI = checkDepartmentType(departmentType);
        Document document = Jsoup.connect(URI).get();
        String iframeSrc = document.select("#professor iframe").attr("src");
        Document iframeDocument = Jsoup.connect("https://www.inu.ac.kr"+iframeSrc).get();

        Elements elements = iframeDocument.select("div[id=\"professor_wrap\"]");

        Elements imageTags = elements.select("p.pro_img img");
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

    default void getCrawling_Mechanical(String departmentType, EmployeeRepository employeeRepository) throws IOException {
        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
        Elements elements = document.select("ul[class=\"prof_list\"]");

        Elements imageTags = elements.select("img[src]"); // 추가 : img 태그에서 일관적으로 가져와지지 않아서 명시적으로 수정
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

    default void getCrawlingEmbeddedMore(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
        Elements elements = document.select("ul[class=\"gallery\"]");
        Elements professorInfoSite = elements.select("a[href^=prfList]");

        Iterator<Element> siteIterator = professorInfoSite.iterator();

        while (siteIterator.hasNext()) {
            String hrefValue = siteIterator.next().attr("href");

            String prfId = extractParamValue(hrefValue, "prfId");
            String prfSeq = extractParamValue(hrefValue, "prfSeq");

            Document detailPage = Jsoup.connect(URI + "&dum=dum&prfId=" + prfId + "&page=1&command=view&prfSeq=" + prfSeq + "&search=&column=").get();
            Elements select = detailPage.select("div[id=\"content-container\"]");

            Elements select1 = select.select("dd:contains(이메일)");
            String email = select1.select("span[class=\"cont\"]").text();
            String formatEmail = formatEmail(email);

            Employee employeePS = employeeRepository.findByEmail(formatEmail).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            Elements selectImage = select.select("div[class=\"thumb\"]");
            Elements imageTags = selectImage.select("img[src]");
            Iterator<Element> imageIterator = imageTags.iterator();

            while (imageIterator.hasNext()) {
                String srcValue = imageIterator.next().attr("src");
                employeePS.setImageByCrawling("https://"+departmentType+".inu.ac.kr" + srcValue);

            }


        }

    }

    default void getCrawling_design(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
        Elements elements = document.select("div[class=\"page-content\"]");
//        System.out.println("테스트 : elements " + elements);
        Elements imageTags = elements.select("img[src^=https]");
        Elements emailLinks = elements.select("a[href^=mailto]");

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();

        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("src");
            String hrefValue = emailLinkIterator.next().attr("href");
            String emailAddress = hrefValue.substring(7);

            if (emailAddress.equals("hhsong3d@inu.ac.kr")) {
                emailAddress = "hhsong3d@incheon.ac.kr";
            }

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling(srcValue);

        }

    }

    default void getCrawling_sports(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
        Elements elements = document.select("div[class=\"prof_wrap\"]");
        Elements imageTags = elements.select("img[src^=http]");
        Elements elements1 = elements.select("dl:has(i.fa-envelope)");
        Elements emailLinks = elements1.select("dd");


        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();

        while (imageTagIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("src");
            String emailAddress = emailLinkIterator.next().text();
            System.out.println("테스트 : email " + emailAddress);
            if (emailAddress.equals("jbhong@inu.ac.kr")) {
                emailAddress = "jbhong@incheon.ac.kr";
            }
            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling(srcValue);

        }

    }



    static String extractParamValue(String hrefValue, String paramName) {
        // extract prfId, prfSeq
        Pattern pattern = Pattern.compile("(?:[?&]" + paramName + "=)([^&]+)");
        Matcher matcher = pattern.matcher(hrefValue);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    static String formatEmail(String input) {
        // Replace " at " with "@"
        String formatted = input.replaceAll("\\s*at\\s*", "@");
        return formatted;
    }


}
