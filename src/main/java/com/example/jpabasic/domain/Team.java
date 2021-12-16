package com.example.jpabasic.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "team_id", nullable = false)
    @GeneratedValue
    private Long id;
    private String name;


}
