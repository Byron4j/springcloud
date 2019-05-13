package org.byron4j.servicefeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-srv-cli1")  // 指定服务应用名
@Service
public interface IFeignServiceHi {

    @RequestMapping(value = "/hi" ,method = RequestMethod.GET)
    String sayHiFromClient(@RequestParam(value = "name") String name);
}
