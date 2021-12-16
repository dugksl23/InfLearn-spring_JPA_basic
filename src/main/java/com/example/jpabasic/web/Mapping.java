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

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class Mapping {

    private final MemberService memberService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public Member main(@RequestBody Member member) {

        Team team = new Team();
        team.setName("dd");
        member.setTeam(team);
        Member save = memberService.save(member);

        return save;

    }

    @RequestMapping(value = "{id}")
    @ResponseBody
    public Member main(@PathVariable Long id) {

        Member save = memberService.findOne(id);

        return save;

    }


}
