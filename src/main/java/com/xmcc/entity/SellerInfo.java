package com.xmcc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity//表示该类为实体类
//设置为true,表示update对象的时候,生成动态的update语句,如果这个字段的值是null就不会被加入到update语句中
@DynamicUpdate
@Data //相当于get set tostring方法
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seller_info")
@Builder
public class SellerInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //表示自增IDENTITY：mysql SEQUENCE:oracle
    private String id;

    private String username;

    private String password;

    private String openid;

    private Date createTime;

    private Date updateTime;


}