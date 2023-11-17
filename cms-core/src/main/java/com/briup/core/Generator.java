package com.briup.core;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author shaoyb
 * @program: ims
 * @description 代码生成器主类
 * @create 2022/12/7 11:20
 **/
public class Generator {
    public static void main(String[] args) {
        //1. 创建代码生成器对象，执行生成代码操作
        AutoGenerator autoGenerator = new AutoGenerator();

        //2. 数据源相关配置：读取数据库中的信息，根据数据库表结构生成代码
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/cms?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("cms");
        dataSource.setPassword("root");
        autoGenerator.setDataSource(dataSource);

        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setOutputDir(System.getProperty("user.dir")+"/mybatis-plus-generator/src/main/java");    //设置代码生成位置
        // 当前项目位置  C:\Users\vanse\Desktop\jd2312\cms\src\main\java
//        String property = System.getProperty("user.dir");
//        System.out.println("property = " + property);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/cms-core/src/main/java");    //设置代码生成位置
        globalConfig.setOpen(false);            //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setAuthor("briup");        //设置作者
        globalConfig.setFileOverride(true);     //设置是否覆盖原始生成的文件
        globalConfig.setMapperName("%sDao");    //设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setIdType(IdType.ASSIGN_ID);   //设置Id生成策略 雪花算法
        autoGenerator.setGlobalConfig(globalConfig);

        //设置包名相关配置
        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setParent("com.briup.core");     //设置生成的包名，与代码所在位置不冲突，二者叠加组成完整路径
        packageInfo.setEntity("bean");        //设置实体类包名
        packageInfo.setMapper("dao");           //设置数据层包名
        packageInfo.setXml("mapper");           //设置映射文件报名，默认mapper.xml
        autoGenerator.setPackageInfo(packageInfo);

        //策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        //strategyConfig.setInclude("tbl_user");  //设置当前参与生成的表名，参数为可变参数
        // cms_user   -> User
        strategyConfig.setTablePrefix("cms_");  //设置数据库表的前缀名称，模块名 = 数据库表名 - 前缀名  例如： User = tbl_user - tbl_
        strategyConfig.setRestControllerStyle(true);    //设置是否启用Rest风格
        strategyConfig.setVersionFieldName("version");  //设置乐观锁字段名
        strategyConfig.setLogicDeleteFieldName("deleted");  //设置逻辑删除字段名
        strategyConfig.setEntityLombokModel(true);  //设置是否启用lombok
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  //设置c_id成cId
        autoGenerator.setStrategy(strategyConfig);

        //3. 执行生成操作
        autoGenerator.execute();
    }
}
