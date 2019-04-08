


### #创建Cookie

**存储Cookie**

- 创建Cookie
- 向客户端发送Cookie
- 访问浏览器
- Cookie默认存储时间
- 设置Cookie的携带路径
- 删除Cookie

```java
//1.创建Cookie
Cookie cookie = new Cookie("name", "tashi");
cookie.setMaxAge(3600);
//2. 写给客户端
resp.addCookie(cookie);
System.out.println("Finish~~");
```

> Cookie的存储时间

默认会话级别，打开浏览器，关闭浏览器为一次会话

如何设置Cookie在客户端的存储时间？

`Cookie.setMaxAge(int seconds)`单位为秒

> Cookie携带路径

`Cookie.setPath()`

> Cookie的删除

`Cookie.setMaxAge(0)`

> Cookie的获取

`.getCookies`


```java
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



### #Session 

session技术是将**数据存储在服务器端**的技术。会为每个客户端都创建一块内存控件存储客户的数据。


> 如何在一个servlet当中存数据在别的servlet当中取出当初存储的数据

每一个用户访问服务器时，会给该用户分配它自己对应的存储空间，并且创建的存储空间有一个编号为SessionID，第一次访问时，会把对应的sessionID以Cookie的形式写给浏览器

下次访问的时候，会携带sessionID，找到当初创建的那个存储空间，在对应的存储空间当中取出数据。

```java
 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//拿到Session对象
        session.setAttribute("Name", "Tashi");
        session.setAttribute("Age", "20");

        System.out.println(session.getAttribute("Name"));
    }
```

> 生命周期

- 创建

	第一次执行request.getSession()时创建

- 销毁
	
	服务器关闭时，session过期(默认30Min)

	或者手动销毁


> 常用

- setAttribute(key,value);
- getAttribute(key);
- session.invalidate(); #销毁