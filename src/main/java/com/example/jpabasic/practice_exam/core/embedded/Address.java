package com.example.jpabasic.practice_exam.core.embedded;

import lombok.*;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Address {

    private String zipCode;
    private String city;
    private String street;

    public Address toNewAddress(String zipCode, String city, String street) {
        Address build = Address.builder().city(city).street(street).zipCode(zipCode).build();
        return build;
    }

}
