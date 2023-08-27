package com.example.inuphonebook.common.Util;

import com.example.inuphonebook.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverUtil {

    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.gecko.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "/Users/eunmi/driver/geckodriver"; // WebDriver 경로

    public WebDriverUtil() {
        chrome();
    }

    private void chrome() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // webDriver 옵션 설정.
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");          // 최대크기로
        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

        // WebDriver 객체 생성
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public List<Employee> useDriver(String url) throws InterruptedException {
        driver.get(url) ;
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.

        List<Employee> employees = new ArrayList<>();
        long page = 1;

        try {
            while(true) {
                log.info("Crawling page" + page);
                WebElement tableElement = driver.findElement(By.className("func-table"));

                List<WebElement> elements = tableElement.findElements(By.tagName("tr"));

                // 원하는 크롤링 작업 수행
                for (WebElement row : elements) {
                    List<WebElement> select = row.findElements(By.tagName("td"));
                    if (select.size() != 0) {
                        Employee member = Employee.builder()
                                .college(select.get(0).getText())
                                .department(select.get(1).getText())
                                .position(select.get(2).getText())
                                .name(select.get(3).getText())
                                .role(select.get(4).getText())
                                .phoneNumber(select.get(5).getText())
                                .email(select.get(6).getText())
                                .build();

                        employees.add(member);
                    }
                }

                // 다음 페이지로 이동
                WebElement paging = driver.findElement(By.className("_paging"));
                WebElement next_page = paging.findElement(By.linkText("다음 페이지"));
                if (next_page == null) {
                    break;
                }
                next_page.click();
                page ++;
                Thread.sleep(1);
            }
        } catch (NoSuchElementException e) {
                log.info("다음페이지가 존재하지 않습니다.");
        }

        log.info("Success Crawling INU homepage");


        quitDriver();
        return employees;
    }

    private void quitDriver() {
        driver.quit(); // webDriver 종료
    }

}
