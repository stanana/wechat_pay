package com.xmcc;


import com.xmcc.entity.OrderDetail;
import com.xmcc.repository.OrderDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatPayApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void contextLoads() {
//        List<OrderDetail> all = orderDetailRepository.findAll();
//        all.stream().forEach(System.out::println);
//        ArrayList<String> ids = Lists.newArrayList("3a510f6472dc47149562664f4bcb4c8a", "8c914210cb384864919ebae9652e9eb7");
//        List<OrderDetail> allByDetailId = orderDetailRepository.findAllByDetailIdIn(ids);
//        allByDetailId.stream().forEach(System.out::println);

        OrderDetail orderDetail = orderDetailRepository.queryOrderDetailByDetailId2("3a510f6472dc47149562664f4bcb4c8a");
        System.out.println(orderDetail);
    }

}
