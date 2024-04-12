package com.lxg.controller;

import com.lxg.mqtt.MqttAcceptClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    private com.lxg.mqtt.MqttSendClient MqttSendClient;

    @Autowired
    private MqttAcceptClient mqttAcceptClient;

    /**
     *  先访问该接口订阅主题，http://localhost:8080/mqtt/acceptTopic
     * @return
     */
    @GetMapping(value = "/acceptTopic")
    public Object acceptTopic() {
        mqttAcceptClient.subscribe("server:report:client:report:2",0);
        return null;
    }

    /**
     * 再调用该接口推送消息，http://localhost:8080/mqtt/publishTopic?sendMessage=%E6%B6%88%E6%81%AF%E6%B5%8B%E8%AF%95
     * @param sendMessage
     * @return
     */
    @GetMapping(value = "/publishTopic")
    public Object publishTopic(String sendMessage) {
        System.out.println("message:"+sendMessage);
        sendMessage=sendMessage+" : {\"name\":\"ljf\",\"age\":345}";
        MqttSendClient.publish(false,"client:report:2",sendMessage);
        return null;
    }
}


