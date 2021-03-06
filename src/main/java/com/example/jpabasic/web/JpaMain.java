package com.example.jpabasic.web;

import com.example.jpabasic.basic_study.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // 비영속
        transaction.begin();
        Member memberEntity = new Member();
//        memberEntity.setId(2l);
        memberEntity.setUsername("d");
        memberEntity.setAge(10);

        // 영속화
        em.persist(memberEntity);
//        em.flush();
        em.createQuery("select m from Member as m").getResultList();
        Member memberEntity3 = em.find(Member.class, 1l);
        log.info("id : {}", memberEntity3.getId());

        // 영속화 find
        Member memberEntity1 = em.find(Member.class, 1l);
        log.info("영속 id : {}", memberEntity1.getId());

        // 준영속 - 영속화된 후 영속성 컨텍스트에서 삭제
        em.detach(memberEntity1);


        // 비영속화 find
        Member memberEntity2 = em.find(Member.class, 1l);
        log.info("비영속 id : {}",   memberEntity2.getId());

        // 삭제 - 영컨에 캐싱된 엔티티와 db의 data까지 삭제
        em.remove(memberEntity);

        // 변경감지
        memberEntity1.setUsername("aaaa");
        Member memberEntity4 = em.find(Member.class, 1l);
        log.info("Dirty checking, name : {}", memberEntity4.getUsername());

        // 트랙잭션 버퍼에 쌓인 query 를 커밋
        transaction.commit();

        // 엔티티 매니저 쓰레드 종료 및 커넥션을 factory에 반환
        em.close();
        emf.close();


    }
}
