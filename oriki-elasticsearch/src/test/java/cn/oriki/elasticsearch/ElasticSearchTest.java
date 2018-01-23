package cn.oriki.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

import java.net.InetAddress;

public class ElasticSearchTest {


    @Test
    public void testCreateIndex() throws Exception {
        /**
         * 创建搜索服务器对象
         * 默认端口号9300
         */
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));

        //创建索引，参数为索引的名字
        client.admin().indices().prepareCreate("index_blog").get();

        client.close();
    }

    //建立索引和删除索引:相当于建表和删除表
    @Test
    public void testDeleteIndex() throws Exception {
        //创建连接搜索服务器对象：客户端核心操作对象
        //默认的服务的端口是9300
        Client client = TransportClient
                .builder()
                .build()
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("172.16.191.201"), 9300));


        //删除索引
        client.admin().indices().prepareDelete("index_blog").get();

        //关闭连接
        client.close();

    }
}
