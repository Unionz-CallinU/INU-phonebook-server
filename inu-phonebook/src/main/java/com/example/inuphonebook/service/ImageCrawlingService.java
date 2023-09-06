package com.example.inuphonebook.service;


import com.example.inuphonebook.common.exception.NotFoundException;
import com.example.inuphonebook.domain.Employee;
import com.example.inuphonebook.repository.EmployeeRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;

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
            // 정규 표현식 패턴 설정
            Pattern pattern = Pattern.compile(",([^)]+)");

            // 문자열에서 패턴 매칭을 수행
            Matcher matcher = pattern.matcher(srcValue);
            String base64Data = null;
            // 매칭된 부분 추출
            if (matcher.find()) {
                String result = matcher.group(1).trim();
                base64Data = result;
            }

//            // base64 디코딩
//            byte[] decodedBytes = Base64Utils.decodeFromString(base64Data);
//
//            // 디코딩된 바이트 배열을 문자열로 변환
//            String decodedString = new String(decodedBytes);
//
//            // 결과 출력
//            System.out.println("디코딩된 문자열: " + decodedString);

            String hrefValue = emailLinkIterator.next().attr("href");
//            System.out.println("테스트 : href " + hrefValue);
            String emailAddress = hrefValue.substring(7);

            Employee employeePS = employeeRepository.findByEmail(emailAddress).orElseThrow(() -> new NotFoundException("이메일이 존재하지 않습니다."));

            employeePS.setImageByCrawling(base64Data);

        }
    }

    String checkDepartmentType(String departmentType);


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



}
