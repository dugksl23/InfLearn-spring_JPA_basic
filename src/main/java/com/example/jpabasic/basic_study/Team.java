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

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();
//    @OneToMany
//    @JoinColumn(name = "team_id")
//    private List<Member> members = new ArrayList<>();


}
