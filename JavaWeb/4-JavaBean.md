---
title: JavaBean
date: 2019-04-21 17:32:26
tags: [JavaWeb]
categories: ["JavaWeb"]
---
# JavaBean

**是什么**

JavaBean是遵循特定写法的一个java类

**为什么**

一次编写，任何地方调用

**条件**

1. 必须具有一个无参构造函数
2. 属性必须私有化
3. 还需要通过public的方法暴露给其他类访问

```
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Person {
    private String name;
    private Integer age;
    public Person(){
        super();
    }
    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}
```

## JSP中使用JavaBean

JSP提供了三个相关动作元素

- <jsp:useBean> 实例化一个JavaBean组件
- <jsp:setProperty> 设置JavaBean组件的属性
- <jsp:getProperty> 获取JavaBean组件的属性

**<jsp:useBean>**

```
<jsp:useBean id="per" class="tashi.Person" scope="page" />

<%
    per.setName("张三");
    per.setAge(18);
%>

<%
    out.print(per.getName());
    out.print(per.getAge());
    out.print(per.getClass());
%>
```

- id 表示生成的实例化的对象
- class 对象所在的包.类名
- scope 在JavaBean的保存范围
	- page
	- request
	- session
	- application

> 执行原理

首先实例化一个对象，因为.jsp执行的首先会翻译成一个servlet 。首先在指定的域范围内查找指定名称JavaBean对象，如果存在则返回对象的引用 如果不存在则实例化一个新的JavaBean对象并将它指定的名称存储到指定的域范围内

**<jsp:setProperty>**

设置和访问JavaBean对象的属性

格式一

<jsp:setProperty name="" property="" value="">

格式二

<jsp:setProperty name="" property="" value="<%= new Date() %>" />

格式三

<jsp:setProperty name="beanName" property="propertyName" param="parameterName"/>

格式四

<jsp:setProperty name="" property= "*" />

- name指定对象的名称
- property指定实例对象的属性名
- value 赋予对象属性的值。注意它可以是字符串也可以是表达式，如果是字符串会自动转换属性相应的类型


```
//使用jsp:setProperty标签设置person对象的属性值
<jsp:useBean id="per" class="tashi.Person" scope="page" />

<jsp:setProperty  name="per" property="name" value="张三" />
<jsp:setProperty name="per" property="age" value="20" />

<%
    out.print(per.getName());
    out.print(per.getAge());
    out.print(per.getClass());
%>
```

```
//使用请求参数为bean的属性赋值
<jsp:useBean id="per" class="tashi.Person" scope="page" />

<jsp:setProperty  name="per" property="name" param="name" />
<jsp:setProperty name="per" property="age" param="age" />

<%
    out.print(per.getName());
    out.print(per.getAge());
    out.print(per.getClass());
%>
```

![](javabean/1555840515766.png)

**<jsp:getProperty>**

用于读取JavaBean对象的属性，也就是调用JavaBean对象的getter方法

```
<jsp:useBean id="per" class="tashi.Person" scope="page" />

<jsp:setProperty  name="per" property="name" value="小红" />
<jsp:setProperty name="per" property="age" value="6" />

<jsp:getProperty name="per" property="name"/>
```

## 应用场景

网站访问人数计数器

数据库应用

```

// 计数器模型
public class Counter {
    private long num;
    public long getNum(){
        return this.num;
    }
    public void setNum(long num){
         this.num = num + 1;
    }
}



//index.jsp
<body>

<jsp:useBean id="counter" class="tashi.Counter" scope="application" />

<%
    if(session.isNew()){ //判断当前用户是否为新会话
        long temp = counter.getNum(); //先取出上次的赋值方法，用计数器进行+1
        counter.setNum(temp);
    }
%>

<h1>本网站已有 <jsp:getProperty name="counter" property="num" />  人访问</h1>

</body>
```

