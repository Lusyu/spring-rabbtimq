package com.is666is.lpl.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送消息的服务类
 */
@Component
public class MessageSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param exchange 指定交换机
     * @param routingKey 指定路由Key
     * @param str 发送的信息
     * @return
     */
    public String send(String exchange,String routingKey,String str){
        String mesg=new Date()+"客户端: "+str;

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);

        //发送消息
        rabbitTemplate.convertAndSend(exchange,routingKey,mesg);
        return null;
    }














    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }


}
