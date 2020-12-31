# 微服物基本架構範例
**一、環境**
---
1. Spring Boot : `2.3.7 (SNAPSHOT)`
2. Spring Cloud : `Hoxton.SR9`
3. MyBatis : `1.4.0`

**二、專案目錄**
---
1. Spring Eureka (Naming Service)
2. Spring Gateway
3. Spring Boot 專案



**1. Spring Eureka (Naming Service)**  
dependency
```sql
1. Eureka Server
```

application.yml
```sql
spring:
  application:
    name: naming-server

server:
  port: 8761

eureka:
#  instance:
#    prefer-ip-address: true
#    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      default-zone: http://${eureka.instance.hostname}:${server.port}/eureka/ ##Eureka Server的位址

```

**2. Spring Gateway**  
dependency
```sql
1. Eureka Discovery Client
2. Gateway
3. Spring Boot Actuator 
```

application.yml
```sql
server:
  port: 8765

spring:
  application:
    name: gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: false
          lower-case-service-id: true
#      routes:
#      - id: service1_route
#        uri: lb://MICROSERVICE1
#        predicates:
#        - Path=/service1/**
        
eureka:
  instance:
    prefer-ip-address: true
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/


```

**3. Spring Boot 專案**
1. MicroService1
2. MicroService2
----------------

dependency
```sql
1. Spring Web
2. Eureka Discovery Client
3. Open Feign
```

application.properties
```sql
server.port=8080
spring.application.name=microservice1

#eureka.instance.prefer-ip-address=true
#eureka.client.service-url.default-zone=http://localhost:8761/eureka/
```

**三、執行順序**
---
1. 啟動NamingServer(Eureka)，執行`http://localhost:8761/`，已啟用服務註冊與發現機制。
2. 啟動Gateway 與 SpringBoot專案。
3. Postman執行 `curl post http://localhost:8765/service1/method1`，透過gateway在yml中的設定，將request導至對應的服務(MicroService1)做後續處理。

**四、DB 連線**
---
mybatis-generator參考document文件  
這裡模仿有多個datasource的情況，在property與config需要另外做設定  

DB1、DB2
```sql
create table STUDENT (
  STUDENT_ID       integer primary key auto_increment,
  STUDENT_NAME     varchar(45),
);
insert into STUDENT (STUDENT_ID, STUDENT_NAME) values (100, 'Bill');
insert into STUDENT (STUDENT_ID, STUDENT_NAME) values (101, 'Jack');

create table STUDENT (
  STUDENT_ID       integer primary key auto_increment,
  STUDENT_NAME     varchar(45),
);
insert into STUDENT (STUDENT_ID, STUDENT_NAME) values (200, 'Mary');
insert into STUDENT (STUDENT_ID, STUDENT_NAME) values (201, 'Linda');
```

dependency
```sql
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.4</version>
</dependency>
<dependency>
    <groupId>org.mybatis.dynamic-sql</groupId>
    <artifactId>mybatis-dynamic-sql</artifactId>
    <version>1.2.1</version>
</dependency>
```

application.properties
```sql
spring.datasource.db1.jdbcUrl=jdbc:mysql://localhost:3306/micro_db1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false
spring.datasource.db1.username=root
spring.datasource.db1.password=
spring.datasource.db1.driverClassName=com.mysql.cj.jdbc.Driver

spring.datasource.db2.jdbcUrl=jdbc:mysql://localhost:3306/micro_db2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false
spring.datasource.db2.username=root
spring.datasource.db2.password=
spring.datasource.db2.driverClassName=com.mysql.cj.jdbc.Driver
```

config 參考DB1DataSourceConfig、DB2DataSourceConfig
