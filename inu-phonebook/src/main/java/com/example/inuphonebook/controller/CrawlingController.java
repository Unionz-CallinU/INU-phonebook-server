package com.example.inuphonebook.controller;

import com.example.inuphonebook.dto.ResponseDto;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.INUCrawlingService;
import com.example.inuphonebook.service.departmentCrawling.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final GlobalLandscapeDepartmentService globalLandscapeDepartmentService;
    private final EngineeringDepartmentService engineeringDepartmentService;
    private final InformationTechnologyDepartmentService informationTechnologyDepartmentService;
    private final AdministrationDepartmentService administrationDepartmentService;
    private final ArtSportDepartmentService artSportDepartmentService;
    private final EducationDepartmentService educationDepartmentService;
    private final UrbanScienceDepartmentService urbanScienceDepartmentService;
    private final LifeScienceDepartmentService lifeScienceDepartmentService;
    private final NortheastAsiaDepartmentService northeastAsiaDepartmentService;
    private final LawDepartmentService lawDepartmentService;

    private static final String SUCCESS_CRAWLING_MESSAGE = "crawling 성공";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0 0 1 1/2 ?")
    @GetMapping("/inuhomepage")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> savePhonebook() throws IOException, InterruptedException {
        inuCrawlingService.getCrawlingDatas();
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }


    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/humanity")
    public ResponseEntity<?> saveImageURI_humanity() throws IOException{
        humanityDepartmentService.getCrawling("korean",employeeRepository);
        humanityDepartmentService.getCrawling("english",employeeRepository);
        humanityDepartmentService.getCrawling("german",employeeRepository);
        humanityDepartmentService.getCrawling("uifrance",employeeRepository);
        humanityDepartmentService.getCrawling("uijapan",employeeRepository);
        humanityDepartmentService.getCrawling("uichina",employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/naturalScience")
    public ResponseEntity<?> saveImageURI_naturalScience() throws IOException{
        naturalScienceDepartmentService.getCrawling("math", employeeRepository);
        naturalScienceDepartmentService.getCrawling("physics", employeeRepository);
        naturalScienceDepartmentService.getCrawling("chem", employeeRepository);
        naturalScienceDepartmentService.getCrawling("uifashion", employeeRepository);
        naturalScienceDepartmentService.getCrawling("marine", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/socialScience")
    public ResponseEntity<?> saveImageURI_socialScience() throws IOException{
        socialScienceDepartmentService.getCrawling("socialwelfare", employeeRepository);
        socialScienceDepartmentService.getIframe("mediaCommunication", employeeRepository);
        socialScienceDepartmentService.getCrawling("lis", employeeRepository);
        socialScienceDepartmentService.getCrawling("hrd", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }


    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/globalLandscape")
    public ResponseEntity<?> saveImageURI_globalLandscape() throws IOException {
        globalLandscapeDepartmentService.getCrawling("uipa", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("politics", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("econ", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("trade", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("ccs", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/engineering")
    public ResponseEntity<?> saveImageURI_engineering() throws IOException {
        engineeringDepartmentService.getCrawling_Mechanical("me", employeeRepository);
        engineeringDepartmentService.getCrawling("meca", employeeRepository);
        engineeringDepartmentService.getCrawling("elec", employeeRepository);
        engineeringDepartmentService.getCrawling("ee", employeeRepository);
        engineeringDepartmentService.getCrawling("ime", employeeRepository);
        engineeringDepartmentService.getCrawling("mse", employeeRepository);
        engineeringDepartmentService.getCrawling("safety", employeeRepository);
        engineeringDepartmentService.getCrawling("echeme", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }
    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/informationTechnology")
    public ResponseEntity<?> saveImageURI_informationTechnology() throws IOException {
        informationTechnologyDepartmentService.getCrawling("computer", employeeRepository);
        informationTechnologyDepartmentService.getCrawling("communication", employeeRepository);
        informationTechnologyDepartmentService.getCrawlingEmbeddedMore("ese", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/administration")
    public ResponseEntity<?> saveImageURI_administration() throws IOException {
        administrationDepartmentService.getCrawling("management", employeeRepository);
        administrationDepartmentService.getCrawling("tax", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/artSport")
    public ResponseEntity<?> saveImageURI_artSport() throws IOException {
        artSportDepartmentService.getCrawling("finearts", employeeRepository);
        artSportDepartmentService.getCrawling_design("design", employeeRepository);
        artSportDepartmentService.getCrawling("uipa10", employeeRepository);
        artSportDepartmentService.getCrawling_sports("sports", employeeRepository);
        artSportDepartmentService.getCrawling("hlkn", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/education")
    public ResponseEntity<?> saveImageURI_education() throws IOException {
        educationDepartmentService.getCrawling("edukorean", employeeRepository);
        educationDepartmentService.getCrawling("eduenglish", employeeRepository);
        educationDepartmentService.getCrawling("edujapanese", employeeRepository);
        educationDepartmentService.getCrawling("edumath", employeeRepository);
        educationDepartmentService.getCrawling("eduphysical", employeeRepository);
        educationDepartmentService.getCrawling("ece", employeeRepository);
        educationDepartmentService.getCrawling("eduhistory", employeeRepository);
        educationDepartmentService.getCrawling("eduethics", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/urban")
    public ResponseEntity<?> saveImageURI_urban() throws IOException {
        urbanScienceDepartmentService.getCrawling("urban", employeeRepository);
        urbanScienceDepartmentService.getCrawling("civil", employeeRepository);
        urbanScienceDepartmentService.getCrawling("ucv", employeeRepository);
        urbanScienceDepartmentService.getCrawling("archi", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/life")
    public ResponseEntity<?> saveImageURI_life() throws IOException {
        lifeScienceDepartmentService.getCrawling("life", employeeRepository);
        lifeScienceDepartmentService.getCrawling("bioeng", employeeRepository);
        lifeScienceDepartmentService.getCrawling("nanobio", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/northeastAsia")
    public ResponseEntity<?> saveImageURI_northeastAsia() throws IOException {
        northeastAsiaDepartmentService.getCrawling("sonas", employeeRepository);
        log.debug("크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "0 0 1 1 1/2 ?")
    @GetMapping("/law")
    public ResponseEntity<?> saveImageURI_law() throws IOException {
        lawDepartmentService.getCrawling("law", employeeRepository);
        log.debug("법학과 크롤링 실행");
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

}
