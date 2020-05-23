package com.cto.cloud.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.result.view.RequestContext;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 
 * @author Zhang Wei
 * @date 2020/5/19 23:52
 * @version v1.0.1
 */
@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {
    /**
     * 数字越小 优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //判断第一个参数是不是name,如果是继续,不是则返回自定义错误 Token验证也是一个道理,从header中获取
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if(StringUtils.isEmpty(name)){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            byte[] bytes = "{\"status\":\"-1\",\"msg\":\"error\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
        return chain.filter(exchange);
    }
}
