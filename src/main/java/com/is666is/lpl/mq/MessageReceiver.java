package com.is666is.lpl.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "queue4")
public class MessageReceiver {
    @RabbitHandler//第一个形参是消息
    public void process(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        Thread.sleep(2000);
        System.out.println("睡眠2s");
        //告诉消息队列这条消息被消费 可以删除这消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("消费者1 queue4:"+msg);
    }
}
