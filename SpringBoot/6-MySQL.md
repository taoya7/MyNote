

# 引入MySQL

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
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j00ava</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

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

### 获取表中所有的内容

创建接口继承JpaRepository

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

