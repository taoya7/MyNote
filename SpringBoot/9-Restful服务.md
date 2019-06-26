# Rest

**定义**

REST通常基于HTTP、URL、XML等流行的协议。在Rest中资源是通过URI指定的，对资源的增删改查可以通过Http协议提供的get、set、put、delete方法。



### 基本实现

**添加依赖**

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

**基本数据库配置**

```properties

spring.datasource.url=jdbc:mysql://47.100.247.150:3306/test
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

spring.jpa.show-sql=true
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=update


spring.datasource.tomcat.max-active=20
spring.datasource.tomcat.max-idle=8
spring.datasource.tomcat.min-idle=8
spring.datasource.tomcat.initial-size=10 
spring.datasource.tomcat.max-wait=10000
spring.datasource.tomcat.time-between-eviction-runs-millis=60000
```





