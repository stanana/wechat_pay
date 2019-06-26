package com.xmcc.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.xmcc.properties.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayConfig {

    @Autowired
    private WeChatProperties weChatProperties;

    @Bean
    public BestPayService bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weChatProperties.getAppid());
        wxPayH5Config.setAppSecret(weChatProperties.getSecret());
        wxPayH5Config.setMchId(weChatProperties.getMchId());
        wxPayH5Config.setMchKey(weChatProperties.getMchKey());
        wxPayH5Config.setNotifyUrl(weChatProperties.getNotifyUrl());
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
}
