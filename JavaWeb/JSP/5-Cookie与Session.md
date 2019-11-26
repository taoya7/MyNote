# Cookie

Cookie是一种在客户端保存信息的技术。Servlet使用`java.servlet.Cookie` 封装一个Cookie信息。在ServletResponse接口中定义了addCookie方法 在ServletRequest接口定义了getCookie方法。

**常用方法**

- getName 获得Cookie名称
- setValue | getValue 设置与获取Cookie的值
- setMaxAge | getMaxAge 设置与获取Cookie在客户端的有效时间
- setPath | getPath 设置与获取Cookie有效的路径
- setDomain | getDomain 设置与获取Cookie有效域
- setComment | getComment 设置与获取Cookie的注释部分
- setVersion | getVersion 设置与获取Cookie的协议版本
- setSecure | getSecure 设置与获取Cookie是否只能在安全的协议传输Cookie

#### 存储Cookie

1. 创建Cookie
2. 发送Cookie
3. 访问浏览器
4. 设置存储时间
5. 设置携带路径
6. 删除Cookie

```
//1.创建Cookie
Cookie cookie = new Cookie("name", "tashi");
cookie.setMaxAge(3600);
//2. 写给客户端
resp.addCookie(cookie);
System.out.println("Finish~~");
```

#### 存储时间

默认为会话级别，打开浏览器关闭浏览器为一次会话

`cookie.setMaxAge(int seconds)` 单位为秒

#### 携带路径

`cookie.setPath()`

#### 删除

`cookie.setMaxAge(0)`

#### 获取

```
 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建Cookie
        Cookie cookie = new Cookie("name", "tashi");
        cookie.setMaxAge(3600);
        //2. 写给客户端
        resp.addCookie(cookie); //添加Cookie
        System.out.println("Finish");

        //1. 获取Cookie
        Cookie[] cookies = req.getCookies();
        if(cookies!=null){
            //取出每一个Cookie
            for(Cookie cook :cookies){
                String name = cook.getName();
                String value = cook.getValue();
                int maxAge = cook.getMaxAge();
                System.out.println("\t\t>>>"+name+"\t"+value+"\t"+maxAge);
            }
        }
    }
```

---

# Session

session对象用来保存每个用户的用户信息和会话状态。Session由服务器自动创建，可以跟踪每个用户的操作状态。 相对于Cookie ，session是**存储在服务器端**的会话，相对安全

> 如何在一个Servlet当中存数据在别的Servlet当中取出

每一个用户访问服务器时，会给该用户分配它自己对应的存储空间，并且创建的存储空间有一个编号为SessionID，第一次访问时，会把对应的sessionID以Cookie的形式写给浏览器。下次访问的时候，会携带sessionID，找到当初创建的那个存储空间，在对应的存储空间当中取出数据。

```
 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//拿到Session对象
        session.setAttribute("Name", "Tashi");
        session.setAttribute("Age", "20");

        System.out.println(session.getAttribute("Name"));
    }
```

**生命周期**

1. 创建。第一次执行`request.getSession()`时创建
2. 销毁。服务器关闭时session过期，默认30分钟

**常用接口方法**

- getId() 返回当前HttpSession对象的SessionId
- getCreationTime() 对象创建的时间
- getLastAccessedTime() 上一次被访问的时间
- getMaxInactiveInterval | setMaxInactiveInterval()
- isNew() 是否新创建的会话
- isvalidate() 强制HttpSession对象失效
- getServletContext() 
- setAttribute()
- getAttribute()
- remoteAttribute()

> 通过Cookie跟踪session

```java
@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); //获得一个session对象
        System.out.println(session.getId());
        session.setMaxInactiveInterval(60*60);

        if(session.isNew()){
            session.setAttribute("name", "tashi");
            resp.getWriter().write("新的会话已建立");
        }
    }
```
