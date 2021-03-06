package com.example.jpabasic.cascade;

import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Team;
import com.example.jpabasic.practice_exam.AddressExam;
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

        Address address = new Address("??????", "??????2", "??????3");

        MemberExam memberExam = new MemberExam();
        memberExam.setName("dd");
        memberExam.setHomeAddress(address);
        memberExam.setLogDate(new LogDate());
        memberExamRepository.save(memberExam);

        // ???????????? ??? ????????? ???????????? ??????. - ?????? ??????
        Address address1 = address.toNewAddress(address.getZipCode(), address.getCity(), address.getStreet());

        System.out.println("?????? ???????????? ?????? : " + (address == address1));

        MemberExam memberExam1 = new MemberExam();
        memberExam1.setName("dd");
        memberExam1.setHomeAddress(address1);
        memberExam1.setLogDate(new LogDate());
        memberExamRepository.save(memberExam1);

        System.out.println(address.getCity());

    }


    @Test
    public void equalTest() {

        // 1. address  ???????????? ??????
        Address address = new Address("??????", "??????2", "??????3");

        // 2. " == " ????????? ????????? ???????????? ??????
        Address address1 = address.toNewAddress(address.getZipCode(), address.getCity(), address.getStreet());
        Address address2 = address1;

        // 1. ??????????????? ????????? ?????? " == "
        System.out.println("?????? ???????????? == ?????? : " + (address == address1));
        // --> false

        // 2. ???????????? value ?????? -  @EqualsAndHashCode
        System.out.println("?????? ???????????? == ?????? : " + (address.equals(address2)));
        // --> true

    }


    @Test
    @Rollback(value = false)
    public void ?????????InsertTest() {

        MemberExam member = new MemberExam();
        member.setName("dd");

        AddressExam addressExam = new AddressExam();
        addressExam.setAddress(Address.builder().city("city1").street("street1").zipCode("zipcode1").build());

        AddressExam addressExam1 = new AddressExam();
        addressExam1.setAddress(Address.builder().city("city2").street("street2").zipCode("zipcode2").build());

        member.setAddresses(Arrays.asList(addressExam, addressExam1));
        member.setFavoriteFood(Set.of("??????", "???"));

        memberExamRepository.save(member);

    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void ???????????????Test() {

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
    public void ???????????????Test() {

        MemberExam memberExam = memberExamRepository.findById(10L).get();

        // 1. ?????? ????????? ?????? ??????
        Address address = memberExam.getHomeAddress()
                .toNewAddress("?????? 3", memberExam.getHomeAddress().getCity(), memberExam.getHomeAddress().getStreet());
        memberExam.setHomeAddress(address);

        // 2. ??? ?????? ????????? ?????? ??????
        Set<String> favoriteFood = memberExam.getFavoriteFood();
        favoriteFood.remove("???"); // ????????? ?????? ??????????????? ????????? ?????? ?????? ????????? ??????.
        favoriteFood.add("???1");
        // ???, ?????? String ??? set collection ?????????, ????????? row ??? ????????? ??????,
        // ?????? ???????????? ?????? ??????(insert)??????.

    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void ????????????????????????Test() {

        // 1. ???????????? ?????? (*?????? ?????? ??????!!)
        MemberExam memberExam1 = memberExamRepository.findById(18L).get();
        AddressExam addressExam = memberExam1.getAddresses().remove(0);

        AddressExam addressExam2 = new AddressExam();
        addressExam2.setAddress(Address.builder().city("city3").street("street3").zipCode("zipcode3").build());

        System.out.println("==== insert query ?????? ??? ====");
        memberExam1.getAddresses().add(addressExam2);

    }


}