package com.xiaoshu.config.zookeeper;

import com.xiaoshu.loadbalance.impl.ServerChoose;
import com.xiaoshu.model.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

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
 * @Date : Create in 2019/9/22 9:58
 * <p>
 * Copyright (C)2013-2019 小树盛凯科技 All rights reserved.
 */

@Component
@Slf4j
public class DistributedClient {
    //先在zookeeper服务器上创建一个/servers节点，防止第一次没有初始化时，启动报错!
    private static final String groupNode = "/servers";
    static ZooKeeper zkCli = null;
    volatile static List<String> servers = null;
    @Value("${zookeeper.configuration.center}")
    private String zookeeperCenter;

    @Autowired
    private ServerChoose choose;

    public static void waitUntilConnected(ZooKeeper testZooKeeper, CountDownLatch testLatch) {
        if (testZooKeeper.getState() == ZooKeeper.States.CONNECTING) {
            try {
                testLatch.await();
            } catch (InterruptedException err) {
                log.error("Latch exception", err.getMessage());
            }
        }
    }

    /**
     * 获取在线的服务节点
     */
    public void getOnlineServers() throws Exception {
        CountDownLatch sampleLatch = new CountDownLatch(1);
        Watcher sampleWatcher = new ConnectedWatcher(sampleLatch);
        // 构造一个zookeeper的客户端
        zkCli = new ZooKeeper(zookeeperCenter, 2000, sampleWatcher);
        /* 只有当zkCli链接成功（状态为 SyncConnected)时，此函数调用才结束 */
        waitUntilConnected(zkCli, sampleLatch);
        /*接下来就可以继续zkCli访问了，避免因为zkCli未连接成功时的访问出错
         一进来就调用updateServers方法获取当前在线的服务节点*/
        updateServers();
    }

    /**
     * 从zookeeper中获取服务节点信息的具体实现方法
     */
    public void updateServers() throws Exception {
        // 构造一个list用来保存服务节点信息
        List<String> serverList = new ArrayList<>();
        //create  /servers 123
        if (null == zkCli.exists(groupNode, false)) {
            zkCli.create(groupNode, "client".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // 先拿到servers下的子节点名称列表,并对父节点servers注册监听器
        List<String> children = zkCli.getChildren("/servers", true);
        //遍历获取每一个子节点所保存的数据——服务节点信息
        for (String child : children) {
            byte[] data = zkCli.getData("/servers/" + child, false, null);
            String serverName = new String(data, "utf-8");
            serverList.add(serverName);
            log.info("The Online Image slave is :{}", serverName);
        }
        this.refreshServers(serverList);
    }

    /**
     * 模拟客户端程序的业务功能
     */
    public void handle() throws Exception {
        log.info("The Client start handle the business function...");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * refresh all servers
     *
     * @param serverList
     */
    private void refreshServers(List<String> serverList) {
        servers = serverList;
        CopyOnWriteArrayList<ServerConfig> serverConfigList = new CopyOnWriteArrayList<>();
        //初始化服务器
        for (String server : serverList) {
            ServerConfig serverConfig = new ServerConfig();
            serverConfig.setIp(server);
            serverConfig.setWeight(100);
            serverConfigList.add(serverConfig);
        }
        choose.setServers(serverConfigList);
        choose.refresh();
        log.info("finish refresh the all servers ...");
    }

    public class ConnectedWatcher implements Watcher {
        private CountDownLatch connectedLatch;

        /* CountDownLatch实例初始化时设为1即可 */
        public ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;
        }

        /* ZK连接成功时，计数器由1减为0 */
        @Override
        public void process(WatchedEvent event) {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                connectedLatch.countDown();
            }
            // 从zookeeper中获取最新的服务节点信息，从新刷新服务器权重
            try {
                updateServers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
