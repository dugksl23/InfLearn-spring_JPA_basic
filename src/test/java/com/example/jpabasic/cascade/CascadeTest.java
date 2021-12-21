package com.example.jpabasic.cascade;

import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Team;
import com.example.jpabasic.repository.MemberRepository;
import com.example.jpabasic.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CascadeTest {


    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void cascadePersistTest() {

        Team team = new Team();
        team.setName("team1");

        Member member = new Member();
        member.setUsername("user1");

        Member member2 = new Member();
        member2.setUsername("user2");

        team.addMember(member);
        team.addMember(member2);

        teamRepository.save(team);


    }

    @Test
    @Rollback(value = false)
    public void cascadeRemoveWithOrphanTest() {

        Team team = new Team();
        team.setName("team1");

        Member member = new Member();
        member.setUsername("user1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setUsername("user2");
        memberRepository.save(member2);

        team.addMember(member);
        team.addMember(member2);

        Team save = teamRepository.save(team);
        teamRepository.delete(save);
//        memberRepository.delete(member2);

    }


}