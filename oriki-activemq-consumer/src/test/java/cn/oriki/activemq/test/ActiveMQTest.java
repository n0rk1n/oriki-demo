package cn.oriki.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQTest {

    @Test
    public void test() throws Exception {
        //第一步：建立连接
        //连接工厂：所有参数默认
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                ("admin", "admin", "tcp://172.16.191.201:61616");

        //获取连接
        Connection connection = connectionFactory.createConnection();

        //开启连接
        connection.start();

        //第二步：获取session操作对象，进行操作
        //参数1：是否开启手动事务，true:事务需要手动提交，mq才会将消息持久化保存，和数据库类似
        //参数2：一旦手动事务，第二个参数无效，但一般写自动。自动处理消息机制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //1)先制造一个消息队列（容器）,这里是一个q的消息-将来就从该队列中读消息
        Queue queue = session.createQueue("myQueue");

        //2)根据消息，创建一个消费者，该消费者只能消费指定队列类型的队列的消息
        MessageConsumer consumer = session.createConsumer(queue);

        //3)获取消息的内容（消息对象）
        while (true) {
//			Message message = consumer.receive();//获取不到消息立即返回空
            Message message = consumer.receive(5000);//如果多少毫秒获取不到消息，则直接返回空消息。
            if (message != null) {
                String str = ((TextMessage) message).getText();
                System.out.println(str);
            } else {
                System.out.println("消息处理完毕，退出消费。。。");
                break;
            }
        }

        //关闭连接
        consumer.close();
        session.close();
        connection.stop();
        connection.close();
    }

    @Test
    public void test2() throws Exception{
        //第一步：建立连接
        //连接工厂：所有参数默认
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                ("admin", "admin", "tcp://172.16.191.201:61616");

        //获取连接
        Connection connection = connectionFactory.createConnection();

        //开启连接
        connection.start();

        //第二步：获取session操作对象，进行操作
        //参数1：是否开启手动事务，true:事务需要手动提交，mq才会将消息持久化保存，和数据库类似
        //参数2：一旦手动事务，第二个参数无效，但一般写自动。自动处理消息机制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //1)先制造一个消息队列（容器）,这里是一个q的消息-将来就从该队列中读消息
        Queue queue = session.createQueue("myQueue");

        //2)根据消息，创建一个消费者，该消费者只能消费指定队列类型的队列的消息
        MessageConsumer consumer = session.createConsumer(queue);

        //3)获取消息的内容（消息对象）
        //MessageListener:jms消息监听超级接口
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                try {
                    String str = ((TextMessage)message).getText();
                    System.out.println(str);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        //junit当代码运行结束，会强制终止jvm
        while (true) {

        }
    }
}
