package cn.oriki.activemq.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicConsumer2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String str;
        try {
            str = ((TextMessage) message).getText();
            System.out.println(str + "新的的处理人：" + this.getClass().getSimpleName());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
