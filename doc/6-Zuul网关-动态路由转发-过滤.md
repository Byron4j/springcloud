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
- 


#### 参考资料

- [Zuul概览图](http://www.cnblogs.com/davidwang456/p/6411016.html)
- [Zuul wiki](https://github.com/Netflix/zuul/wiki/Getting-Started-2.0)
- [金丝雀测试即灰度测试](https://www.cnblogs.com/apanly/p/8784096.html)

