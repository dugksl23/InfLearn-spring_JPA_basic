package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderProductExam extends BaseEntity  {
    @Id
    @GeneratedValue
    @Column(name = "order_product_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderExam order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductExam product;

}
