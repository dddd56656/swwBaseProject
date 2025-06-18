package com.sww.ad;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 应用主类：广告服务模块（sww-ad）
 *
 * 此类为整个 Spring Boot 应用的启动入口，负责初始化 IOC 容器、加载配置、
 * 启用注册中心、整合 Feign 接口及数据库访问。
 *
 * 架构角色：微服务应用中的广告子系统。
 *
 * 特别注意：
 * - 启动时会注册到注册中心，需确保配置正确（如 nacos/eureka 正常）。
 * - Mapper 扫描路径必须准确，否则数据库接口注入会失败。
 * - Feign 客户端扫描路径需包含所有远程服务接口所在包。
 */
@SpringBootApplication  // 自动启用：@Configuration、@EnableAutoConfiguration、@ComponentScan
@EnableDiscoveryClient   // 注册到注册中心（如 Nacos/Eureka），参与服务治理
@MapperScan("com.sww.ad.mapper")  // 扫描 MyBatis Mapper 接口所在包，自动实现接口注入
@Slf4j                    // 使用 SLF4J 日志门面，Lombok 自动注入 log 变量
@EnableFeignClients("com.sww") // 启用 Feign 客户端，扫描 base 模块中的远程服务接口
public class AdApplication {

    /**
     * 应用主启动方法（标准 Java 入口）。
     *
     * 功能：
     * - 启动 Spring Boot 容器
     * - 加载 Spring 上下文
     * - 输出启动标识（开发/测试用）
     *
     * 警告：
     * - 不要在此方法中添加业务逻辑
     * - 控制台输出可被日志替代（如 log.info）
     *
     * @param args 命令行参数（不建议用于业务控制）
     */
    public static void main(String[] args) {
        // 启动 Spring Boot 应用，加载配置、自动装配 Bean
        SpringApplication.run(AdApplication.class, args);

        // 控制台输出启动成功标志（建议替换为日志输出）
        System.out.println("start....");  // TODO: 替换为 log.info("AdApplication started.")
    }
}
