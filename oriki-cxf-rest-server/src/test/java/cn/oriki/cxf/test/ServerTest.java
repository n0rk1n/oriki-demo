package cn.oriki.cxf.test;

import cn.oriki.cxf.domain.User;
import cn.oriki.cxf.service.impl.UserServiceImpl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class ServerTest {

    public static void main(String[] args) {

        //1.创建工厂
        JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
        //2.设置属性
        serverFactory.setAddress("http://127.0.0.1:8888/userService");
        //设置实现对象
        serverFactory.setServiceBean(new UserServiceImpl());
        //设置资源表述转换对象类型
        serverFactory.setResourceClasses(User.class);

        //3.创建并发布服务
        serverFactory.create();
        System.out.println("服务发布成功！");

    }

}
