package com.xiaoshu.loadbalance;

import com.xiaoshu.model.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;

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
 * @Date : Create in 2019/9/22 9:35
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */

@Slf4j
public abstract class Choose {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化服务器配置信息
     */
    public void init() {
        logger.info("initial the server Configuration...");
    }

    /**
     * 策略： 可以根据服务器权重、随机、一致性HASH、轮询等负载均衡策略
     *
     * @return
     */
    public abstract ServerConfig chooseServer();

    /**
     * 刷新客户端服务器信息
     */
    public abstract void refresh();


    /**
     * 设置服务器信息
     *
     * @param servers
     */
    public abstract void setServers(CopyOnWriteArrayList<ServerConfig> servers);

    /**
     * 服务结束，关闭服务器配置信息
     */
    public void destory() {
        logger.info("服务结束，开始关闭服务器管理");
    }

}
