package cn.oriki.activemq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mq.xml"})
public class ActiveMQTest {

    @Autowired
    @Qualifier("jmsQueueTemplate")//必须根据名字注入
    private JmsTemplate jmsQueueTemplate;
    //注入topic的消息模版对象
    @Autowired
    @Qualifier("jmsTopicTemplate")//必须根据名字注入
    private JmsTemplate jmsTopicTemplate;

    @Test
    public void test() {

        //发q的消息
        //参数1：队列的名字，取的时候要用
        //参数2：消息创建者（spring封装对象）
        jmsQueueTemplate.send("spring.queue.hello", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("Queue消息：spring整合后的helloworld!");
                return message;
            }
        });

        //发t的消息
        jmsTopicTemplate.send("spring.topic.hello", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("Topic消息：spring整合后的helloworld!");
                return message;
            }
        });
    }
}