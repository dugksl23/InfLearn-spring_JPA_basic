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
import java.util.Optional;
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
        member.setAddresses(Arrays.asList(new Address("old", "dd", "dd"), new Address("old1", "dd", "dd")));
        member.setFavoriteFood(Set.of("사과", "빵"));

        memberExamRepository.save(member);

    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void 값타입조회Test() {

        Optional<MemberExam> byId = memberExamRepository.findById(11L);
        byId.get().getAddresses().get(0);
        Set<String> favoriteFood = byId.get().getFavoriteFood();
        favoriteFood.iterator().forEachRemaining(food -> {
            System.out.println("food : " + food);
        });
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void 값타입수정Test() {

        MemberExam memberExam = memberExamRepository.findById(11L).get();

        // 1. 일반 값타입 필드 수정
        Address address = memberExam.getHomeAddress()
                .toNewAddress("수정 3", memberExam.getHomeAddress().getCity(), memberExam.getHomeAddress().getStreet());
        memberExam.setHomeAddress(address);

        // 2. 값 타입 컬렉션 필드 수정
        Set<String> favoriteFood = memberExam.getFavoriteFood();
        favoriteFood.remove("빵"); // 추가가 아닌 변경이기에 삭제를 하고 다시 넣어야 한다.
        favoriteFood.add("빵1");
        // 단, 단순 String 의 set collection 이기에, 삭제된 row 의 수정이 아닌,
        // 다음 개행으로 새로 추가(insert)된다.

        // 3. 값 타입 컬렉션 삭제 (*조회 후에 삭제!!)
        MemberExam memberExam1 = memberExamRepository.findById(11L).get();
        Address address1 = memberExam1.getAddresses().get(0);
        memberExam1.getAddresses().remove(address1);

        memberExam1.getAddresses().add(Address.builder().city("dd").zipCode("dd").street("dd").build());

    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void 값타입컬렉션삭제Test() {

        // 1. 값 타입 컬렉션 삭제 (*조회 후에 삭제!!)
        MemberExam memberExam1 = memberExamRepository.findById(13L).get();
        Address address = memberExam1.getAddresses().get(0);
        memberExam1.getAddresses().remove(memberExam1.getAddresses().get(0));

        memberExam1.getAddresses().add(address.toNewAddress("new city","dd","dd"));

    }


}