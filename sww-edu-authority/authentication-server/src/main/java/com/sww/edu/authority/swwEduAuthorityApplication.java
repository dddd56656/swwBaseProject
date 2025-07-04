package com.sww.edu.authority;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableCreateCacheAnnotation
@MapperScan("com.sww.edu.authority.mapper")
public class swwEduAuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(swwEduAuthorityApplication.class, args);
    }
}
