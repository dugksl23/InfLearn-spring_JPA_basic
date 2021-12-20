package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CategoryProduct extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductExam product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryExam category;



}