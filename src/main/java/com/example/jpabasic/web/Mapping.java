package com.example.jpabasic.web;


import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Role;
import com.example.jpabasic.domain.Team;
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

    @RequestMapping(value = "/register")
    @ResponseBody
    public Member register(@RequestBody Member member) {

        Team team = new Team();
        team.setName("dd");
        member.setTeam(team);
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
    public String findTeam(@PathVariable Long id) {

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

        // 멤버 생성 및 영속화
        Member member = new Member();
        member.setAge(20);
        member.setRole(Role.MANAGER);
        member.setUsername("mappedByTeam");
        member.setDescription("dd");

        // 팀 생성 및 영속화
        Team team = new Team();
        team.setName("dd");
        Team team2 = memberService.saveTeam(team);

        //양 방향일 경우에는 팀과 회원 모두에게 해야지 객체지향스러운 코드가 된다.
        team2.getMembers().add(member);

        // member(주인)을 통한 객체 참조 매핑
        member.setTeam(team2);
        memberService.save(member);

        return "dd";

    }


}
