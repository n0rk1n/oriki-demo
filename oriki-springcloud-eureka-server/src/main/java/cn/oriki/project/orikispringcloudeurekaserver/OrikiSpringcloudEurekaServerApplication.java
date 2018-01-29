package cn.oriki.project.orikispringcloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OrikiSpringcloudEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrikiSpringcloudEurekaServerApplication.class, args);
    }
}
