package org.byron4j.eurekasrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动一个服务注册中心，只需要一个注解@EnableEurekaServer，
 * 这个注解需要在springboot工程的启动application类上加
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSrvApplication.class, args);
    }

}
