package com.bill.microservice.config.datasource;

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

@Configuration
@MapperScan(basePackages = "com.bill.microservice.dao.db1", sqlSessionTemplateRef = "DB1SessionTemplate")
public class DB1DataSourceConfig implements DataSourceConfig{

	@Bean(name = "DB1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	@Primary
	@Override
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("DB1SessionFactory")
	@Primary
	@Override
	public SqlSessionFactory createSessionFactory(
			@Qualifier("DB1DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource);
		return ssf.getObject();
	}

	@Bean("DB1TransactionManager")
	@Primary
	@Override
	public DataSourceTransactionManager createTransactionManager(
			@Qualifier("DB1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("DB1SessionTemplate")
	@Primary
	@Override
	public SqlSessionTemplate createSessionTemplate(
			@Qualifier("DB1SessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
