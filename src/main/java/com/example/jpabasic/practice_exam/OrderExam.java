package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OrderExam extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberExam_id")
    private MemberExam member;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProductExam> orderProducts;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryExam delivery;

}
