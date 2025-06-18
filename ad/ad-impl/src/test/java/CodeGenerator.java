import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatis-Plus 自动代码生成工具类
 *
 * 主要功能：
 * - 通过控制台输入模块名、表名，自动生成实体类、Mapper、Service、Controller 等 Java 文件
 * - 避免重复手写样板代码，提高开发效率
 *
 * 使用说明：
 * - 直接运行 main 方法，按提示输入模块名和表名（多个表用逗号分隔）
 * - 输出路径为 `${projectPath}/ad-impl/src/main/java`
 * - Mapper XML 文件输出至 `src/main/resources/mapper/模块名/`
 *
 * 依赖模板引擎：Freemarker（也可切换为 Velocity）
 *
 * ⚠ 注意事项：
 * - 表前缀将被自动去除（由 setTablePrefix 决定）
 * - 数据库配置为硬编码，建议抽出为 config 文件或参数
 * - 模板路径硬编码，适配 Freemarker 引擎
 */
public class CodeGenerator {

    /**
     * 从控制台读取用户输入（用于模块名或表名等动态参数）
     *
     * @param tip 提示信息，例如“模块名”或“表名”
     * @return 控制台输入的非空字符串
     * @throws MybatisPlusException 如果用户未输入或输入为空
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 主方法：配置并执行 MyBatis-Plus 代码生成流程
     */
    public static void main(String[] args) {
        // Step 1: 初始化代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // Step 2: 全局配置项（输出路径、作者、ID 策略等）
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/ad-impl/";
        gc.setOutputDir(projectPath + "src/main/java"); // Java 代码输出路径
        gc.setAuthor("su wei wei");                     // 作者信息，用于类注释
        gc.setOpen(false);                              // 生成后是否自动打开资源管理器
        gc.setIdType(IdType.AUTO);                      // 主键策略：数据库自增
        // gc.setSwagger2(true);                        // 是否生成 Swagger 注解（若使用 Swagger）
        mpg.setGlobalConfig(gc);

        // Step 3: 数据源配置（请根据实际项目替换成配置文件读取方式）
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://113.31.119.154:3306/edu_ad?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("edurw");
        dsc.setPassword("edurw");
        mpg.setDataSource(dsc);

        // Step 4: 包名配置（控制类、实体、Mapper 所属包结构）
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));            // 模块名（例如 user、course）
        pc.setParent("swwBaseProject");                // 基础包名（父包）
        mpg.setPackageInfo(pc);

        // Step 5: 自定义文件输出配置（可跳过或扩展 Mapper XML 位置）
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // 自定义参数映射，可用于模板传值（目前未用）
            }
        };

        // 自定义 Mapper XML 输出路径模板（Freemarker 引擎）
        String templatePath = "/templates/mapper.xml.ftl";

        // 输出配置列表（目前只配置 Mapper XML 文件的自定义输出路径）
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // Mapper XML 输出位置：src/main/resources/mapper/模块名/EntityMapper.xml
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // Step 6: 模板配置（是否覆盖默认模板）
        TemplateConfig templateConfig = new TemplateConfig();
        // 设置为 null 表示禁用默认 XML 生成（已使用自定义输出）
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // Step 7: 策略配置（驼峰命名、是否使用 Lombok、是否生成 Rest 风格 Controller 等）
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);         // 表名 -> 实体类驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);   // 字段名 -> 属性名驼峰命名
        strategy.setEntityLombokModel(true);                           // 实体类启用 Lombok
        strategy.setRestControllerStyle(true);                         // Controller 使用 @RestController
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));  // 目标表（支持多个）
        strategy.setControllerMappingHyphenStyle(true);                // URL 映射风格（a-b → aB）
        strategy.setTablePrefix(pc.getModuleName() + "_");             // 去除表前缀（例如：user_xxx → Xxx）
        mpg.setStrategy(strategy);

        // Step 8: 设置模板引擎并执行生成
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();  // 执行代码生成
    }
}
