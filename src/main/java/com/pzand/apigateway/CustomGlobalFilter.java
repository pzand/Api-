package com.pzand.apigateway;

import com.pzand.apicommon.model.entity.InterfaceInfo;
import com.pzand.apicommon.model.entity.User;
import com.pzand.apicommon.service.InnerInterfaceInfoService;
import com.pzand.apicommon.service.InnerUserInterfaceInfoService;
import com.pzand.apicommon.service.InnerUserService;
import com.pzand.clinesdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    public static final String INTERFACE_HOST = "http://localhost:8123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        String path = INTERFACE_HOST + request.getPath().value();
        String method = request.getMethod().toString();
        log.info("请求唯一标识：" + request.getId());
        log.info("请求路径：" + path);
        log.info("请求方法：" + method);
        log.info("请求参数：" + request.getQueryParams());
        log.info("请求来源地址：" + request.getRemoteAddress());
        log.info("custom global filter");

        // 2. 访问控制 - 黑白名单

        // 3. 用户鉴权 (判断 ak、sk 是否合法)
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");

        // todo 实际应该从数据库中查是否已经分配给用户
        User invokeUser;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
            throw new RuntimeException("无权限");
        }
        if (invokeUser == null) {
            throw new RuntimeException("无权限");
        }


        // 随机数是否一致
        if (nonce == null || Long.parseLong(nonce) > 1000000) {
            throw new RuntimeException("无权限");
        }

        // 时间和当前时间不能超过 5 分钟
        long currentTime = System.currentTimeMillis() / 1000;
        final long FIVE_MINUTES = 60 * 5L;
        if (timestamp == null || currentTime - Long.parseLong(timestamp) >= FIVE_MINUTES) {
            throw new RuntimeException("无权限");
        }

        // 实际情况中是从数据库中查出 secretKey
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtils.genSign(body, secretKey);
        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限");
        }

        // 4. 请求的模拟接口是否存在，以及请求方法是否匹配
        InterfaceInfo interfaceInfo;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInterfaceInfo error", e);
            throw new RuntimeException("无权限");
        }
        if (interfaceInfo == null) {
            throw new RuntimeException("无权限");
        }

        boolean res = innerUserInterfaceInfoService.hasInvokeCount(interfaceInfo.getId(), invokeUser.getId());
        if (!res) {
            throw new RuntimeException("没有调用次数");
        }

//        headers.add("interfaceId", "1");
        ServerHttpRequest.Builder mutateRequest = exchange.getRequest().mutate();
        mutateRequest.header("interfaceId", String.valueOf(interfaceInfo.getId()));
        mutateRequest.header("userId", String.valueOf(invokeUser.getId()));
        // 5. 调用模拟接口 + 6. 响应日志
        return chain.filter(exchange.mutate().request(mutateRequest.build()).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
