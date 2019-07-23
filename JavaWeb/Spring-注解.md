# 注解

> 前提

1. 引入aop的包
2. 配置文件引入context约束

```xml
<beans
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

    <!--指定注解扫描包-->
    <context:component-scan base-package="Unit1"/>

</beans>
```

## 使用

配置文件加入

```xml
<!--指定注解扫描包-->
<context:component-scan base-package="Unit1"/>
```

类

```java
@Component("stu")
public class Student {
    @Value("张三")
    public String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

测试

```java
@org.junit.Test
public void t1(){
		//加载配置文件 - 自动创建对象
		ApplicationContext applicationContext =
						new ClassPathXmlApplicationContext("applicationContext.xml");

		//根据ID获取对象
		Student stu = (Student)applicationContext.getBean("stu");


		System.out.println(stu);


}
```

注意：要给属性初值，如果由set方法在set方法上添加@value() 如果没有set在属性上添加@value()

## 注解详细解释

`@Component` 修改一个类将这个类给Spring管理，相当于配置文件中`<bean id="" class="">`

@Component 衍生注解

- @Component 
	- @Controller web层
	- @Service service层
	- @Repository dao层

三个注解功能都一样

**属性注入**

- @value() 设置普通属性值
- @Autowired 设置对象属性直接使用是按照类型完全属性注入
	- @Qualifier()  /*根据名称注入*/
	- @Resource(name="") /*等同于@Autowired + @Qualifier*/
- @PostConstruct 初始化方法
- @PreDestroy 销毁方法
- @scope 作用范围
	- prototype 多例

注入对象类型

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component("stu")
public class Student {
    @Value("张三")
    String name;
    @Value("18")
    Integer age;
    @Autowired
    @Qualifier("dog")
    Dog dog;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dog=" + dog +
                '}';
    }
}
```


##### XML与注解比较

XML可以适用任何场景，结构清晰。


注解的形式，贴标签的形式开发简单。





