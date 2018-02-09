package cn.oriki.springcloud.service;

import cn.oriki.springcloud.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public Item queryItemById(Long id) {
        String serviceId = "oriki-springcloud-item";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return null;
        }
        // 为了演示，在这里只获取一个实例
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
    }

}