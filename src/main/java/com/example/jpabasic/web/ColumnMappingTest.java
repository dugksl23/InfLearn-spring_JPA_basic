package com.example.jpabasic.web;

import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


@Slf4j
public class ColumnMappingTest extends JpaTransactionManager {

    static JpaTransactionManager transactionManager = new JpaTransactionManager();

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // transaction 시작
        transaction.begin();
        Member member = Member.builder().username("dd1").age(10).role(Role.MANAGER).description("관리자임").build();
        Member member2 = Member.builder().username("dd2").age(102).role(Role.MANAGER).description("관리자임1").build();
        Member member4 = Member.builder().username("dd3").age(103).role(Role.MANAGER).description("관리자임").build();
        Member member5 = Member.builder().username("dd4").age(104).role(Role.MANAGER).description("관리자임1").build();

        em.persist(member);
        em.persist(member2);
        em.persist(member4);
        em.persist(member5);


        log.info("ID: {}", member.getId());
        log.info("ID: {}", member2.getId());
        log.info("ID: {}", member4.getId());
        log.info("ID: {}", member5.getId());

        transaction.commit();
        em.close();
        // transaction 종료


    }
}
