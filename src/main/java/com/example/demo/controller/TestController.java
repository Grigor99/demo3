package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {
    @PersistenceContext()
    private EntityManager entityManager;

    @GetMapping
    public List getAnswer() {

        Map<Long, Long> nodeIdMap = new HashMap<>();
        List<Long> subList = new ArrayList<>();
        subList.add(55L);
        subList.add(55L);


        String ql = "SELECT n.id, n.nodeId FROM NodeBE n WHERE n.id IN :nodeIds ";


        TypedQuery<Object[]> query = entityManager.createQuery(ql, Object[].class);

        query.setParameter("nodeIds", subList);
        System.out.println( query.getResultList());


        return query.getResultList();
    }
}

