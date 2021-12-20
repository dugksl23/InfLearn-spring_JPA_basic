package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ProductExam {
    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false)
    private Long id;



}
