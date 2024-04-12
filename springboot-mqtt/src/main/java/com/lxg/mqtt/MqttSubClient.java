package com.lxg.mqtt;


import com.google.common.base.Throwables;
import com.hqx.mows.common.enums.MqttTopicEnum;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhouyongquan
 * @date 2023/4/27 13:16
 * @description
 */
@Slf4j
@Component
public class MqttSubClient {

    private static Logger logger = LoggerFactory.getLogger(MqttSubClient.class);

    public MqttSubClient(MqttPushClient mqttPushClient) {
        subScribeDataPublishTopic();
    }


    private void subScribeDataPublishTopic() {
        //订阅主题
        subscribe("text_topic");
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic 主题名
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            MqttClient client = MqttPushClient.getClient();
            if (client == null) {
                return;
            }
            client.subscribe(topic, qos);
            logger.info("订阅主题:{}", topic);
        } catch (MqttException e) {
            logger.error("subscribe -> {}", Throwables.getStackTraceAsString(e));
        }
    }

}