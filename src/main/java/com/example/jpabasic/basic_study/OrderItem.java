package com.example.jpabasic.basic_study;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem { // =OrderItemDetail, 주문 내역서의 아이템 1 row

    //=== item info ====
    @Id
    @GeneratedValue
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @Column(name = "order_price")
    private Integer orderPrice;

    @Column(name = "order_count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "item_id ")
    private Item item; // 하나의 아이템 정보만을 담은 객체 단위의 테이블로 규정, 단 아이템을 중복 주문 가능하기에 ManyToOne ex) 맥북 그레이, 화이트인데 주문된 row는 2개 n:1

    // === order(주문 내역서) 와의 매핑 관계 설정 ===
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order; // 주문 내역서의 i row 를 many 로 가질 order 와 매핑 관계


    public void mappingWithItem(Item item) {
        if (this.item == null) {
            this.item = item;
        }
    }

    public void mappingWithOrder(Orders orderDto) {
        if (this.order == null) {
            this.order = orderDto;
            orderDto.getOrderItems().add(this);
        }

    }


}
