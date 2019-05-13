package org.byron4j.servicehystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")  // 指定熔断的回退方法
    public String sayHi(String name){
        return restTemplate.getForObject("http://eureka-srv-cli1/hi?name=" + name, String.class);
    }

    public String hiError(String name){
        return name + ",so sorry, something error.";
    }
}
