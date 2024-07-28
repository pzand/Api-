package com.pzand.apigateway;

import com.pzand.apicommon.service.InnerUserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PostGatewayFilterFactory implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        HttpStatus statusCode = (HttpStatus) originalResponse.getStatusCode();
        if (statusCode != HttpStatus.OK) {
            return chain.filter(exchange);//降级处理返回数据
        }

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            System.out.println(response.getStatusCode());

            HttpHeaders headers = exchange.getRequest().getHeaders();
            long interfaceId = Long.valueOf(headers.getFirst("interfaceId"));
            long userId = Long.valueOf(headers.getFirst("userId"));
            innerUserInterfaceInfoService.invokeCount(interfaceId, userId);
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
