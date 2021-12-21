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
    private List<Member> members = new ArrayList<Member>();
//    @OneToMany
//    @JoinColumn(name = "team_id")
//    private List<Member> members = new ArrayList<>();


    public void addMember(Member member) {
        this.members.add(member);
        member.setTeam(this);
    }

}
