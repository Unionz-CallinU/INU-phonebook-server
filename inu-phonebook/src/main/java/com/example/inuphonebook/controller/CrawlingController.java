package com.example.inuphonebook.controller;

import com.example.inuphonebook.common.domain.Message;
import com.example.inuphonebook.service.INUCrawlingService;
import com.example.inuphonebook.service.departmentCrawling.HumanityDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final HumanityDepartmentService humanityDepartmentService;

    private static final String SUCCESS_CRAWLING_MESSAGE = "crawling 성공";

    @GetMapping("/inuhomepage")
    @ResponseStatus(HttpStatus.OK)
    public Message savePhonebook() throws IOException {
        inuCrawlingService.getCrawlingDatas();
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }

    @GetMapping("/humanity")
    @ResponseStatus(HttpStatus.OK)
    public Message saveImageURI() throws IOException {
        humanityDepartmentService.getCrawlingData("uichina");
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }

}
