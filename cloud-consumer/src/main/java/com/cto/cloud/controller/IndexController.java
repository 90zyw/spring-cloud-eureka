package com.cto.cloud.controller;

import com.cto.cloud.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Zhang Wei
 * @date 2020/5/23 15:08
 * @version v1.0.1
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class IndexController {

    @Autowired
    private ProviderService providerService;
    //基于Ribbon的实现方式
    /*private final String providerService = "http://CLOUD-PROVIDER/";
    private final RestTemplate restTemplate;
    @Autowired
    public IndexController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/getData")
    public Object getData(){
        return restTemplate.getForEntity(providerService + "/provider/getData",String.class);
    }*/
    //基于Feign的实现方式

    @GetMapping(value = "/getData")
    public Object getData(){
        return providerService.getData();
    }

    @GetMapping(value = "/getDataError")
    public Object getDataError(){
        return providerService.getDataError();
    }

    @GetMapping(value = "/getId/{id}")
    public Object getId(@PathVariable("id") Integer id){
        return providerService.getId(id);
    }
}
