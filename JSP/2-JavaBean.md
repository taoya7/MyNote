### JavaBean

**是什么**

JavaBean是一个遵循特定写法的Java类

- 这个Java类必须具有一个无参的构造函数
- 属性必须私有化
- 私有化的属性必须通过public类型的方法暴露给其它程序，并且方法的命名也必须遵守一定的命名规范

```java

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

**JSP中访问JavaBean**

JSP技术提供了三个关于JavaBean组件的动作元素

- `<jsp:useBean>`标签：用于在JSP页面中查找或实例化一个JavaBean组件。
- `<jsp:setProperty>`标签：用于在JSP页面中设置一个JavaBean组件的属性。
- `<jsp:getProperty>`标签：用于在JSP页面中获取一个JavaBean组件的属性。

> ### `<jsp:useBean>`

用于在指定的域范围内查找指定名称的JavaBean对象.如果存在则直接返回该JavaBean对象的引用，如果不存在则实例化一个新的JavaBean对象并将它以指定的名称存储到指定的域范围中

- id 用于指定JavaBean实例对象的引用名称
- class 用于指定JavaBean的完整类名
- scope 属性用于指定JavaBean实例对象所存储的域范围
	- 四种范围：page、request、session、application

```jsp
<body>
	<jsp:useBean id="per" class="tashi.Person" scope="page"/>

	<%
	    per.setName("张三");
	    per.setAge(18);
	%>
	<%= per %>
	<h1><%= per.getName() %></h1>
	<h1><%= per.getAge() %></h1>
</body>
```

**执行原理**

`<jsp:useBean id="per" class="tashi.Person" scope="page"/>`

首先在指定的域范围内查找指定名称的JavaBean对象，如果存在则直接返回该JavaBean对象的引用，如果不存在则实例化一个新的JavaBean对象并将它以指定的名称存储到指定的域范围中


> ### `<jsp:setProperty>`

用于设置和访问JavaBean对象的属性


- 语法格式一：`<jsp:setProperty name="beanName" property="propertyName" value="string字符串"/>`
- 语法格式二：`<jsp:setProperty name="beanName" property="propertyName" value="<%= expression %>" />`
- 语法格式三：`<jsp:setProperty name="beanName" property="propertyName" param="parameterName"/>`
- 语法格式四：`<jsp:setProperty name="beanName" property= "*" />`

name属性用于指定JavaBean对象的名称。 

property属性用于指定JavaBean实例对象的属性名

value属性用于指定JavaBean对象的某个属性的值，value的值可以是字符串，也可以是表达式。为字符串时，该值会自动转化为JavaBean属性相应的类型，如果value的值是一个表达式，那么该表达式的计算结果必须与所要设置的JavaBean属性的类型一致


```jsp
<jsp:useBean id="per" class="tashi.Person" scope="page"/>


<jsp:setProperty property="name" name="per" value="Luck"/>
<jsp:setProperty property="age" name="per" value="20"/>


<%= per.getName()%>
<%= per.getAge()%>

>>> Luck 20
```

> ### `<jsp:getProperty>`

用于读取JavaBean对象的属性，也就是调用JavaBean对象的getter方法，然后将读取的属性值转换成字符串后插入进输出的响应正文中

```jsp
<jsp:useBean id="per" class="tashi.Person" scope="page"/>

<!-- SET -->
<jsp:setProperty property="name" name="per" value="Luck"/>
<jsp:setProperty property="age" name="per" value="20"/>

<!-- GET -->
<jsp:getProperty  property="name" name="per"></jsp:getProperty>
<jsp:getProperty  property="age" name="per"></jsp:getProperty>
```

**MyWebSite**

[http://www.itaolaity.com](http://www.itaolaity.com)
