package cn.oriki.springcloud.web.controller;

import org.springframework.beans.factory.annotation.Value;
        import org.springframework.cloud.context.config.annotation.RefreshScope;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @GetMapping("/config")
    public String config(){
        return jdbcUrl;
    }
}
