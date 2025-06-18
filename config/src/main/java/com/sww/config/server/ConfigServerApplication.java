package com.sww.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

// 标记为Spring Boot应用入口（自动配置/组件扫描等）
@SpringBootApplication

// 开启服务注册与发现（如Eureka/Nacos，方便微服务互相感知）
@EnableDiscoveryClient

// 启用配置中心功能（Config Server，统一管理分布式配置）
@EnableConfigServer

public class ConfigServerApplication {

    // 主方法（标准Java启动入口）
    public static void main(String[] args) {
        // 启动Spring Boot应用（自动初始化所有Bean/服务/端口等）
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

