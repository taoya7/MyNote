### Web开发



### 静态资源的映射

SpringBoot的静态资源文件main/resources里

**WebJars的方式引入文件**

[webjars](<https://www.webjars.org/>)

在官网里引入文件比如JQuery

```xml
<dependency>
    <groupId>org.webjars.bower</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
</dependency>
```

添加到pom.xml配置文件中加入依赖

####  默认配置

提供了四个文件夹提供静态文件存放(小->大)

1. `static`
2. `public`
3. `resources`
4. `/META-INF/resources`
5. `/`项目根路径

```bash
src/main/resources/static/main.css

>访问 http://127.0.0.1:8080/main.css 就会访问到
```

#### 自定义策略

自定义默认静态资源过滤策略

1. 配置文件定义

   ```xml
   spring.mvc.static-path-pattern=/static/**
   spring.resources.static-locations=classpath:/static/
   ```

#### 欢迎页面

静态资源文件夹下的所有index.html页面

```bash
# 1.在static创建index.html页面
# 2.url:127.0.0.1访问就会跳转此页面
```

#### ICON图标

所有静态资源文件夹下寻找`favicon.ico`



## SpringBoot热部署

**spring-boot-devtools**

添加配置pom.xml

```properties
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

配置文件application.yml

```yml
debug: true
spring:
  devtools:
    restart:
      enabled: true  #设置开启热部署
  freemarker:
    cache: false    #页面不加载缓存，修改即时生效
```

**JRebel插件**



File -> Settings -> Plugins 搜索JRebel安装



## 文件上传

一共涉及两个组件



#### 单文件上传



**Demo**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--
	上传接口: /upload 
	请求方法: post
	enctype: multipart/form-data
-->
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile" value="请上传文件">
    <input type="submit" value="上传">
</form>
</body>
</html>
```













