package com.sww.edu.boss;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.sww.edu")
@EnableCreateCacheAnnotation
@EnableFeignClients("com.sww.edu")
@ComponentScan({"com.sww.edu"})
public class swwEduBossApplication {
    public static void main(String[] args) {
        SpringApplication.run(swwEduBossApplication.class, args);
    }
}
