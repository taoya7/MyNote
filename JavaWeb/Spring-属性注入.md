

# Spring属性注入

- 构造方法的方式属性注入
- Set方法的属性注入
- Set方法设置对象类型属性
- P名称空间的属性注入
- spEL表达式属性注入
- 集合类属性注入
	- 数组
	- List集合
	- Set集合
	- Map集合

> 构造方法的方式属性注入

![](image/1557326652676.png)

> Set方法的属性注入

![](image/1557326631873.png)

> Set方法设置对象类型属性

![](image/1557326612956.png)

> P名称空间的属性注入

使用P名称空间，就不需要写很多的property

使用之前需要添加名称空间

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

![](image/1557326586553.png)


> spEL表达式属性注入

![](image/1557326567257.png)

> 集合类属性注入

- 数组|List集合|Set集合

通过list标签里的value标签 定义值

```xml
<bean id="stu" class="Unit1.Student">
		<property name="name" value="#{'Luck'}"/>
		<property name="arr">
				<list>
						<value>站衫</value>
						<value>李四</value>
						<value>王五</value>
				</list>
		</property>
</bean>
```

- Map集合

通过mao标签在内部的entry标签里的key与value定义map里的key-value

```xml
<bean id="stu" class="Unit1.Student">
		<property name="name" value="#{'Luck'}"/>
		<property name="map">
				<map>
						<entry key="a" value="1"></entry>
						<entry key="b" value="2"></entry>
						<entry key="c" value="3"></entry>
				</map>
		</property>
</bean>
```

