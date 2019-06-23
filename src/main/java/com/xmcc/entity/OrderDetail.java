package com.xmcc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity//表示该类为实体类
//设置为true,表示update对象的时候,生成动态的update语句,如果这个字段的值是null就不会被加入到update语句中
@DynamicUpdate
@Data //相当于get set tostring方法
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    /** 类目id. */
    @Id  //主键
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}