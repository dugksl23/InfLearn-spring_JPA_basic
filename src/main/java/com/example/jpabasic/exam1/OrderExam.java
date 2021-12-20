package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "order")
    private List<OrderProductExam> orders;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryExam delivery;

}
