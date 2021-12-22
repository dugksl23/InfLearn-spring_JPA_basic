package com.example.jpabasic.cascade;

import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Team;
import com.example.jpabasic.practice_exam.MemberExam;
import com.example.jpabasic.practice_exam.core.embedded.Address;
import com.example.jpabasic.practice_exam.core.embedded.LogDate;
import com.example.jpabasic.repository.MemberExamRepository;
import com.example.jpabasic.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CascadeTest {


    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberExamRepository memberExamRepository;

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
//        memberRepository.save(member);

        Member member2 = new Member();
        member2.setUsername("user2");
//        memberRepository.save(member2);

        team.addMember(member);
        team.addMember(member2);

        Team save = teamRepository.save(team);
//        save.getMembers().remove(0);
//        teamRepository.delete(save);
//        memberRepository.delete(member2);

    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void embeddableMemberTest() {

        Address address = new Address("서울", "서울2", "서울3");

        MemberExam memberExam = new MemberExam();
        memberExam.setName("dd");
        memberExam.setHomeAddress(address);
        memberExam.setLogDate(new LogDate());
        memberExamRepository.save(memberExam);

        // 임베디드 값 타입을 복사해서 사용. - 공유 금지
        Address address1 = address.toNewAddress(address.getZipCode(), address.getCity(), address.getStreet());

        System.out.println("동일 인스턴스 비교 : " + (address == address1));

        MemberExam memberExam1 = new MemberExam();
        memberExam1.setName("dd");
        memberExam1.setHomeAddress(address1);
        memberExam1.setLogDate(new LogDate());
        memberExamRepository.save(memberExam1);

        System.out.println(address.getCity());

    }


    @Test
    public void equalTest() {

        // 1. address  인스턴스 생성
        Address address = new Address("서울", "서울2", "서울3");

        // 2. " == " 비교용 새로운 인스턴스 생성
        Address address1 = address.toNewAddress(address.getZipCode(), address.getCity(), address.getStreet());
        Address address2 = address;

        // 1. 인스턴스의 참조값 비교 " == "
        System.out.println("동일 인스턴스 == 비교 : " + (address == address1));
        // --> false

        // 2. 인스턴스 value 비교 -  @EqualsAndHashCode
        System.out.println("동일 인스턴스 == 비교 : " + (address.equals(address2)));
        // --> true
    }


    @Test
    @Rollback(value = false)
    public void 값타입InsertTest() {

        MemberExam member = new MemberExam();
        member.setName("dd");
        member.setAddresses(Arrays.asList(new Address("dd", "dd", "dd"), new Address("dd", "dd", "dd")));
        member.setFavoriteFood(Set.of("사과", "빵"));

        memberExamRepository.save(member);

    }


}