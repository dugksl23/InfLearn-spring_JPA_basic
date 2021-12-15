package com.example.jpabasic.web;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Role;
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
        transaction.begin();
        Member member = Member.builder().username("dd")
                .age(10).role(Role.MANAGER).description("관리자임").build();

        em.persist(member);
        log.info("ID: {}", member.getId());
        Member member1 = em.find(Member.class, member.getId());
        log.info("member role : {}", member1.getRole());
        transaction.commit();
        em.close();
    }
}
