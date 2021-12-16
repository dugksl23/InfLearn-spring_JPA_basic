package com.example.jpabasic.web;


import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;
import com.example.jpabasic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
