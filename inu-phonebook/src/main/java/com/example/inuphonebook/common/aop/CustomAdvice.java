package com.example.inuphonebook.common.aop;

import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Component
@Aspect
public class CustomAdvice {

    private final EmployeeRepository employeeRepository;
    private final EntityManager em;
    private final Logger log = LoggerFactory.getLogger(getClass());

    // .. : 메서드의 매개변수 개수와 타입에 상관없이 해당 메서드를 가리킴
    @Pointcut("execution(* com.example.inuphonebook.service.INUCrawlingService.getCrawlingDatas(..))")
    public void employeeCrawlingExecution() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {

    }

    @Before("employeeCrawlingExecution()")
    public void beforeExecution() {
        employeeRepository.deleteAll();
        afterTruncate();
    }

    public void afterTruncate() {
        String sql = "ALTER TABLE employee AUTO_INCREMENT = 1";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Around("getMapping()")
    Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        log.debug("실행시간 : " + executionTime + "ms");
        return result;
    }

}
