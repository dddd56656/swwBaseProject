package com.sww.edu.boss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Swagger配置属性
 **/
@Data
@Component
@ConfigurationProperties(prefix = "edu.swagger")
public class SwaggerProperties {

    /**
     * 是否启用Swagger
     */
    private boolean enable;

    /**
     * 扫描的基本包
     */
    @Value("${edu.swagger.base.package}")
    private String basePackage;

    /**
     * 联系人邮箱
     */
    @Value("${edu.swagger.contact.email}")
    private String contactEmail;

    /**
     * 联系人名称
     */
    @Value("${edu.swagger.contact.name}")
    private String contactName;

    /**
     * 联系人网址
     */
    @Value("${edu.swagger.contact.url}")
    private String contactUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 网址
     */
    private String url;

    /**
     * 版本
     */
    private String version;

    /**
     * 接口分组
     */
    private String groupName;

    /**
     * 自定义参数配置
     */
    @NestedConfigurationProperty
    private List<ParameterConfig> parameterConfig;

    /**
     * 自定义参数配置
     */
    @Data
    public static class ParameterConfig {

        /**
         * 名称
         */
        private String name;

        /**
         * 描述
         */
        private String description;

        /**
         * 参数类型
         * header, cookie, body, query
         */
        private String type = "head";

        /**
         * 数据类型
         */
        private String dataType = "String";

        /**
         * 是否必填
         */
        private boolean required;

        /**
         * 默认值
         */
        private String defaultValue;

    }
}
