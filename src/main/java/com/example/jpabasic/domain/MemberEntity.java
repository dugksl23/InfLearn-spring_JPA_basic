package com.example.jpabasic.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;


}
