# Zuul网关-动态路由转发-过滤

>[Zuul是一个边缘服务，提供动态路由、监视、弹性、安全性等等](https://github.com/Netflix/zuul)

回顾一下，微服务需要的几大组件： 
- 服务注册与发现
- 服务消费
- 负载均衡
- 断路器
- 服务路由
- 配置管理

一种常见的SpringCloud为服务体系中负载均衡方式是： 客户端经由负载均衡（Nginx、zuul），再到达服务网关（zuul集群），然后再到具体的服务，服务同意注册到高可用的服务注册中心集群，服务的所有配置文件由配置服务管理。

Zuul 主要功能是转发、过滤器， 如 /api/pay 转发到 pay 服务， /api/repay 转发到 repay 服务。
Zuul默认和Ribbon结合实现了负载均衡的功能。

Zuul 功能：

- 授权验证
- 压力测试
- [金丝雀测试-灰度测试](https://www.cnblogs.com/apanly/p/8784096.html)
- 动态路由
- 服务迁移
- 卸载
- 安全机制
- 静态响应处理
- 主动交互管理


## 准备

### 创建工程

创建一个 service-zuul 工程。

引入依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

### 在启动累使用 ```@EnableZuulProxy``` 注解，开启 zuul 功能

```java
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}

}

```

### 修改配置

修改application.properties文件；

```properties


spring.application.name=service-zuul
#  本实例端口
server.port=8767

# eureka 注册中心
registry.port=8761
eureka.instance.hostname=localhost
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${registry.port}/eureka/

# 添加zuul路由配置

# 将 api-a 的请求都转发给 service-ribbon
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=service-ribbon


# 将 api-a 的请求都转发给 service-fign
zuul.routes.api-b.path=/api-b/**
zuul.routes.api-b.serviceId=service-feign

```

会将 localhost:8768/api-a/ 的所有请求都转发给 service-ribbon；

会将 localhost:8768/api-a/ 的所有请求都转发给 service-feign；

### 测试

启动服务注册中心： eureka-srv、eureka-srv-cli1 的两个实例、service-ribbon、service-feign 实例。

在浏览器访问： localhost:8767/api-a/hi?name=zyy , 看到：

>

#### 参考资料

- [Zuul概览图](http://www.cnblogs.com/davidwang456/p/6411016.html)
- [Zuul wiki](https://github.com/Netflix/zuul/wiki/Getting-Started-2.0)
- [金丝雀测试即灰度测试](https://www.cnblogs.com/apanly/p/8784096.html)

