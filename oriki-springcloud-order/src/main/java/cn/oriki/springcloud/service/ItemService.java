package cn.oriki.springcloud.service;

import cn.oriki.springcloud.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${oriki.item.url}")
    private String itemProjectUrl;

    public Item queryItemById(Long id) {
        return this.restTemplate.getForObject(itemProjectUrl + "/item/"
                + id, Item.class);
    }

}