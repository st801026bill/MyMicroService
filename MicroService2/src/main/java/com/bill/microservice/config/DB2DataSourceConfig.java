package com.bill.microservice.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.bill.microservice.config.datasource.DataSourceConfig;

@Configuration
@MapperScan(basePackages = "com.bill.microservice.dao.db2", sqlSessionTemplateRef = "DB2SessionTemplate")
public class DB2DataSourceConfig implements DataSourceConfig {
	@Bean(name = "db2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	@Override
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db2SessionFactory")
	@Override
	public SqlSessionFactory createSessionFactory(
			@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource);
		return ssf.getObject();
	}

	@Bean("db2TransactionManager")
	@Override
	public DataSourceTransactionManager createTransactionManager(
			@Qualifier("db2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("DB2SessionTemplate")
	@Override
	public SqlSessionTemplate createSessionTemplate(
			@Qualifier("db2SessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
