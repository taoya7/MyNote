### Web开发



### 静态资源的映射

SpringBoot的静态资源文件`main/resources`里

**WebJars的方式引入文件**

如果在前端页面需要引入js文件css文件，以jars方式引入文件

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

1. `public`

2. `static`

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
   

使用外部目录

如果需要用指定的一个绝对路径的文件夹需要使用`addResourceLocations()`指定

```java
registry.addResourceHandler("/api_files/**").addResourceLocations("file:D:/data/
api_files");
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



## SpringBoot Junit单元测试

**步骤**

1. 加入Maven依赖
2. 测试

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

### 基本注解

| 注解           | 注释                                                         |
| -------------- | ------------------------------------------------------------ |
| `@BeforeClass` | 在所有测试方法前执行一次，一般在其中写上整体初始化的代码     |
| `@AfterClass`  | 在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码 |
| `@Before`      | 在每个测试方法前执行，一般用来初始化方法（比如我们在测试别的方法时，类中与其他测试方法共享的值已经被改<b变，为了保证测试结果的有效性，我们会在@Before注解的方法中重置数据） |
| @Test          |                                                              |

## 文件上传

一共涉及两个组件

#### 单文件上传

**Demo**

`src/main/resources`

```html
<!DOCTYPE html>
<html lang="zh">
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
    <input type="file" name="file" value="请上传文件">
    <input type="submit" value="上传">
</form>
</body>
</html>
```

编写Controller

```java
package com.itaolaity.demo.File;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileUploadController {
    /*上传页面*/
    @RequestMapping("/file")
    public String file(){
        return "file";
    }

    /*上传实现*/
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file")MultipartFile file){

        System.out.println(file.getName());
        System.out.println(file.getContentType()); //上传类型
        System.out.println(file.getOriginalFilename()); //文件名字
        System.out.println(file.getSize()); //文件大小

        if(!file.isEmpty()){
            try{
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "上传失败"+e.getMessage();
            }

        }else{
            return "上传失败,文件为空";
        }

        return  "Ok";
    }
}

```

`MainApplication.class`

```java

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}

```

当访问`<http://127.0.0.1:8080/file>`进行上传，完毕后文件会在项目根路径下

#### 多文件上传

前端页面

```html
<!DOCTYPE html>
<html lang="zh">
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
<form action="/upload" method="post" enctype="multipart/form-data" >
    <input type="file" name="file" value="请上传文件" multiple>
    <input type="submit" value="上传">
</form>
</body>
</html>
```

**注意**

多文件上传 需要在input标签加上`multiple`

Controller

```java
package com.itaolaity.demo.File;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class FileUploadController {
    /*上传页面*/
    @RequestMapping("/file")
    public String file(){
        return "file";
    }

    /*上传实现*/
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        //获取文件列表
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        MultipartFile file = null;
        BufferedOutputStream stream = null;

        for(int i=0; i<files.size(); ++i){
            file = files.get(i);

            if(!file.isEmpty()){
                try{
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                return "上传失败,空文件类型";
            }
        }

        return "Ok";
    }
}
```



