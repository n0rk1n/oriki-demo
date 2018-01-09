package cn.oriki.cxf.test;

import cn.oriki.cxf.service.MobileAddressService;
import cn.oriki.cxf.service.impl.MobileAddressServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class ServerTest {

    public static void main(String[] args) {
        //发布服务
        //1.创建工厂对象
        JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
        //2.设置属性
        //接口：暴露出去
        serverFactory.setServiceClass(MobileAddressService.class);
        //实现
        serverFactory.setServiceBean(new MobileAddressServiceImpl());
        //地址
        serverFactory.setAddress("http://127.0.0.1:8888/mobileAddress");
        //3.发布服务
        serverFactory.create();
        System.out.println("服务发布成功！");
    }

}
