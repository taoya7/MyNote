# 初识Django

**Django与MVC**

M：Model

V：View

C：Controller

分别代表模型、视图、控制器

Django是一个`MTV`的设计模式，MTV是Model、Template、View三个单词的简写

Views加载网页的数据，然后去Models查找数据，把数据加载Templates模板，之后网页呈现给我们

![](image/1555397291202.png)


### URL的组成部分

```
https://host:port/path/?query-string=xxx#anchor
```

- 协议
- 主机名|IP地址
- 端口
- 路径
- 查询字符串

**注意**

URL中所有字符都是ASCII字符集