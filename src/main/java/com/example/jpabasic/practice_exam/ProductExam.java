package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "d_type")
public abstract  class ProductExam extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false)
    private Long id;

    private Integer quantity;
    private String name;

    @OneToMany
    private List<CategoryProduct> categoryProductList;

}
