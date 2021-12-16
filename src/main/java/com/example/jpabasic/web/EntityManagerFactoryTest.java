package com.example.jpabasic.web;

import com.example.jpabasic.domain.Member;

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
            Member dd = new Member();
            dd.setId(1l);
            dd.setUsername("dd");

            // 영속성 컨텍스트에 저장
            // 이렇게 하면 해당 엔티티 매니저의 영속성 컨텍스트에 위에서 만든 member 객체가 저장된다.
            // 이제 member 엔티티는 엔티티 매니저의 관리 대상이 되고, 영속성을 가졌다고 말할 수 있다.
            em.persist(dd);

            // 2. find (jpql)
            // 2-1 단순조회
            Member memberEntity = em.find(Member.class, 1l);


            // 2-2. @Query 어노테이션(객체단위)
            List<Member> resultList = em.createQuery("select m.id, m.username from Member as m", Member.class)
                    .getResultList();

            // -> 테이블을 대상으로 하는 쿼리가 아닌, 객체(entity)  대상으로 쿼리 수행
            //    테이블을 대상으로 직접 쿼리를 날리면, db에 종속적이게 된다.
            //    jpa는 객체지향적으로 객체단위로 쿼리를 수행함으로서, 객체지향중심의 orm 개발을 가능케한다.

            // 2-3. paging
            List<Member> resultList1 = em.createQuery("select m.id, m.username from Member as m", Member.class)
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

            // db에 flush - db와 유일성을 유지하기 위함
            em.flush();

            // 각각의 엔티티 매니저에서 쌓인 쿼리를 커밋
            // em.flush()가 포함됨
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            // 엔티티 매니저를 종료시켜줘야 한다.
            // 아마 더 이상 사용하지 않는 자원이므로 메모리에 자원을 반환하지 않으면
            // 성능 상 문제가 있어서 이렇게 종료시켜줘야 하는 건지 모르겠다.
            // ** 다른 쓰레드와의 공유를 금지하기 위해서 반드시 닫아줘야 한다.
            em.close();
        }
        emf.close();// 마찬가지로 엔티티 매니저 팩토리도 더이상 쓰지 않는다면 종료시켜줘야 한다.

    }

}
