package com.example.jpabasic.service;


import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;
import com.example.jpabasic.repository.MemberRepository;
import com.example.jpabasic.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Member save(Member member) {
        Team team = new Team();
        team.setName("team1");
        Team save1 = teamRepository.save(team);
        log.info("team id : {}",team.getId());
        member.setTeam(save1);
        Member save = memberRepository.save(member);
        return save;
    }

    @Transactional
    public Member findOne(Long id){
        Optional<Member> byId = memberRepository.findById(id);

        Team team = new Team();
        team.setName("team2");
        Team save1 = teamRepository.save(team);
        log.info("team id : {}",team.getId());

        byId.get().setTeam(save1);
        return byId.get();
    }




}
