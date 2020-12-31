package com.bill.microservice.dao.db1.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StudentDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Student student = new Student();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> studentId = student.studentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> studentName = student.studentName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Student extends SqlTable {
        public final SqlColumn<Integer> studentId = column("STUDENT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> studentName = column("STUDENT_NAME", JDBCType.VARCHAR);

        public Student() {
            super("student");
        }
    }
}