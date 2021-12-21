package com.example.jpabasic.proxyTest;

import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Team;
import com.example.jpabasic.repository.MemberRepository;
import com.example.jpabasic.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class proxyTest {

    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void saveTeam() {

        Team team = new Team();
        team.setName("team1");
        teamRepository.save(team);


        Member member = new Member();
        member.setAge(10);
        member.setTeam(team);
        memberRepository.save(member);


    }

    @Test
    @Rollback(value = false)
    public void saveMemberWithTeam() {


        Optional<Team> byId = teamRepository.findById(4l);


        Member member = new Member();
        member.setAge(10);
        member.setTeam(byId.get());
        memberRepository.save(member);

    }

    @Test
    @Rollback(value = false)
    public void checkTeamReference() throws Exception {


        Optional<Member> member = memberRepository.findById(1l);
        Optional<Team> team = teamRepository.findById(4l);
        log.info(" team entity: {}}", team.get().getClass());
        log.info(" member.team entity: {}}", member.get().getTeam().getClass());


        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        log.info("=== 초기화 전 ===");
        Team team1 = member.get().getTeam();
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(team1);
        log.info("초기화 결과 result : {}", loaded);
        transaction.commit();
        em.close();

        EntityManager em1 = emf.createEntityManager();
        EntityTransaction transaction1 = em1.getTransaction();
        transaction1.begin();
        Optional<Member> member1 = memberRepository.findById(1l);
        log.info("=== 초기화 후 ===");
        Team team2 = member1.get().getTeam();
        log.info("name : {}", team2.getId());
        log.info(" 주소값 : {}", team2.getClass());
        boolean loaded2 = emf.getPersistenceUnitUtil().isLoaded(team2);
        log.info("초기화 결과 result : {}", loaded2);
        transaction1.commit();
        em1.close();
        emf.close();


        if (team.get().getClass() == member.get().getTeam().getClass()) {
            log.info("expected : false");
            log.info(" team entity: {}}", team.get());
            log.info(" member.team entity: {}}", member.get().getTeam());

        } else if (team.get() instanceof Team) {
            log.info("상속 관계 맞음");
            log.info(" team entity: {}}", team.get().getClass());
            log.info("query 날라가기 전");
            log.info(" member.team entity: {}}", member.get().getTeam().getName());

        }

    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void lazyLoadingTest() {


        Optional<Member> byId = memberRepository.findById(1l);
        log.info("team reference : {}", byId.get().getTeam().getClass());
        byId.get().getTeam().getName();

    }


}
