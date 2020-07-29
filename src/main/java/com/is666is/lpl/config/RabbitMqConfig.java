package com.is666is.lpl.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean//DirectExchange:直连交换机
    public DirectExchange directExchange(){
        return  new DirectExchange("exchange1");
    }
    @Bean//创建队列 用来存放exchange交换机发送来的消息  true持久化
    public Queue queue1(){
        return new Queue("queue1",true);
    }
    @Bean//交换机与队列绑定关系
    public Binding binding(Queue queue1,DirectExchange directExchange){
        return BindingBuilder.bind(queue1).to(directExchange).with("k1");
    }


   /*------------------------FanoutExchange 广播交换机---------------------------------*/
   @Bean
   public FanoutExchange fanoutExchange(){
       return new FanoutExchange("fanoutExchange2");
   }
   @Bean
   public Queue queue2(){
       return new Queue("queue2",true);
   }
   @Bean
   public Queue queue3(){
        return new Queue("queue3",true);
   }
   @Bean//建立关系
   public Binding binding2(@Qualifier("queue2") Queue queue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
   }

   @Bean//建立关系
   public Binding binding3(@Qualifier("queue3")Queue queue3,FanoutExchange fanoutExchange){
       return BindingBuilder.bind(queue3).to(fanoutExchange);
   }

   /*------------------------TopicExchange 主题交换机---------------------------------*/
   @Bean
   public TopicExchange topicExchange(){

       return new TopicExchange("topicExchange");
   }
   @Bean
   public Queue queue4(){
        return new Queue("queue4",true);
   }
   @Bean
   public Queue queue5(){
       return new Queue("queue5",true);
   }
   @Bean//*：表示匹配任意一个单词 #：表示匹配任意一个或多个单词。
   public Binding binding4(@Qualifier("queue4") Queue queue4,TopicExchange topicExchange){
       return BindingBuilder.bind(queue4).to(topicExchange).with("k.*");
   }
   @Bean
   public Binding binding5(@Qualifier("queue5")Queue queue5,TopicExchange topicExchange){
       return BindingBuilder.bind(queue5).to(topicExchange).with("k.#");
   }
}
