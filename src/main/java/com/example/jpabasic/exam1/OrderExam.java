package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderExam {
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberExam_id")
    private MemberExam member;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryExam delivery;

}
