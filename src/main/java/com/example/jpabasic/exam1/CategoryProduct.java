package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CategoryProduct {
    @Id
    @GeneratedValue
    @Column(name = "category_product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductExam product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryExam categorie;



}