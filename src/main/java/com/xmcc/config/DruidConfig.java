package com.xmcc.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*表示该类为配置类*/
@Configuration
public class DruidConfig {

    @Bean(value = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.druid")
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        //Lists.newArrayList()相当于new ArrayList()  google工具包提供的
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));

        return druidDataSource;
    }
    //配置过滤的数据
    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);//慢查询是否记录日志
        statFilter.setSlowSqlMillis(5);//慢查询时间
        statFilter.setMergeSql(true);//格式化sql
        return statFilter;
    }
    //配置访问路径
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //localhost:8888/sell/druid
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*  ");
    }
}
