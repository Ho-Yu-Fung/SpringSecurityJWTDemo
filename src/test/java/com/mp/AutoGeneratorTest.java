package com.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class AutoGeneratorTest {

    private static final String OUT_PUT_DIR = System.getProperty("user.dir") + "/src/main/java";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shiro?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PWD = "mysql";
    private static final String AUTHOR = "Ho";

    public static void main(String[] args) {
        //代码生成
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(AUTHOR)
                .setFileOverride(false)
                .setOutputDir(OUT_PUT_DIR)
                .setMapperName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(globalConfig);
        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername(DB_USER_NAME)
                .setPassword(DB_PWD)
                .setUrl(DB_URL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);
        //pack配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.mp")
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setController("controller");

        mpg.setPackageInfo(pc);
        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("tb_user","tb_role","tb_perm")//映射的表
                .setTablePrefix("tb_")
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)//是否使用lombok开启注解
                .setRestControllerStyle(true);
        //自动填充配置
        TableFill gmtCreate = new TableFill("created_time", FieldFill.INSERT);
        TableFill gmtUpdate = new TableFill("updated_time", FieldFill.INSERT_UPDATE);
            ArrayList<TableFill> tableFills = new ArrayList<>();
            tableFills.add(gmtCreate);
            tableFills.add(gmtUpdate);
            strategy.setTableFillList(tableFills);
            mpg.setStrategy(strategy);

        mpg.execute();//执行
    }
}
