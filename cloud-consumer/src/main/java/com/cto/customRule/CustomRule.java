package com.cto.customRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Zhang Wei
 * @date 2020/5/23 18:27
 * @version v1.0.1
 */
//@Configuration
public class CustomRule {
    //@Bean
    public IRule myCustomRule(){
        return new RandomRule();
    }
}
