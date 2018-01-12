package cn.oriki.cxf.test;

import cn.oriki.cxf.domain.User;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

public class ClientTest {


    public static void main(String[] args) {
        Collection<? extends User> collection = WebClient.create("http://127.0.0.1:8888/userService/users")
                .accept(MediaType.APPLICATION_JSON)//我要什么表述形式向服务端
                .getCollection(User.class);//如果返回N个，则使用集合方式
        System.out.println("客户端：" + collection);

        System.out.println("客户端操作完成！");
    }


}
