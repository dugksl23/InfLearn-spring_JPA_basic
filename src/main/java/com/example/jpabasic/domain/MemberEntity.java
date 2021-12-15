package com.example.jpabasic.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "memberEntity")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MemberEntity", uniqueConstraints = @UniqueConstraint(name = "member_uniq", columnNames = {"username", "age"}))
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false, length = 255)
    private String username;
    @Column(unique = true)
    private long age;

}
