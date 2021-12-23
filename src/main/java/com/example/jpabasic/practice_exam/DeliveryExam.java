package com.example.jpabasic.practice_exam;


import com.example.jpabasic.practice_exam.core.BaseEntity;
import com.example.jpabasic.practice_exam.core.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DeliveryExam extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id", nullable = false)
    private Long id;

    // 배송지 주소 - embedded 타입으로 대체
    @Embedded
    private Address addressExam;

    @CreationTimestamp
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private OrderExam order;

}
