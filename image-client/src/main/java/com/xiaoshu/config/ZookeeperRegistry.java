package com.xiaoshu.config;

import com.xiaoshu.config.DistributedServer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : function description
 * ---------------------------------
 * @Author : deane
 * @Date : Create in 2019/9/22 10:26
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@Component
@Slf4j
public class ZookeeperRegistry {

    @Autowired
    private DistributedServer distributeServer;

    @Value("${zookeeper.registry.value}")
    private String zookeeperRegistryValue;

    @PostConstruct
    public void startRegistryZookeeper() {
        log.info("=========>  start resgister MyServer to Zookeeper ...===========>");
        try {
            //将客户端注册到Zookeeper上面
            distributeServer.registerZK(zookeeperRegistryValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
