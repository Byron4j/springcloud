package org.byron4j.servicefeign.controller;

import org.byron4j.servicefeign.feign.IFeignServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    IFeignServiceHi iFeignServiceHi;

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name){
        return iFeignServiceHi.sayHiFromClient(name);
    }
}
