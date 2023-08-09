package com.example.inuphonebook.controller;

import com.example.inuphonebook.common.domain.Message;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.INUCrawlingService;
//import com.example.inuphonebook.service.departmentCrawling.HumanityDepartmentService;
//import com.example.inuphonebook.service.departmentCrawling.NaturalScienceDepartmentService;
import com.example.inuphonebook.service.departmentCrawling.HumanityDepartmentService;
import com.example.inuphonebook.service.departmentCrawling.NaturalScienceDepartmentService;
import com.example.inuphonebook.service.departmentCrawling.SocialScienceDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crawling")
public class CrawlingController {
    private final INUCrawlingService inuCrawlingService;
    private final EmployeeRepository employeeRepository;

    private final HumanityDepartmentService humanityDepartmentService;
    private final NaturalScienceDepartmentService naturalScienceDepartmentService;
    private final SocialScienceDepartmentService socialScienceDepartmentService;

    private static final String SUCCESS_CRAWLING_MESSAGE = "crawling 성공";

    @GetMapping("/inuhomepage")
    @ResponseStatus(HttpStatus.OK)
    public Message savePhonebook() throws IOException {
        inuCrawlingService.getCrawlingDatas();
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }

//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/humanity")
    @ResponseStatus(HttpStatus.OK)
    public Message saveImageURI() throws IOException {
        humanityDepartmentService.getCrawling("korean",employeeRepository);
        humanityDepartmentService.getCrawling("english",employeeRepository);
        humanityDepartmentService.getCrawling("german",employeeRepository);
        humanityDepartmentService.getCrawling("uifrance",employeeRepository);
        humanityDepartmentService.getCrawling("uijapan",employeeRepository);
        humanityDepartmentService.getCrawling("uichina",employeeRepository);
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }
//
//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/naturalScience")
    @ResponseStatus(HttpStatus.OK)
    public Message saveImageURI2() throws IOException {
        naturalScienceDepartmentService.getCrawling("math", employeeRepository);
        naturalScienceDepartmentService.getCrawling("physics", employeeRepository);
        naturalScienceDepartmentService.getCrawling("chem", employeeRepository);
        naturalScienceDepartmentService.getCrawling("uifashion", employeeRepository);
        naturalScienceDepartmentService.getCrawling("marine", employeeRepository);

        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }
    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/socialScience")
    @ResponseStatus(HttpStatus.OK)
    public Message saveImageURI3() throws IOException {
//        socialScienceDepartmentService.getCrawling("socialwelfare", employeeRepository);
        socialScienceDepartmentService.getIframe("mediaCommunication", employeeRepository);
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }

}
