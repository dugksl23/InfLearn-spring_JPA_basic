package com.example.jpabasic.basic_study;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "team_id")
    private List<Member> members = new ArrayList<Member>();
    // - oneToMany에서 mappedBy 속성을 쓰지 않았다면, Team이 주인
    // - 단 @JoinColumn 은 대상 DB를 기준으로 다(N)인 Member를 기준으로 해야 하기에
    // - @JoinColumn은 Team을 해주어야 한다.
//    @OneToMany
//    @JoinColumn(name = "team_id")
//    private List<Member> members = new ArrayList<>();


    public void addMember(Member member) {
        this.members.add(member);
        member.setTeam(this);
    }

}
