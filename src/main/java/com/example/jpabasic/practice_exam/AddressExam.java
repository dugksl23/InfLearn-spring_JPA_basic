package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressExam {
    @Id
    @GeneratedValue
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Embedded
    private Address address;

}
