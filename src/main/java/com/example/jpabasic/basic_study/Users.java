package com.example.jpabasic.basic_study;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "name")
    private Integer age;

    @Builder
    public Users(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

}
