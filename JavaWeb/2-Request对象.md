# Request

## 请求行

1. 获取请求方法`getMethod()`
2. 请求URL`getRequestURL()`
3. 请求URI`getRequestURI()`
4. 请求Get请求参数`getQueryString()`
5. 获取Web应用名称`getContextPath()`

### 请求头

1. 获取所有请求头名称`getHeaderNames()`

```java
<%
    Enumeration headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()){
   	 System.out.println(headerNames.hasMoreElements());
	}
%>
```

2. 根据名称获取指定头信息`getHeader()`

### 请求体

1. 获取一个值`getParameter()`
2. 获取多个值`getParameterValues()`
3. 获取所有请求参数名称`getParameterNames()`
4. 获取所有请求参数`getParameterMap()`



### 请求转发

**重定向**

发送两次请求

**请求转发**

发送一次请求

1. 获取转发器`request.getRequestDispatcher(String path)`

2. 转发器转发`.forward(request, response)`

### Request域对象

一次请求的过程中