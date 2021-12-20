package com.example.jpabasic.basic_study;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders { //주문 내역서

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime orderDate;

    @Column(name = "update_date", updatable = true)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @ManyToOne // 연관관계의 주인
    @JoinColumn(name = "user_id") // 외래키 매핑
    private Users user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems; // 각각의 주문 내역

    public void setUser(Users user) {
        if(!ObjectUtils.isEmpty(user)){
            this.user = user;
        }
    }

}
