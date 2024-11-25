package com.zhy.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.zhy.dataobject.BaseDO;

import java.sql.Types;
import java.util.Collections;
import java.util.Scanner;

public class MybatisplusGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要创建的表名，以逗号分隔：");
        String tableNames = scanner.nextLine();

        String rootPath = "/Users/qiaobusi/work/codeup/zhy-be/zhy-persistence/src/main/";
        FastAutoGenerator.create("jdbc:mysql://rm-uf6uu0qyeand624zv0o.mysql.rds.aliyuncs.com:3306/zhy_dev?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true", "zhy_dev", "Senhai@116")
                .globalConfig(builder -> {
                    builder.author("jobury") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(rootPath + "java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.zhy") // 设置父包名
                              .mapper("mapper")
//                            .moduleName("") // 设置父包模块名
//                            .service("repository")
//                            .serviceImpl("repository.impl")
                            .entity("dataobject")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, rootPath + "resources/mapper")); // 设置mapperXml生成路径
                })
//                .templateConfig(builder -> {
//                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICE_IMPL);
//                    //builder.disable(TemplateType.CONTROLLER);
//                })
                .injectionConfig(builder ->
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                    tableInfo.setEntityName(tableInfo.getEntityName()+"DO");
                    objectMap.put("entity", tableInfo.getEntityName());
                    // 这里是预处理输出模板信息
                    System.out.println("tableInfo:" + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                })
                // 这里是自定义传入模板参数值
                //.customMap(Collections.singletonMap("baseResultMap", true))
//                .customMap(new HashMap<>(){{
//                    put("baseResultMap", true);
//                    put("baseColumnList",true);
//                }})
                .build())
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames);
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .enableFileOverride()
                            .superClass(BaseDO.class)
                            .disableSerialVersionUID()
                            .addSuperEntityColumns("is_valid", "gmt_create","create_by","gmt_modify","modify_by")
                            .enableLombok()
                            .enableRemoveIsPrefix();
                    builder.controllerBuilder().disable();
                    builder.serviceBuilder().disable();
//                    builder.serviceBuilder()
//                            .enableFileOverride()
//                            .formatServiceFileName("%sRepository")
//                            .formatServiceImplFileName("%sRepositoryImpl");
                    builder.mapperBuilder()
                            .enableFileOverride()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
