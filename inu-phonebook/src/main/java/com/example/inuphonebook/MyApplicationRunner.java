package com.example.inuphonebook;

import com.example.inuphonebook.repository.EmployeeRepository;
import com.example.inuphonebook.service.INUCrawlingService;
import com.example.inuphonebook.service.departmentCrawling.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyApplicationRunner implements ApplicationRunner {

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
//    private final NortheastAsiaDepartmentService northeastAsiaDepartmentService;
    private final LawDepartmentService lawDepartmentService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("[INU] 직원정보 크롤링 시작");
        long employeeStartTime = System.currentTimeMillis();
        inuCrawlingService.getCrawlingDatas();
        long employeeEndTime = System.currentTimeMillis();
        long endEmployeeTime = employeeEndTime - employeeStartTime;
        log.info("[INU] 직원정보 크롤링 완료시간 : " + endEmployeeTime);

        log.info("이미지 크롤링 시작");
        long imgStartTime = System.currentTimeMillis();
        humanityDepartmentService.getCrawling("korean",employeeRepository);
        humanityDepartmentService.getCrawling("english",employeeRepository);
        humanityDepartmentService.getCrawling("german",employeeRepository);
        humanityDepartmentService.getCrawling("inufrance",employeeRepository);
        humanityDepartmentService.getCrawling("unjapan",employeeRepository);
        humanityDepartmentService.getCrawling("inuchina",employeeRepository);
        log.info("[인문대]이미지 크롤링 종료");

        naturalScienceDepartmentService.getCrawling("math", employeeRepository);
        naturalScienceDepartmentService.getCrawling("physics", employeeRepository);
        naturalScienceDepartmentService.getCrawling("chem", employeeRepository);
        naturalScienceDepartmentService.getCrawling("uifashion", employeeRepository);
        naturalScienceDepartmentService.getCrawling("marine", employeeRepository);
        log.info("[자연과학대]이미지 크롤링 종료");

        socialScienceDepartmentService.getCrawling("dsw", employeeRepository);
        socialScienceDepartmentService.getCrawling("newdays", employeeRepository);
        socialScienceDepartmentService.getCrawling("cls", employeeRepository);
        socialScienceDepartmentService.getCrawling("hrd", employeeRepository);
        log.info("[사회과학대]이미지 크롤링 종료");

        globalLandscapeDepartmentService.getCrawling("uipa", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("politics", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("econ", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("trade", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("ccs", employeeRepository);
        log.info("[글로벌정경대]이미지 크롤링 종료");

        engineeringDepartmentService.getCrawling("me", employeeRepository);
        engineeringDepartmentService.getCrawling("meca", employeeRepository);
        engineeringDepartmentService.getCrawling("elec", employeeRepository);
        engineeringDepartmentService.getCrawling("ee", employeeRepository);
        engineeringDepartmentService.getCrawling("ime", employeeRepository);
        engineeringDepartmentService.getCrawling("mse", employeeRepository);
        engineeringDepartmentService.getCrawling("safety", employeeRepository);
        engineeringDepartmentService.getCrawling("energy", employeeRepository);
        log.info("[공과대]이미지 크롤링 종료");

        informationTechnologyDepartmentService.getCrawling("cse", employeeRepository);
        informationTechnologyDepartmentService.getCrawling("ite", employeeRepository);
        informationTechnologyDepartmentService.getCrawlingEmbeddedMore("ese", employeeRepository);
        log.info("[정보기술대]이미지 크롤링 종료");

        administrationDepartmentService.getCrawling("biz", employeeRepository);
        administrationDepartmentService.getCrawling("tax", employeeRepository);
        log.info("[경영대]이미지 크롤링 종료");

        artSportDepartmentService.getCrawling("finearts", employeeRepository);
        artSportDepartmentService.getCrawling("design", employeeRepository);
        artSportDepartmentService.getCrawling("uipa10", employeeRepository);
        artSportDepartmentService.getCrawling_sports("sports", employeeRepository);
        artSportDepartmentService.getCrawling("uiex", employeeRepository);
        log.info("[예체대] 이미지 크롤링 종료");


        educationDepartmentService.getCrawling("edukorean", employeeRepository);
        educationDepartmentService.getCrawling("eduenglish", employeeRepository);
        educationDepartmentService.getCrawling("edujapanese", employeeRepository);
        educationDepartmentService.getCrawling("mathedu", employeeRepository);
        educationDepartmentService.getCrawling("eduphysical", employeeRepository);
        educationDepartmentService.getCrawling("ece", employeeRepository);
        educationDepartmentService.getCrawling("eduhistory", employeeRepository);
        educationDepartmentService.getCrawling("eduethics", employeeRepository);
        log.info("[교육대]이미지 크롤링 종료");

        urbanScienceDepartmentService.getCrawling("urban", employeeRepository);
        urbanScienceDepartmentService.getCrawling("civil", employeeRepository);
        urbanScienceDepartmentService.getCrawling("et", employeeRepository);
        urbanScienceDepartmentService.getCrawling("scity", employeeRepository);
        urbanScienceDepartmentService.getCrawling("archi", employeeRepository);
        log.info("[도시과학대]이미지 크롤링 종료");

        lifeScienceDepartmentService.getCrawling("life", employeeRepository);
        lifeScienceDepartmentService.getCrawling("molbio", employeeRepository);
        lifeScienceDepartmentService.getCrawling("nanobio", employeeRepository);
        log.info("[생명과학대]이미지 크롤링 종료");

//        northeastAsiaDepartmentService.getCrawling("sonas", employeeRepository);
        log.info("[동북아]이미지 크롤링 종료");

        lawDepartmentService.getCrawling("law", employeeRepository);
        log.info("[법대]이미지 크롤링 종료");

        long imgEndTime = System.currentTimeMillis();
        log.debug("이미지 크롤링 종료 : " + imgEndTime);

    }
}