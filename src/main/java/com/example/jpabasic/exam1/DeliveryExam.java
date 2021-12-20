package com.example.jpabasic.exam1;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DeliveryExam {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id", nullable = false)
    private Long id;

    // 배송지 주소
    private String address;
    private String zipcode;

    @CreationTimestamp
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private OrderExam order;

}
