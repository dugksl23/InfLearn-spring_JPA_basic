package com.example.jpabasic.web;

import com.example.jpabasic.domain.MemberEntity;
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
        MemberEntity memberEntity = new MemberEntity();
//        memberEntity.setId(2l);
        memberEntity.setUsername("d");
        memberEntity.setAge(10);

        // 영속화
        em.persist(memberEntity);

        // 영속화 find
        MemberEntity memberEntity1 = em.find(MemberEntity.class, 1l);
        log.info("영속 id : {}", memberEntity1.getId());

        // 준영속 - 영속화된 후 영속성 컨텍스트에서 삭제
        em.detach(memberEntity1);


        // 비영속화 find
        MemberEntity memberEntity2 = em.find(MemberEntity.class, 1l);
        log.info("비영속 id : {}",   memberEntity2.getId());

        // 삭제 - 영컨에 캐싱된 엔티티와 db의 data까지 삭제
        em.remove(memberEntity);


        // 트랙잭션 버퍼에 쌓인 query 를 커밋
        transaction.commit();

        // 엔티티 매니저 쓰레드 종료 및 커넥션을 factory에 반환
        em.close();
        emf.close();


    }
}
