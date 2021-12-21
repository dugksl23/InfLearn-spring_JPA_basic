package com.example.jpabasic.basic_study;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "locker_id", nullable = false)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
