package com.bill.microservice.dao.db1;

import static com.bill.microservice.dao.db1.mapper.StudentDynamicSqlSupport.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.bill.microservice.dao.db1.mapper.StudentMapper;
import com.bill.microservice.dao.db1.model.Student;

@Repository
public class StudentDb1Dao {
	@Autowired
	@Qualifier("DB1StudentMapper")
	private StudentMapper mapper;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public List<Student> findAll() {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			
			SelectStatementProvider provider = SqlBuilder
					.select(student.allColumns())
					.from(SqlTable.of(student.tableNameAtRuntime()))
					.build().render(RenderingStrategy.MYBATIS3);
			
			return mapper.selectMany(provider);
		}
	}
}
