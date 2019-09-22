package com.xiaoshu.config;

import com.xiaoshu.config.zookeeper.DistributedClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
 * @Date : Create in 2019/9/22 9:57
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@Configuration
@Component
@Slf4j
public class ServerConfigInitail {

    @Autowired
    private DistributedClient distributeClient;

    @PostConstruct
    private void initialServerConfig() {
        // 启动Zookeeper 监听，监听Zookeeper上注册的服务信息
        log.info("start up Zookeeper listener,then listen all client register...");
        try {
            distributeClient.getOnlineServers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
