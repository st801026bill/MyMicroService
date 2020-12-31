package com.bill.microservice.dao.db2.model;

import javax.annotation.Generated;

import lombok.Builder;

@Builder
public class Student {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer studentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String studentName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getStudentId() {
        return studentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getStudentName() {
        return studentName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }
}