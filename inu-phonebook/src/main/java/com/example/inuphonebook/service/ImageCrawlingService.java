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
//        System.out.println("테스트 : document" + document);
        Elements elements = document.select("ul[class=\"prof_list\"]");
//        System.out.println("테스트 : elements" + elements);

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

    default void getCrawlingStartsWithInu(String departmentType, EmployeeRepository employeeRepository) throws IOException {

        String URI = checkDepartmentType(departmentType);

        Document document = Jsoup.connect(URI).get();
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


}
