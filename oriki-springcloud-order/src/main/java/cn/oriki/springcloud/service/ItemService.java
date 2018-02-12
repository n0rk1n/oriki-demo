package cn.oriki.springcloud.service;

import cn.oriki.springcloud.domain.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallBack")// 调用失败后执行的请求
    public Item queryItemById(Long id) {
        String serviceId = "oriki-springcloud-item";
        String url = "http://" + serviceId + "/item/" + id;
        return this.restTemplate.getForObject(url, Item.class);
    }

    public Item queryItemByIdFallBack(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

}