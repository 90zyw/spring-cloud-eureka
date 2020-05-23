package com.cto.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Zhang Wei
 * @date 2020/5/23 22:39
 * @version v1.0.1
 */
@Service
public class ProviderService {

    @Value("${server.port}")
    private String port;

    /**
     * 正确返回
     * @return
     */
    public String getDataOk(){
        return port + "\t getDataOk";
    }

    /**
     * 故意异常
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDateErrorFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String getDataError() {
        int i = 10/0;
        return port + "\t getDataError";
    }

    public String getDateErrorFallBack(){
        return port + "服务异常啦,待会再试";
    }
}
