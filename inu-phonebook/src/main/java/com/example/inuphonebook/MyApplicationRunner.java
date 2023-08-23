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
    private final NortheastAsiaDepartmentService northeastAsiaDepartmentService;
    private final LawDepartmentService lawDepartmentService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long startTime = System.currentTimeMillis();
        inuCrawlingService.getCrawlingDatas();
        humanityDepartmentService.getCrawling("korean",employeeRepository);
        humanityDepartmentService.getCrawling("english",employeeRepository);
        humanityDepartmentService.getCrawling("german",employeeRepository);
        humanityDepartmentService.getCrawling("uifrance",employeeRepository);
        humanityDepartmentService.getCrawling("uijapan",employeeRepository);
        humanityDepartmentService.getCrawling("uichina",employeeRepository);

        naturalScienceDepartmentService.getCrawling("math", employeeRepository);
        naturalScienceDepartmentService.getCrawling("physics", employeeRepository);
        naturalScienceDepartmentService.getCrawling("chem", employeeRepository);
        naturalScienceDepartmentService.getCrawling("uifashion", employeeRepository);
        naturalScienceDepartmentService.getCrawling("marine", employeeRepository);

        socialScienceDepartmentService.getCrawling("socialwelfare", employeeRepository);
        socialScienceDepartmentService.getIframe("mediaCommunication", employeeRepository);
        socialScienceDepartmentService.getCrawling("lis", employeeRepository);
        socialScienceDepartmentService.getCrawling("hrd", employeeRepository);

        globalLandscapeDepartmentService.getCrawling("uipa", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("politics", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("econ", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("trade", employeeRepository);
        globalLandscapeDepartmentService.getCrawling("ccs", employeeRepository);

        engineeringDepartmentService.getCrawling_Mechanical("me", employeeRepository);
        engineeringDepartmentService.getCrawling("meca", employeeRepository);
        engineeringDepartmentService.getCrawling("elec", employeeRepository);
        engineeringDepartmentService.getCrawling("ee", employeeRepository);
        engineeringDepartmentService.getCrawling("ime", employeeRepository);
        engineeringDepartmentService.getCrawling("mse", employeeRepository);
        engineeringDepartmentService.getCrawling("safety", employeeRepository);
        engineeringDepartmentService.getCrawling("echeme", employeeRepository);

        informationTechnologyDepartmentService.getCrawling("computer", employeeRepository);
        informationTechnologyDepartmentService.getCrawling("communication", employeeRepository);
        informationTechnologyDepartmentService.getCrawlingEmbeddedMore("ese", employeeRepository);

        administrationDepartmentService.getCrawling("management", employeeRepository);
        administrationDepartmentService.getCrawling("tax", employeeRepository);

        artSportDepartmentService.getCrawling("finearts", employeeRepository);
        artSportDepartmentService.getCrawling_design("design", employeeRepository);
        artSportDepartmentService.getCrawling("uipa10", employeeRepository);
        artSportDepartmentService.getCrawling_sports("sports", employeeRepository);
        artSportDepartmentService.getCrawling("hlkn", employeeRepository);

        educationDepartmentService.getCrawling("edukorean", employeeRepository);
        educationDepartmentService.getCrawling("eduenglish", employeeRepository);
        educationDepartmentService.getCrawling("edujapanese", employeeRepository);
        educationDepartmentService.getCrawling("edumath", employeeRepository);
        educationDepartmentService.getCrawling("eduphysical", employeeRepository);
        educationDepartmentService.getCrawling("ece", employeeRepository);
        educationDepartmentService.getCrawling("eduhistory", employeeRepository);
        educationDepartmentService.getCrawling("eduethics", employeeRepository);

        urbanScienceDepartmentService.getCrawling("urban", employeeRepository);
        urbanScienceDepartmentService.getCrawling("civil", employeeRepository);
        urbanScienceDepartmentService.getCrawling("ucv", employeeRepository);
        urbanScienceDepartmentService.getCrawling("archi", employeeRepository);

        lifeScienceDepartmentService.getCrawling("life", employeeRepository);
        lifeScienceDepartmentService.getCrawling("bioeng", employeeRepository);
        lifeScienceDepartmentService.getCrawling("nanobio", employeeRepository);

        northeastAsiaDepartmentService.getCrawling("sonas", employeeRepository);

        lawDepartmentService.getCrawling("law", employeeRepository);
        log.debug("크롤링 실행");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.debug("Code execution time: " + executionTime + " ms");



    }
}
