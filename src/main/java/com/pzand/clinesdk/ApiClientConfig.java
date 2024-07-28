package com.pzand.clinesdk;

import com.pzand.clinesdk.cline.ApiCline;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("api.client")
@Data
@ComponentScan
public class ApiClientConfig {
    String accessKey;
    String secretKey;

    @Bean
    public ApiCline apiCline() {
        return new ApiCline(accessKey, secretKey);
    }
}
