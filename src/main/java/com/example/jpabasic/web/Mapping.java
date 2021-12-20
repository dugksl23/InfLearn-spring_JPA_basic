package com.example.jpabasic.web;


import com.example.jpabasic.basic_study.Locker;
import com.example.jpabasic.basic_study.Member;
import com.example.jpabasic.basic_study.Role;
import com.example.jpabasic.basic_study.Team;
import com.example.jpabasic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class Mapping {

    private final MemberService memberService;
    private MemberService memberService1;


    @RequestMapping(value = "/register")
    @ResponseBody
    public Member register(@RequestBody Member member) {

        Team team = new Team();
        team.setName("dd");
//        member.setTeam(team);
        Member save = memberService.save(member);

        return save;

    }

    @RequestMapping(value = "{id}")
    @ResponseBody
    public Member findMember(@PathVariable Long id) {

        Member save = memberService.findMember(id);

        return save;

    }


    @RequestMapping(value = "/team/{id}")
    @ResponseBody
    @Transactional
    public String findTeam1(@PathVariable Long id) {

        Team team = memberService.findTeam(id);
        System.out.println("======== 관계의 주인이 아닌 team에서 update start ========");
        log.info("member id, age : {},{}", team.getMembers().get(0).getId(), team.getMembers().get(0).getAge());
        team.getMembers().get(0).setAge(200);
        System.out.println("======== 관계의 주인이 아닌 team에서 update end ========");
        log.info("age : {}", team.getMembers().get(0).getAge());

        return "dd";

    }

    @GetMapping(value = "/team/add/member")
    @ResponseBody
    @Transactional
    public String saveTeamAndAddMember() {

        // 팀 생성 및 영속화
        Team team = new Team();
        team.setName("dd");
        Team team2 = memberService.saveTeam(team);

        // 멤버 생성 및 영속화
        Member member = new Member();
        member.setAge(20);
        member.setRole(Role.MANAGER);
        member.setUsername("mappedByTeam");
        member.setDescription("dd");
//        team2.getMembers().add(member);
        // member(주인)을 통한 객체 참조 매핑
        //양 방향일 경우에는 팀과 회원 모두에게 해야지 객체지향스러운 코드가 된다.
//        member.changeTeam(team2);
//        member.changeTeam(team2);
        System.out.println("team.getMember : {}, {}" + team.getMembers().get(0).getUsername() + team.getMembers().get(0).toString());
        memberService.save(member);

        return "dd";

    }


    @RequestMapping("/oneToMany")
    public Team oneToManyTest() {
        Team team = new Team();
        team.setName("team owner");

        Member member = new Member();
        member.setUsername("member1");
        member = memberService.save(member);

        team.getMembers().add(member);
        team = memberService.saveTeam(team);

        log.info("============ 매핑 완료 =================");

        return team;

    }

    @RequestMapping("/oneToMany/update")
    public Team oneToManyUpdateTest() {

        Member member1 = memberService.findMember(1l);
        member1.getTeam().setName("df");

        return member1.getTeam();

    }


    @RequestMapping("/locker")
    @ResponseBody
    @Transactional
    public Locker locker() {

        Member member = new Member();
        member.setUsername("dddd");
        Member save = memberService.save(member);

        Locker locker = new Locker();
        locker.setName("dd");
        locker.setMember(member);
        Locker locker1 = memberService.saveLocker(locker);
        Member member1 = memberService.findMember(save.getId());

        System.out.println(member1.getId());


        return locker1;

    }

    @RequestMapping("/registerTeam")
    @ResponseBody
    public Member updateTest() {

        Team team = new Team();
        team.setName("dd");
//        member.setTeam(team);
        Team team1 = memberService.saveTeam(team);

        Member member = new Member();
        member.setUsername("dddd");
        member.setTeam(team1);
        Member save = memberService.save(member);

        return save;
    }

    @RequestMapping("/findTeam/{id}")
    @ResponseBody
    @Transactional
    public void findTeam(@PathVariable Long id) {

        Team team = memberService.findTeam(id);
        team.getMembers().stream().map(member -> {
            System.out.println("수정 전 member name : " + member.getUsername());
            System.out.println("수정후");
            member.setUsername("ddddd");
            System.out.println("수정 후 member name : " + member.getUsername());
            return member;
        });



    }


}
