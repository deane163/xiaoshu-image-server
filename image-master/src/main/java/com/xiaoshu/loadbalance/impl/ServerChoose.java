package com.xiaoshu.loadbalance.impl;

import com.xiaoshu.loadbalance.Choose;
import com.xiaoshu.model.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

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
 * @Date : Create in 2019/9/22 9:38
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */
@Component
@Slf4j
public class ServerChoose extends Choose {

    private ReentrantLock lock = new ReentrantLock();

    private CopyOnWriteArrayList<ServerConfig> servers;

    private Random random = new Random();
    private int factor;
    private boolean init = false;

    @Override
    public void init() {
        lock.lock();
        try {
            if (!init) {
                refresh();
                this.init = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public ServerConfig chooseServer() {
        if (!init) {
            init();
        }
        ServerConfig selector = null;
        int rv = random.nextInt(factor);
        while (null == selector) {
            for (int i = servers.size() - 1; i >= 0; i--) {
                ServerConfig server = servers.get(i);
                if (rv > server.getStartWeight()) {
                    selector = server;
                    break;
                }
            }
        }
        return selector;
    }

    @Override
    public void refresh() {
        lock.lock();
        try {
            this.factor = 0;
            log.info("====>Initial the ImageMaster,The Pools client size is =====> {}", servers.size());
            for (Iterator<ServerConfig> iter = servers.iterator(); iter.hasNext(); ) {
                ServerConfig server = iter.next();
                server.setStartWeight(factor);
                factor += server.getWeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public CopyOnWriteArrayList<ServerConfig> getServers() {
        return servers;
    }

    @Override
    public void setServers(CopyOnWriteArrayList<ServerConfig> servers) {
        this.servers = servers;
    }
}
