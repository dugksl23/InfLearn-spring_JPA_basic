package com.example.jpabasic.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table(name = "Member", uniqueConstraints = @UniqueConstraint(name = "member_uniq", columnNames = {"username", "age"}))
@SequenceGenerator(
        name = "Member_Sequence_Generator", // generator 이름
        sequenceName = "Member_Seq", // 매핑할 db 시퀀스 이름,
        initialValue = 1,
        allocationSize = 1
)
//@TableGenerator(
//        name = "Member_seq_generator",
//        table = "Member_seq_table",
//        pkColumnName = "member_seq",
//        allocationSize = 1
//)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Member_seq_generator")
    @Column(name = "member_id")
    private Long id;


    // int 는 null 일경우에 문제가 발생하기에 null 일경우 0으로 대체 가능한 Integer 로 사용해야 함
    // int, Integer는 integer
    // 그러나 long 써야 한다. int는 10억이상 안되기에...
    // long은 BigInt
    @Column(name = "username")
    private String username;

    @Column(name = "age")
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
    @CreationTimestamp
    private LocalDate localDate;
    @UpdateTimestamp
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING) //열거
    private Role role;

    @ManyToOne
    @JoinColumn(name = "team_id") // 외래키로 조인
    private Team team;


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
