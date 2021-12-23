package com.example.jpabasic.practice_exam.core.embedded;

import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;
import java.util.Objects;


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

    // 객체지향적으로 SRP 수행.
    public boolean isValid(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return ObjectUtils.isEmpty(getZipCode()) && ObjectUtils.isEmpty(getCity()) && ObjectUtils.isEmpty(getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZipCode(), getCity(), getStreet());
    }

    public Address toNewAddress(String zipCode, String city, String street) {
        Address build = Address.builder().city(city).street(street).zipCode(zipCode).build();
        return build;
    }

}
