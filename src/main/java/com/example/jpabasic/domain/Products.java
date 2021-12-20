package com.example.jpabasic.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Products {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String name;

}
