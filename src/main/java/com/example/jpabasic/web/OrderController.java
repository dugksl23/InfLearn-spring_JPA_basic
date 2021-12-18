package com.example.jpabasic.web;


import com.example.jpabasic.domain.*;
import com.example.jpabasic.repository.ItemRepository;
import com.example.jpabasic.repository.OrderItemRepository;
import com.example.jpabasic.repository.OrderRepository;
import com.example.jpabasic.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final UsersRepository usersRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;


    @RequestMapping(value = "/items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @ResponseBody
    public Orders purchaseOrder(@RequestBody Orders order) {

        order.setStatus(OrderStatus.purchase);

        // == user 생성 및 영속화
        Users user = Users.builder().userName("user1").age(20).build();
        user = usersRepository.save(user);

        // == user 와 order 의 매핑 및 영속화
        order.setUser(user);
        Orders orderSave = orderRepository.save(order);

        // == item 생성 및 영속화 및 영속화된 item을 OrderItem과 객체 매핑
        List<OrderItem> collect = order.getOrderItems().stream().map(orderItem -> {
            Item item = itemRepository.save(orderItem.getItem());
            orderItem.setItem(item);
            return orderItem;
        }).collect(Collectors.toList());

        // == orderItem 과 item 영속화
        List<OrderItem> orderItemList = collect.stream().map(orderItem -> orderItemRepository.save(orderItem)).collect(Collectors.toList());

        // oderItem으로 order와 매핑 및 영속화
        List<OrderItem> collect1 = orderItemList.stream().map(orderItem -> {
            orderItem.mappingWithOrder(orderSave);
            return orderItemRepository.save(orderItem);
        }).collect(Collectors.toList());

        // orderItem와 order의 객체매핑
        //orderSave.setOrderItems(collect1);

        return orderSave;

    }


}
