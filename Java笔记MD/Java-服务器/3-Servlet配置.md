<style type="text/css">
	h1 {

		font-weight: 400;
	}
	img {
		border-radius: 10px;
		box-shadow: 0 2px 8px rgba(0,0,0,.3);
	}
	html {
		background-color: #FDF6E3; 
	}
	import::before{
		display: inline-block;
		content: "";
		width: 10px;
		height: 10px;
		background-color: red;
		margin-right: 10px;
		border-radius: 50%;
	}
	import {
		font-size:14px;
		font-weight: bold;
		padding:0.55rem;
		border-radius:5px; 
		color:#eb5055;
	}
	.markdown-body blockquote{
		border-left: 4px solid tomato;
	}
</style>

### #1.第一个程序

```java
/*编写字节码文件*/

package Unit1;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class a_ServerletDemo implements Servlet{

	private static final String HttpServletResponse = null;

	@Override
	public void destroy() {
		/*
		 * 销毁时调用
		 * */
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*
		 * 创建Servlet调用
		 * */
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Browser: I send you a message!"); //打印终端
		/*
		 * 每一次发送请求的时候就会调用
		 * */
		
		HttpServletResponse resp = (HttpServletResponse)res;
		resp.getWriter().write("<h1>Server:I send a message</h1>"); //返回信息到浏览器
	}
	
}

```

```xml
<!-- #添加映射 -->
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
  <display-name>FirstTry</display-name>
  
  
  <!-- 自定义信息 -->
  <servlet>
  	<servlet-name>Tashi</servlet-name>
  	<servlet-class>Unit1.a_ServerletDemo</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>Tashi</servlet-name>
  	<url-pattern>/d</url-pattern>
  </servlet-mapping>
  
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```


### #2.获取字节码文件

```java
Class c = Class.forName("获取字节码.Person");
System.out.println(c);

Class c2 = Person.class;
System.out.println(c2);


Person p = new Person();
Class c3 = p.getClass();
System.out.println(c3);
```

### #3.通过字节码创建对象

```java
public class Main{
	Class c = Class.forName("......");
	Person p = (Person)c.newInstance();
	p.show(); //调用P的show方法


	{
		//2. 通过有参的构造器来创建对象
		Constructor ah = c.getConstructor(String.class, Integer.class);
        Person p = (Person)ah.newInstance("鲁班",18);
        p.show();
	}
}
```

### #4.获取字段

```java
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Main {
    public static void main (String[] args)throws  Exception{
        Class c = Class.forName("获取字节码.Person");
        {
            /*获取公共字段*/
            //1.获取字节码  必须设置public 才可以
            Person per = (Person)c.newInstance();
            Field f = c.getField("name");
            f.set(per,"张三");
            per.show();
        }
        {
            /*获取私有字段*/
            Person per = (Person)c.newInstance();

            //暴力反射获取字段
            Field f = c.getDeclaredField("age");

            //去除私有权限
            f.setAccessible(true);

            f.set(per, 666);

            per.show();
        }
    }
}

```

### #5.获取方法

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args)throws  Exception{
        Class c = Class.forName("获取字节码.Person");

        {
            /*获取公共方法*/
            Person per = (Person)c.newInstance();

            Method m = c.getMethod("show");//获取方法
            System.out.println(m.getName());//打印方法名字
            m.invoke(per); //执行方法  参数传入对象
        }

        {
            /*获取私有方法*/
            Person p = (Person)c.newInstance();

            Method m = c.getDeclaredMethod("temp");
            m.setAccessible(true);
            m.invoke(p);

        }

    }
}
```


### #注解的方式创建servlet

`src` -> `new` -> `	Servlet`

```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Demo")
public class Demo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Message");
    }
}


// http://localhost:8080/Demo
```

### #`doGet`与`doPost`

```java
public class Main extends HttpServlet {
    //每次请求的时候就会调用此方法
    // 注意：一但写了service就不会去找doGet与doPost
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求");
    }
    //get请求会调用这个方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get请求");
    }
    //post请求会调用这个方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post请求");
    }
}
```

**内部原理**

`HttpServlet`是专门负责处理http请求与响应的。`HttpServlet`接口 继承`GenericServlet`接口 继承 `Servlet`接口

当收到一个请求的时候，就会找到`service`方法,如果不存在就会在它的父类中找，强制转换`httpServlet`,获取请求的方式，然后调用`get`还是`post`

- **1.获取请求参数**

`req.getParameter("userName");`

```java
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mesg = req.getParameter("mesg"); //获取前端的表单
    System.out.println(mesg);
}
```


# #ServletContext

---

WEB应用的上下文对象

- **生命周期**

服务器创建与服务器关闭


- **如何获取**

> 全局初始化参数

```java
public void init(...){
	ServletContext sc = config.getServletContext();
	System.out.println(sc);
}
```

```java
public void init(...){
	super.init(config);
}

public void service(...){
	ServletContext context = this.getServletContext();
	System.out.println(context);
}
```


> 获取绝对地址

`String str = context.getRealPath('filePath ');`


# #响应`response`

设置响应行|响应头|响应体

- 设置响应行
```java
 @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置状态码
        resp.setStatus(200);
    }
```

- 设置响应头

	- `add`设置
		- `addHeader(String name, String value);`
		- `addIntHeader(String name, int value);`
		- `addDateHeader(String name, date);`
	- `set`修改
		- `setHeader("Name", "LLE");`

```java
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 设置状态码
    resp.setStatus(200);
    // 设置响应头
    resp.addHeader("Name", "Tashi");
    resp.addIntHeader("Age", 20);
    resp.addDateHeader("Birthday",new Date().getTime());
}
```

- 重定向

```java
// 设置响应头
resp.setStatus(302);

// 设置响应头
resp.setHeader("location", "/book");
```

```java
resp.sendRedirect("/book");
```

- 定时刷新重定向

```java
resp.setHeader("refresh", "3;url=/book");
```

- 设置相应体
	- 通过`writter`方法
	- 通过`OutPutStream`来写

```java
// 如果有html标签会自动解析
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    resp.setHeader("Content-Type", "text/html;charset=UTF-8"); //或者 resp.setContentType("text/html;charset=UTF-8");
    resp.getWriter().write("<h1>Yo!啊哈</h1>");
}
```


```java
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = this.getServletContext().getRealPath("index.jsp");
    //加载文件
    FileInputStream in = new FileInputStream(path);
    ServletOutputStream out = resp.getOutputStream(); //输出流

    //获取字节码
    byte[] buffer = new byte[1024];
    int len = 0;
    while((len=in.read(buffer))!= -1) {
        System.out.println(len);
        System.out.println(Arrays.toString(buffer));
        out.write(buffer,0,len); // 写给浏览器
    }

    //关闭输入流
    in.close();
}
```


# #请求`request`

**设置编码格式**`req.setCharacterEncoding("UTF-8");`

- 获取请求行
```java
System.out.println(req.getMethod()); //请求的方法
System.out.println(req.getRequestURL()); //请求的URL
System.out.println(req.getRequestURI()); //请求的URI
System.out.println(req.getQueryString()); //请求的参数
System.out.println(req.getContextPath());// 请求当前的Web应用名称
```


- 获取请求头的名称
```java
Enumeration<String> headerNames = req.getHeaderNames();
while(headerNames.hasMoreElements()){
    String name = headerNames.nextElement();
    System.out.println(name+"\t"+ req.getHeader(name));
}
```

- 获取请求体
```java
//请求一个
String str1 = req.getParameter("Fruit");
System.out.println(str1);

//请求多个
String[] str2 = req.getParameterValues("Fruit");
System.out.println(Arrays.toString(str2));

//请求所有参数名称
Enumeration<String> parameterNames = req.getParameterNames();
while(parameterNames.hasMoreElements()){
    String name = parameterNames.nextElement();
    System.out.println(name);
}

//获取所有参数值
Map<String, String[]> parameterMap = req.getParameterMap();
for(Map.Entry<String,String[]>entry: parameterMap.entrySet()){
    System.out.println(entry.getKey());
    System.out.println(Arrays.toString(entry.getValue()));
}
```


### #请求转发与重定向

- 1.获取转发器`req.getRequestDispatcher('path')`
- 2.转发`disp.forward(request,response)`


# #Demo

- 文件下载

```java
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //接收参数
    String  name = req.getParameter("Main.mp4");
    String mType = this.getServletContext().getMimeType(name);

    resp.setContentType(mType); //设置类型
    resp.setHeader("Content-Disposition", "attachment;filename="+name); //告诉浏览器以附件形式

    //获取文件路径
    String path = this.getServletContext().getRealPath("Download/"+name);
    
    //获取响应的输出
    ServletOutputStream out = resp.getOutputStream();


    //加载文件
    FileInputStream in = new FileInputStream(path);
    byte[] buffer = new byte[1024];
    int len = 0;
    while((len = in.read())!=-1){
        out.write(buffer,0, len);
    }
    in.close();
}
```