package com.example.inuphonebook.common.aop;

import com.example.inuphonebook.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Component
@Aspect
public class CustomTruncateAdvice {

    private final EmployeeRepository employeeRepository;
    private final EntityManager em;

    // .. : 메서드의 매개변수 개수와 타입에 상관없이 해당 메서드를 가리킴
    @Pointcut("execution(* com.example.inuphonebook.service.INUCrawlingService.getCrawlingDatas(..))")
    public void employeeCrawlingExecution() {}

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
}
