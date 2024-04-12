package com.lxg.mqtt;


import com.google.common.base.Throwables;
import com.hqx.mows.core.service.PlcService;
import com.lxg.config.MqttConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhouyongquan
 * @date 2023/4/27 13:16
 * @description
 */
@Slf4j
@Component
public class PushCallback implements MqttCallback {

    private static Logger logger = LoggerFactory.getLogger(PushCallback.class);

    @Autowired
    private MqttConfiguration mqttConfiguration;

    @Autowired
    private PlcService plcService;

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("连接断开，正在重连");
        MqttPushClient mqttPushClient = mqttConfiguration.getMqttPushClient();
        if (null != mqttPushClient) {
            mqttPushClient.connect(mqttConfiguration.getHost(), mqttConfiguration.getClientid(), mqttConfiguration.getUsername(),
                    mqttConfiguration.getPassword(), mqttConfiguration.getTimeout(), mqttConfiguration.getKeepalive());
            logger.info("已重连");
        }

    }

    /**
     * 发送消息，消息到达后处理方法
     *
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    /**
     * 订阅主题接收到消息处理方法
     *
     * @param topic
     * @param message
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void messageArrived(String topic, MqttMessage message) {
        //处理接收到的数据
    }

}
