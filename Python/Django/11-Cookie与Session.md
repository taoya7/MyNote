---
title: Django-Cookie与Session
date: 2019-04-26 19:42:55
tags: [Django]
categories: ["Python"]
---
`cookie`在网站中，http请求是无状态的。也就是说即使第一次和服务器连接后并且登录成功后，第二次请求服务器依然不能知道当前请求是哪个用户。cookie的出现就是为了解决这个问题，第一次登录后服务器返回一些数据（cookie）给浏览器，然后浏览器保存在本地，当该用户发送第二次请求的时候，就会自动的把上次请求存储的cookie数据自动的携带给服务器，服务器通过浏览器携带的数据就能判断当前用户是哪个了。cookie存储的数据量有限，不同的浏览器有不同的存储大小，但一般不超过4KB。因此使用cookie只能存储一些小量的数据

`session`session和cookie的作用有点类似，都是为了存储用户相关的信息。不同的是，cookie是存储在本地浏览器，session是一个思路、一个概念、一个服务器存储授权信息的解决方案，不同的服务器，不同的框架，不同的语言有不同的实现。虽然实现不一样，但是他们的目的都是服务器为了方便存储数据的。session的出现，是为了解决cookie存储数据不安全的问题的


## Cookie



**设置**

response对象来设置，设置cookie可以通过response.set_cookie来设置


```
from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader,context
def index(request):
    response = render(request,'index.html')
    response.set_cookie("name","tashi")
    return response
```

参数

- key：这个cookie的key。
- value：这个cookie的value。
- max_age：最长的生命周期。单位是秒。
- expires：过期时间。跟max_age是类似的，只不过这个参数需要传递一个具体的日期
- path：对域名下哪个路径有效。默认是对域名下所有路径都有效。
- domain：针对哪个域名有效。默认是针对主域名下都有效，如果只要针对某个子域名才有效，那么可以设置这个属性.
- secure：是否是安全的，如果设置为True，那么只能在https协议下才可用。
- httponly：默认是False。如果为True，那么在客户端不能通过JavaScript进行操作

**删除**

通过delete_cookie即可删除cookie。实际上删除cookie就是将指定的cookie的值设置为空的字符串，然后使用将他的过期时间设置为0，也就是浏览器关闭后就过期

**获取**

获取浏览器发送过来的cookie信息。可以通过request.COOKIES来或者。这个对象是一个字典类型。


## Session

```
def index(request):
    request.session["name"]="tashi"
    request.session["age"] = "20"
```

**常用方法**

- get：用来从session中获取指定值。

- pop：从session中删除一个值。

- keys：从session中获取所有的键。

- items：从session中获取所有的值。

- clear：清除当前这个用户的session数据。

- flush：删除session并且删除在浏览器中存储的session_id，一般在注销的时候用得比较多。

- set_expiry(value)：设置过期时间。

整形：代表秒数，表示多少秒后过期。

0：代表只要浏览器关闭，session就会过期。

None：会使用全局的session配置。在settings.py中可以设置SESSION_COOKIE_AGE来配置全局的过期时间。默认是1209600秒，也就是2周的时间。

- clear_expired：清除过期的session。Django并不会清除过期的session，需要定期手动的清理，或者是在终端，使用命令行python manage.py clearsessions来清除过期的session

**修改session的存储机制**

默认情况下，session数据是存储到数据库中的。当然也可以将session数据存储到其他地方。可以通过设置SESSION_ENGINE来更改session的存储位置

- django.contrib.sessions.backends.db使用数据库
- django.contrib.sessions.backends.file使用文件来存储
- django.contrib.sessions.backends.cache使用缓存来存储session
- django.contrib.sessions.backends.cached_db在存储数据的时候，会将数据先存到缓存中，再存到数据库中。这样就可以保证万一缓存系统出现问题，session数据也不会丢失。在获取数据的时候，会先从缓存中获取，如果缓存中没有，那么就会从数据库中获取
- django.contrib.sessions.backends.signed_cookies将session信息加密后存储到浏览器的cookie中