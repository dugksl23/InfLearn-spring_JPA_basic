package com.example.jpabasic.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Member", uniqueConstraints = @UniqueConstraint(name = "member_uniq", columnNames = {"username", "age"}))
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "age", unique = true)
    private long age;

    @Lob
    private String description;//varchar2보다 더 큰 데이터를 저장하기 위한 타입을 지원하는 어노테이션

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    // @Temporal -  Date 타입만 지원된다.

    // java 8 버전부터
    // @Temporal(TemporalType.TIMESTAMP) 어노테이션 생략 가능
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING) //열거
    private Role role;

    @Transient
    private String temp;
    // @Transient - jpa, 영속성 컨텍스트의 관리에서 제외되는 어노테이션

    @Builder
    public Member(String username, long age, String description, Role role, String temp) {
        this.username = username;
        this.age = age;
        this.description = description;
        this.role = role;
        this.temp = temp;
    }
}
