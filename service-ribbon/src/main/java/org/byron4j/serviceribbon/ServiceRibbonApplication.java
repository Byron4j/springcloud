package org.byron4j.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableDiscoveryClient // 使用非eureka服务发现的时候可以使用该注解
@EnableEurekaClient
@SpringBootApplication
public class ServiceRibbonApplication {

    @Bean // 向IOC注册一个bean
    @LoadBalanced   // 对这个restTemplate开启负载均衡功能
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

}
