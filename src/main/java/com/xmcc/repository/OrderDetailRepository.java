package com.xmcc.repository;

import com.xmcc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//泛型1：实体类泛型 泛型2：主键类型
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    //关键字定义
    List<OrderDetail> findAllByDetailIdIn(List<String> ids);
    //自定义sql
    //jpa 底层给予hibernate：hql（基于实体类进行查询）jpa：jpql（基于实体类）
    //基于传统sql
    //写法1
    @Query(value = "select * from order_detail where detail_id= ?1",nativeQuery = true)
    OrderDetail queryOrderDetailByDetailId(String id);
    //写法2
    @Query(value = "select * from order_detail where detail_id=:ids",nativeQuery = true)
    OrderDetail queryOrderDetailByDetailId2(@Param("ids") String id);
    //基于实体类
    @Query(value = "select od from OrderDetail od where detail_id= ?1")
    OrderDetail getOrderDetailByDetailId(String id);

}
