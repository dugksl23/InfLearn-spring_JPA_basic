package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderProductExam {
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
