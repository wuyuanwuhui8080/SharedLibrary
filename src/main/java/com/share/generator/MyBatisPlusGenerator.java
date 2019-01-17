package com.share.generator;

import java.sql.SQLException;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

	public static void main(String[] args) throws SQLException {

		// 1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(true) // 是否支持AR模式
				.setAuthor("博博大人") // 作者
				// .setOutputDir("D:\\workspace_mp\\mp03\\src\\main\\java") //
				// 生成路径
				.setOutputDir("E:\\ideaxm\\SharedLibrary\\src\\main\\java") // 生成路径
				.setFileOverride(true) // 文件覆盖
				.setIdType(IdType.UUID) // 主键策略
				.setServiceName("%sService") // 设置生成的service接口的名字的首字母是否为I
				// IEmployeeService
				.setBaseResultMap(true)// 生成基本的resultMap
				.setBaseColumnList(true);// 生成基本的SQL片段

		// 2. 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL) // 设置数据库类型
				.setDriverName("com.mysql.jdbc.Driver")
				.setUrl("jdbc:mysql://localhost:3306/sharedlibrary")
				.setUsername("root").setPassword("root");

		String[] incLude = { "shared_forum_comment",
				"shared_forum_comment_give", "shared_forum_comment_reply",
				"shared_forum_comment_reply_give", "shared_forum" };
		// 3. 策略配置globalConfiguration中
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) // 全局大写命名
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				// .setTablePrefix("tbl_")
				.setInclude(incLude); // 生成的表

		// 4. 包名策略配置
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.share.forum").setMapper("mapper")// dao
				.setService("service")// servcie
				.setController("controller")// controller
				.setEntity("pojo").setXml("mapper");// mapper.xml

		// 5. 整合配置
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig)
				.setPackageInfo(pkConfig);

		// 6. 执行
		ag.execute();
	}

}
