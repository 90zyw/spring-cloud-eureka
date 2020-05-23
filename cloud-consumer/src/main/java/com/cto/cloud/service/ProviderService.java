package com.cto.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zhang Wei
 * @version v1.0.1
 * @date 2020/5/18 20:42
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER",fallback = ProviderServiceHystrix.class)
public interface ProviderService {

    @GetMapping("/provider/getData")
    String getData();

    @GetMapping("/provider/getDataError")
    String getDataError();
}
