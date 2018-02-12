package cn.oriki.springcloud.feign;

import cn.oriki.springcloud.domain.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 申明这是一个Feign客户端，并且指明服务id
@FeignClient(value = "oriki-springcloud-item")
public interface ItemFeignClient {

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Item queryItemById(@PathVariable("id") Long id);

}
