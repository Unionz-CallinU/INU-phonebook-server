package com.example.inuphonebook.repository;

import com.example.inuphonebook.domain.Employee;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Employee> findEmployeeList(String search) {
        String sql = "";
        sql += "select e from Employee e ";
        sql += "where e.name like :search ";
        sql += "or ";
        sql += "e.phoneNumber like :search ";
        sql += "or ";
        sql += "e.college like :search ";
        sql += "or ";
        sql += "e.department like :search ";

        TypedQuery<Employee> query = em.createQuery(sql, Employee.class);
        query.setParameter("search", "%" + search + "%");
        return query.getResultList();
    }
}
