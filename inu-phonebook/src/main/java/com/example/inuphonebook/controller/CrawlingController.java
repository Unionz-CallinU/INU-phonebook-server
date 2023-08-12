package com.example.inuphonebook.controller;

import com.example.inuphonebook.common.domain.Message;
import com.example.inuphonebook.dto.ResponseDto;
import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.INUCrawlingService;
//import com.example.inuphonebook.service.departmentCrawling.HumanityDepartmentService;
//import com.example.inuphonebook.service.departmentCrawling.NaturalScienceDepartmentService;
import com.example.inuphonebook.service.departmentCrawling.*;
import lombok.RequiredArgsConstructor;
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

    private static final String SUCCESS_CRAWLING_MESSAGE = "crawling 성공";

    @GetMapping("/inuhomepage")
    @ResponseStatus(HttpStatus.OK)
    public Message savePhonebook() throws IOException {
        inuCrawlingService.getCrawlingDatas();
        return new Message(SUCCESS_CRAWLING_MESSAGE);
    }


    //    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/humanity")
    public ResponseEntity<?> saveImageURI_humanity() throws IOException{
        humanityDepartmentService.getCrawling("korean",employeeRepository);
        humanityDepartmentService.getCrawling("english",employeeRepository);
        humanityDepartmentService.getCrawling("german",employeeRepository);
        humanityDepartmentService.getCrawling("uifrance",employeeRepository);
        humanityDepartmentService.getCrawling("uijapan",employeeRepository);
        humanityDepartmentService.getCrawling("uichina",employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    //    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/naturalScience")
    public ResponseEntity<?> saveImageURI_naturalScience() throws IOException{
        naturalScienceDepartmentService.getCrawling("math", employeeRepository);
        naturalScienceDepartmentService.getCrawling("physics", employeeRepository);
        naturalScienceDepartmentService.getCrawling("chem", employeeRepository);
        naturalScienceDepartmentService.getCrawling("uifashion", employeeRepository);
        naturalScienceDepartmentService.getCrawling("marine", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    //    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/socialScience")
    public ResponseEntity<?> saveImageURI_socialScience() throws IOException{
        socialScienceDepartmentService.getCrawling("socialwelfare", employeeRepository);
        socialScienceDepartmentService.getIframe("mediaCommunication", employeeRepository);
        socialScienceDepartmentService.getCrawling("lis", employeeRepository);
        socialScienceDepartmentService.getCrawling("hrd", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }


//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/globalLandscape")
    public ResponseEntity<?> saveImageURI_globalLandscape() throws IOException {
        globalLandscapeDepartmentService.getCrawling("uipa", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("politics", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("econ", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("trade", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("ccs", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

//    @Scheduled(cron = "*/10 * * * * *")
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
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }
//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/informationTechnology")
    public ResponseEntity<?> saveImageURI_informationTechnology() throws IOException {
        informationTechnologyDepartmentService.getCrawling("computer", employeeRepository);
        informationTechnologyDepartmentService.getCrawling("communication", employeeRepository);
        informationTechnologyDepartmentService.getCrawlingEmbeddedMore("ese", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/administration")
    public ResponseEntity<?> saveImageURI_administration() throws IOException {
        administrationDepartmentService.getCrawling("management", employeeRepository);
        administrationDepartmentService.getCrawling("tax", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

//    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/artSport")
    public ResponseEntity<?> saveImageURI_artSport() throws IOException {
        artSportDepartmentService.getCrawling("finearts", employeeRepository);
        artSportDepartmentService.getCrawling_design("design", employeeRepository);
        artSportDepartmentService.getCrawling("uipa10", employeeRepository);
        artSportDepartmentService.getCrawling_sports("sports", employeeRepository);
        artSportDepartmentService.getCrawling("hlkn", employeeRepository);
        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

    @Scheduled(cron = "*/10 * * * * *")
    @GetMapping("/education")
    public ResponseEntity<?> saveImageURI_education() throws IOException {

        return new ResponseEntity<>(new ResponseDto<>(1, SUCCESS_CRAWLING_MESSAGE, null), HttpStatus.OK);
    }

}
