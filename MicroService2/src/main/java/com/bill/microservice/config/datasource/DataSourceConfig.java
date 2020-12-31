package com.bill.microservice.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public interface DataSourceConfig {
	public DataSource createDataSource();
	
	public SqlSessionFactory createSessionFactory(DataSource dataSource) throws Exception;
	
	public DataSourceTransactionManager createTransactionManager(DataSource dataSource);
	
	public SqlSessionTemplate createSessionTemplate(SqlSessionFactory sqlSessionFactory);
}
