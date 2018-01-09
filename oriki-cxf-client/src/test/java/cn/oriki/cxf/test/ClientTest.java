package cn.oriki.cxf.test;

import cn.oriki.cxf.client.stub.MobileAddressService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ClientTest {
    public static void main(String[] args) {
        // 1.创建一个代理工厂
        JaxWsProxyFactoryBean proxyFactory=new JaxWsProxyFactoryBean();
        //2.设置属性
        //访问地址
        proxyFactory.setAddress("http://127.0.0.1:8888/mobileAddress?wsdl");
        //代理对象类型
        proxyFactory.setServiceClass(MobileAddressService.class);

        //3.创建代理对象
        MobileAddressService ma=(MobileAddressService)proxyFactory.create();

        //使用代理对象调用方法
        String address = ma.getAddressByMobileNo("18516566511");

        System.out.println(address);

    }


}
