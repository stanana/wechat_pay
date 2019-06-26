package com.xmcc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("weixin")
public class WXController {

    public void getCode(@RequestParam("code") String code){
        log.info("进入getCode回调方法");
        log.info("获得当前微信用户的授权码为:{}",code);
    }
}
