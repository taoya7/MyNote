## 引入MySQL

> **步骤**

1. 配置文件`application.properties`加入datasouce配置
2. `pom.xml`加入MySQL依赖
3. 测试

配置项

```properties
###############################

#        数据库

###############################

spring.datasource.url = jdbc:mysql://localhost:3306/hibernate_test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username = root
spring.datasource.password =
spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.max-active = 20
spring.datasource.max-idle = 8
spring.datasource.min-idle = 8
spring.datasource.initial-size = 10
spring.datasource.max-wait=10000



spring.jpa.database = MYSQL
spring.jpa.show-sql = true1
spring.jpa.hibernate.ddl-auto = update
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

依赖项

- mysql
- jdbc
- jpa

```xml
 <!--数据库-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

测试连接

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads()  throws  Exception{
        //默认使用的数据源
        System.out.println(dataSource.getClass()); //class com.zaxxer.hikari.HikariDataSource
        Connection conn = dataSource.getConnection(); 
        System.out.println(conn);//HikariProxyConnection@770164810 wrapping com.mysql.cj.jdbc.ConnectionImpl@4182a651
    }

}
```

可以看到默认的数据源是`com.zaxxer.hikari.HikariDataSource`





## 实例

模型Students同时数据库创建students表

```java
package com.itaolaity.main.mysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity//需要使用@Entity注解 Students就会进行持久化了
public class Students {
    @Id //自动增长ID
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer age;

    public Students(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

## 整合JdbcTemplate

JdbcTemplate是Spring提供的JDBC模板框架

### 获取所有数据信息

```java
@RestController
@RequestMapping("/student")
public class StudentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public List<Map<String, Object>> stuList(){
        List<Map<String, Object>> stus = jdbcTemplate.queryForList("select * from students");
        return stus;
    }
}
```

![](E:\Tashi\Desktop\Learning\Docker\image\20190621094426.png)

### 添加用户信息

接受url传过来的参数然后进行加入操作

```java
@GetMapping("/addstu")
public int addStudent(@RequestParam("name") String name, @RequestParam("age") Integer age){
    System.out.println(name+"\t"+age);
    int update = jdbcTemplate.update("insert into students(name,age) values (?,?)", name, age);
    return update;
}
```

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190621100225.png)

### 删除用户信息

```java
@GetMapping("/delstu")
public int delStudent(@RequestParam("delNum") Integer id){
    int update = jdbcTemplate.update("delete from students where id=?", id);
    return update;
}
```

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190621100627.png)

### 总结

**使用步骤**

1. 创建Dao 注入JdbcTemplate

2. 写入依赖

3. 数据库连接配置

**主要API**

在JdbcTemplate中 增删改查主要是

`update()` `batchUpdate()` `query` `queryForObject` `execute`

## 整合SpringData JPA

JPA( Java Persistence API)

Hibernate是一个ORM(对象关系映射)框架，JPA是ORM的规范

**加入依赖**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

**数据库配置**

```properties
spring.jpa.show-sql=true #是否控制台打印SQL
spring.jpa.database=mysql #对应的数据库是MySQL
spring.jpa.hibernate.ddl-auto=update #启动时根据实体类更新数据库中的表
spring.jpa.properties.hibernate.dialect=

```

- spring.jpa.hibernate.ddl-auto

  - update
  - create
  - create-drop
  - validate
  - no

**实体类**

- `@entry `表示该类是一个实体类，项目启动的时候会自动生成表。表的名称是注解中的name值，如果没有默认为类的名称
- `@id`表示该属性是一个主键
- `@Column`可以自定义字段属性 name的值表示属性对应数据库字段的名称
- `@Transient`表示生成数据库中的表时。属性被忽略


### 获取表中所有的内容

**创建接口继承JpaRepository**

```java
package com.itaolaity.main.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  StudentRepository extends JpaRepository<Students,Integer>{}
```



创建控制类 返回Json数据

```java
@RestController
public class Main {
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/stulist")
    public List<Students> getStuList(){
        List<Students> stuList = studentRepository.findAll();

       return stuList;
    }
}
```

![](E:\Tashi\Desktop\Learning\SpringBoot\image\20190619094911.png)

**解析**

1. JPA 全称Java Persistence API  通过JDK注解或XML描述对象 关系表的映射关系 并将运行实体对象持久化到数据库中

### 保存数据

1. 创建实体类
2. 创建jpa repository类操作持久化
3. 创建restful请求类
4. 测试

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface  StudentRepository extends JpaRepository<Students,Integer>{}
```

创建Controller使用Post请求接受name与age参数，保存并且返回添加的数据

```java
@PostMapping("/addStudent")
public Students addStudent(@RequestParam("name") String name, @RequestParam("age") Integer age){
    System.out.println(name +"\t" + age);

    Students stu = new Students();
    stu.setName(name);
    stu.setAge(age);
    return studentRepository.save(stu);
}
```



![](E:\Tashi\Desktop\Learning\SpringBoot\image\20190619102435.png)

![](E:\Tashi\Desktop\Learning\SpringBoot\image\20190619102518.png)



### 自定义

**接口实现(Dao)**

```java
package com.itaolaity.main.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository  extends JpaRepository<Students,Integer> {
    // 返回编号最大的学生
    @Query(value = "select * from students where id=(select max(id) from students)", nativeQuery = true)
    Students getTopById();
}
```

自定义Dao继承JpaRepository。JpaRepository提供了一些基本的数据库操作方法

- 增删改查
- 分页查询
- 排序查询

Dao定义的方法符合规范 SpringData就会分析开发者意图

| 关键字 | 方法名           | SQL                      |
| ------ | ---------------- | ------------------------ |
| And    | findByNameAndAge | where name=? and age = ? |
| Or     | findByNameOrAge  | where name=? or age = ?  |
| Is     | findByName       | where name=?             |

**Java编码**(Impl)

```java
@Autowired
private StudentRepository studentRepository;

@GetMapping("/maxid")
public Students getTopById(){
    Students stu = studentRepository.getTopById();
    return stu;
}

```

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190621105021.png)

