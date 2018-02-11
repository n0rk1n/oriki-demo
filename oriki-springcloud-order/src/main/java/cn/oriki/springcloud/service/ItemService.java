package cn.oriki.springcloud.service;

import cn.oriki.springcloud.domain.Item;
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

    public Item queryItemById(Long id) {
        String serviceId = "oriki-springcloud-item";
        String url = "http://" + serviceId + "/item/" + id;
        return this.restTemplate.getForObject(url, Item.class);
    }

}