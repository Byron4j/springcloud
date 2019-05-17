# [SpringCloud Bus 消息总线（官网翻译）](https://cloud.spring.io/spring-cloud-static/spring-cloud-bus/2.1.0.RELEASE/single/spring-cloud-bus.html)

Spring Cloud Bus使用轻量级消息代理将分布式系统的节点链接起来。然后，可以使用此代理广播状态更改(例如配置更改)或其他管理指令。

一个关键的思想是消息总线好比一个SpringBoot中的分布式的actuator，可扩展。但是，它也可以作为应用之间通讯的方式。


## 快速开始

Spring Cloud Bus消息总线通过classpath下添加的SpringBoot配置来发现自己。
为了使得总线可用，需要添加依赖：```spring-cloud-starter-bus-amqp``` 或者 ```spring-cloud-starter-bus-kafka```。SpringCloud会处理剩下的工作。确保消息代理（RabbitMQ、Kafka）是配置可用的。

当在本地运行的时候，你不需要做任何事情。如果你在远程运行，使用SpringCloud连接器或者SpringBoot约定定义代理凭据，像以下Rabbit示例一样：

**application.yml**:

```yaml
spring:
    rabbitmq:
      host: mybroker.com
      port: 5672
      username: user
      password: secret
```

消息总线目前支持将消息发送到正在侦听某个特定服务的所有节点或所有节点(由Eureka定义)。
```/bus/*``` 执行器命名空间有一些http端点。现在，实现了两个。
第一个是 ```/bus/env``` ，发送 key/value 对来更新每个节点的Spring环境。
第二个是 ```/bus/refresh``` ，重新加载每个应用的配置，因为它们在之前已经连通了```/refresh```端点。


>SpringCloud Bus消息总线通过```RabbitMQ```和```Kafka```启动，因为这两者是最常用的实现（可以考虑使用```RocketMQ```实现）。但是，SpringCloud Stream是非常灵活的，并且绑定器使用的是```spring-cloud-bus```。

 


#### 参考资料

- [SpringCloud Bus 消息总线（官网）](https://cloud.spring.io/spring-cloud-static/spring-cloud-bus/2.1.0.RELEASE/single/spring-cloud-bus.html)