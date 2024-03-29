package com.xmcc.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity//表示该类为实体类
//设置为true,表示update对象的时候,生成动态的update语句,如果这个字段的值是null就不会被加入到update语句中
@DynamicUpdate
@Data //相当于get set tostring方法
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_info") //按照了去掉下划线首字母大写的规则 就可以不用指定
@Builder
public class ProductInfo implements Serializable {

    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}