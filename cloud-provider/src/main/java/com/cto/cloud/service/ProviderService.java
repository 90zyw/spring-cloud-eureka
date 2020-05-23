package com.cto.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port + "\t getDataError 等3秒";
    }

    public String getDateErrorFallBack(){
        return port + "服务超时了,我只等你2秒";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "getIdErrorFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50") //失败率达到多少
    })
    public String getId(Integer id) throws Exception {
        if(id < 0){
            throw new Exception("主动异常");
        }
        return port + "-----" + id;
    }
    public String getIdErrorFallBack(Integer id){
        return port + "被熔断了" + id;
    }
}
