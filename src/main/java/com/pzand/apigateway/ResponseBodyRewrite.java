package com.pzand.apigateway;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class ResponseBodyRewrite implements RewriteFunction<String, String> {

    public ResponseBodyRewrite() {
//        this.objectMapper = objectMapper;
    }

    @Override
    public Publisher<String> apply(ServerWebExchange exchange, String body) {
        log.info("响应结果：" + body);
        return Mono.just(body);
    }
}
