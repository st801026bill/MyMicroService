# 微服物基本架構範例
**一、環境**
1. Spring Boot : `2.3.7 (SNAPSHOT)`
2. Spring Cloud : `Hoxton.SR9`

**二、專案目錄**
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
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
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
      routes:
      - id: service1_route
        uri: lb://MICROSERVICE1
        predicates:
        - Path=/service1/**
```

**3. Spring Boot 專案**

dependency
```sql
1. Eureka Discovery Client
2. Spring Web
```

application.properties
```sql
server.port=8080
spring.application.name=microservice1
```

**三、執行順序**
1. 啟動NamingServer(Eureka)，已啟用服務註冊與發現機制。
2. 啟動Gateway 與 SpringBoot專案。
3. Postman執行 `curl post http://localhost:8765/service1/method1`，透過gateway在yml中的設定，將request導至對應的服務(MicroService1)做後續處理。
