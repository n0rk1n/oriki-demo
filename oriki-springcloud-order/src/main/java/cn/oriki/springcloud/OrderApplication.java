package cn.oriki.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

    @Bean
    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(okHttp3ClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
