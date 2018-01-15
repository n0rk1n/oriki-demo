package cn.oriki.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQTest {

    @Test
    public void test() throws Exception{
        //第一步：建立连接
        //参数1：用户名
        //参数2：密码
        //参数：节点连接url,客户端连接端口是61616
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory
				("admin", "admin", "tcp://172.16.191.201:61616");

        //获取连接
        Connection connection = connectionFactory.createConnection();

        //开启连接
        connection.start();

        //第二步：获取session操作对象，进行操作
        //参数1：是否开启手动事务，true:事务需要手动提交，mq才会将消息持久化保存，和数据库类似
        //参数2：一旦手动事务，第二个参数无效，但一般写自动。自动处理消息机制
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //1)先制造一个消息队列（容器）,这里是一个q的消息
        Queue queue = session.createQueue("myQueue");

        //2)根据消息，创建一个生产者
        //destination:消息类型的超级接口
        //这里放入q消息，意味着该生产者只能生产q的消息，队列的名字helloQueue
        MessageProducer producer = session.createProducer(queue);

        //3)生产消息：
        //发送10条消息
        for (int i = 0; i < 10; i++) {
            //message:jms中消息的超级接口，也就是说消息都必须封装为messager才能发出去，将来才能接收
            TextMessage message = session.createTextMessage("hello world:"+i);
            producer.send(message);
        }

        //提交事务
        session.commit();

        //关闭连接
        producer.close();
        session.close();
        connection.stop();
        connection.close();

    }
}
