package com.lxg.mqtt;


import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhouyongquan
 * @date 2023/4/27 13:16
 * @description
 */
@Slf4j
@Component
public class MqttPushClient{

    private static Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    @Autowired
    private PushCallback pushCallback;

    private static MqttClient client;


    public static void setClient(MqttClient client) {
        MqttPushClient.client = client;
    }

    public static MqttClient getClient() {
        return client;
    }

    public void connect(String host, String clientID, String username, String password, int timeout, int keepalive) {
        MqttClient client;
        try {
            client = new MqttClient(host, clientID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keepalive);
            MqttPushClient.setClient(client);
            try {
                //设置回调类
                client.setCallback(pushCallback);
                IMqttToken iMqttToken = client.connectWithResult(options);
                boolean complete = iMqttToken.isComplete();
                logger.info("MQTT连接"+(complete?"成功":"失败"));
            } catch (Exception e) {
                logger.error("connect -> {}", Throwables.getStackTraceAsString(e));
            }
        } catch (Exception e) {
            logger.error("connect -> {}", Throwables.getStackTraceAsString(e));
        }

    }

    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic 主题名
     * @param pushMessage 消息
     */
    public void publish(String topic, String pushMessage) {
        publish(2, false, topic, pushMessage);
    }

    /**
     * 发布
     *
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = MqttPushClient.getClient().getTopic(topic);
        if (null == mTopic) {
            logger.error("主题不存在:{}",mTopic);
        }
        try {
            mTopic.publish(message);
        } catch (Exception e) {
            logger.error("publish -> {}", Throwables.getStackTraceAsString(e));
        }
    }

}