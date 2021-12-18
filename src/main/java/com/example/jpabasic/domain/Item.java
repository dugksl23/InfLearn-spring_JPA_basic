package com.example.jpabasic.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_quantity")
    private Integer stockQuantity;

    @Column(name = "item_price")
    private Integer price;

}
