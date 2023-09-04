package com.example.inuphonebook.service;


import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface ImageCrawlingService {

    default void getCrawling(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get(); // The 'url' parameter must not be empty.
//        System.out.println("테스트 : document " + document);

        Elements imageTags = document.select("div[class=\"thumb-image\"]"); // 추가 : img 태그에서 일관적으로 가져와지지 않아서 명시적으로 수정
//        System.out.println("테스트 : imageTags " + imageTags);
        Elements emailLinks = document.select("a[href^=mailto]");
//        System.out.println("테스트 : emailLinks " + emailLinks);

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();
//
        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("style");
            // "base64," 이후의 데이터 추출
            String base64Data = srcValue.split(",")[1];
//            System.out.println("테스트 : base64data " + base64Data);

            String hrefValue = emailLinkIterator.next().attr("href");
//            System.out.println("테스트 : href " + hrefValue);
            String emailAddress = hrefValue.substring(7);

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling(base64Data);

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


    default void getCrawlingEmbeddedMore(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get();

        Elements imageTags = document.select("div[class=\"thumb-image\"]");
        Elements emailLinks = document.select("a[href^=mailto]");

        Iterator<Element> imageTagIterator = imageTags.iterator();
        Iterator<Element> emailLinkIterator = emailLinks.iterator();

        while (imageTagIterator.hasNext() && emailLinkIterator.hasNext()) {

            String srcValue = imageTagIterator.next().attr("style");
            String base64Data = srcValue.split(",")[1];

            String hrefValue = emailLinkIterator.next().attr("href");
            String emailAddress = hrefValue.substring(7);
            System.out.println("테스트 : email " + emailAddress);

            if (emailAddress.equals("hkwangil  AT inu.ac.kr")) {
                emailAddress = "hkwangil AT inu.ac.kr";
            }

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling(base64Data);

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
