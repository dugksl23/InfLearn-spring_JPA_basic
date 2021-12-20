package com.example.jpabasic.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class OrderRequestTest {


    public MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext ctx;

    private Logger log = (Logger) LoggerFactory.getLogger(OrderRequestTest.class);

    @BeforeEach
    @DisplayName("MockMvc 객체 DI 및 UTF 설정")
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("주문 생성 test")
    public void registerOrderTest() throws Exception {
        Orders order = new Orders();
        // given...
        OrderItem orderItem = new OrderItem();
        Item item = new Item();
        item.setName("item1");
        item.setPrice(1000);
        item.setStockQuantity(100);
        orderItem.mappingWithItem(item);
        orderItem.setCount(10);
        orderItem.setOrderPrice(10000);



        OrderItem orderItem2 = new OrderItem();
        Item item2 = new Item();
        item2.setName("item1");
        item2.setPrice(1000);
        item2.setStockQuantity(100);
        orderItem2.mappingWithItem(item2);
        orderItem2.setOrderPrice(10000);
        orderItem2.setCount(100);

        order.setOrderItems(Arrays.asList(orderItem2, orderItem2));


        // when...
        MvcResult result = mvc.perform(
                        MockMvcRequestBuilders.post("/order/items")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(order))
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.notNull(content);
        System.out.println(content);
        log.info("content : {}",content);
    }

}
