package com.lxg.config;


import com.lxg.mqtt.MqttPushClient;
import com.lxg.mqtt.MqttSubClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zhouyongquan
 * @date 2023/4/27 13:16
 * @description
 */
@Component
@Configuration
@ConfigurationProperties(MqttConfiguration.PREFIX)
public class MqttConfiguration {

    private static Logger logger = LoggerFactory.getLogger(MqttConfiguration.class);

    @Autowired
    private MqttPushClient mqttPushClient;

    public static final String PREFIX = "config.mqtt";

    private String host;

    private String clientId;

    private String userName;

    private String password;

    private String topic;

    private int timeout;

    private int keepAlive;

    public String getClientid() {
        return clientId;
    }

    public void setClientid(String clientid) {
        this.clientId = clientid;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepAlive;
    }

    public void setKeepalive(int keepalive) {
        this.keepAlive = keepalive;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 连接至mqtt服务器，获取mqtt连接
     * @return
     */
    @Bean
    public MqttPushClient getMqttPushClient() {
        //连接至mqtt服务器，获取mqtt连接
        mqttPushClient.connect(host, clientId, userName, password, timeout, keepAlive);
        //一连接mqtt,就订阅默认需要订阅的主题（如test_queue）
        new MqttSubClient(mqttPushClient);
        return mqttPushClient;
    }
}