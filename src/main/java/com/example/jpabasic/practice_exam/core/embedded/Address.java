package com.example.jpabasic.practice_exam.core.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

    private String zipCode;
    private String city;
    private String street;

}
