package com.pzand.clinesdk.cline;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.pzand.clinesdk.model.User;

import java.util.HashMap;
import java.util.Map;

public class ApiCline {
    public static final String GATEWAY_HOST = "http://localhost:8090";

    String accessKey;
    String secretKey;

    public ApiCline(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private Map<String, String> getHeader(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);

        // 不能传递密钥
//        hashMap.put("secretKey", secretKey);

        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

    private String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String context = body + "." + secretKey;
        // 5393554e94bf0eb6436f240a4fd71282
        return md5.digestHex(context);
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        return HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        return HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
    }

    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .body(json)
                .addHeaders(getHeader(json))
                .execute();
        System.out.println(response.getStatus());
        String body = response.body();
        response.close();
        return body;
    }
}
