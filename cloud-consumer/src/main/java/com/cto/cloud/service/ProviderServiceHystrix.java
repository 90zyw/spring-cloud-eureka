package com.cto.cloud.service;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Zhang Wei
 * @date 2020/5/23 23:57
 * @version v1.0.1
 */
@Component
public class ProviderServiceHystrix implements ProviderService{
    @Override
    public String getData() {
        return "服务异常了";
    }

    @Override
    public String getDataError() {
        return "服务异常了";
    }
}
