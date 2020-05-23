package com.cto.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Zhang Wei
 * @date 2020/5/23 15:51
 * @version v1.0.1
 */
//@Configuration
public class ApplicationContextConfig {

    //@Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
