package com.example.jpabasic.web;

import com.example.jpabasic.domain.MemberEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EntityManagerFactoryTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // 1. save
        try {
            MemberEntity dd = new MemberEntity();
            dd.setId(1L);
            dd.setUsername("dd");
            em.persist(dd);

            // 2. find (jpql)
            // 2-1 단순조회
            MemberEntity memberEntity = em.find(MemberEntity.class, 1l);


            // 2-2. @Query 어노테이션(객체단위)
            List<MemberEntity> resultList = em.createQuery("select m.id, m.username from MemberEntity as m", MemberEntity.class)
                    .getResultList();

            // -> 테이블을 대상으로 하는 쿼리가 아닌, 객체(entity)  대상으로 쿼리 수행
            //    테이블을 대상으로 직접 쿼리를 날리면, db에 종속적이게 된다.
            //    jpa는 객체지향적으로 객체단위로 쿼리를 수행함으로서, 객체지향중심의 orm 개발을 가능케한다.

            // 2-3. paging
            List<MemberEntity> resultList1 = em.createQuery("select m.id, m.username from MemberEntity as m", MemberEntity.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            // jpql -> jpql는 sql을 추상화한 jqpl이라는 객체지향적인 쿼리 언어 제공
            //         객체를 대상으로 쿼리 수행
            // sql - 테이블 대상으로 쿼리 수행
            // *이점 -> 특정 db의 방언에 따라 쿼리를 짤 필요없이 객체단위로 쿼리 수행하기에 db에 종속적이기 않는다.

            // 3. update
            // * jpa의 모든 데이터 변경은 트랙잭션 안에서만 이뤄져야 하며,
            // * 트랜잭션 간의 쓰레드 공유는 절대 해선 안된다.
            memberEntity.setId(2l);


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

}
