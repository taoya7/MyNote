> 安装Django

```python
pip install django
```

```
#检查是否安装成功
import django
django.get_version()
```

### 创建项目

- 命令行
```python
django-admin startproject 目录名称
```

- Pycham
```
File-->New project-->Django
```

### 运行项目

```python
python manage.py runserver
```

### 局域网其他设备访问

```python
python manage.py runserver 0.0.0.0:8080
```


### 项目结构

1. `manage.py` 以后和项目交互基本上都是基于这个文件。在终端输入`python manage.py [子命令]`

2. `settings.py`本项目的设置项，项目配置放在这里

3. `urls.py`配置URL路由的，

4. `wsgi.py`项目于与WSGI协议兼容的WEB服务器入口，部署的时候需要用到

> Project与App的关系

app是django项目的组成部分。一个app代表项目中的一个模块。


## 视图

视图一般写在app的views.py中。这个对象存储了请求过来的所有信息，包括携带的参数以及一些头部信息

注意：
1. 视图函数第一个参数必须是`request`参数必不可少
2. 返回值必须是`django.http.response.HttpResponseBase`的子类对象

- 创建视图

```python

# 创建App
python manage.py startapp book
# 创建视图
from django.shortcuts import render
from django.http import HttpResponse

def book(request):
	return HttpResponse("图书视图")
	
# 添加url映射
from django.contrib import admin
from django.urls import path
from book import views

urlpatterns = [
	path('admin/', admin.site.urls),
	path('book/',views.book)
]
```

> ### URL添加参数

1. 采用URL使用变量的方式。在path的第一个参数中，使用<参数名>的方式传递参数，然后在视图函数中也要写一个参数， 视图函数中的参数必须和URL中的参数名称保持一致，不然就找不到这个参数。同时支持传递多个参数

2. 采用查询字符串的方式。在URL中不需要单独的匹配字符串的部分。只需要在视图函数中使用request.GET.get("参数名称")的方式来获取。在访问的网站的时候前加上?+参数名称=值


**方式1**

book/views.py

```
from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def index(request):
    return HttpResponse("首页")

def book(request):
    return HttpResponse("The Book")

def book_info(request, book_id, book_kind):
    #从数据库中提取信息
    text = "获取ID为<%s>  图书种类是<%s>"%(book_id, book_kind)
    return HttpResponse(text)
```

book/urls.py

```
from django.contrib import admin
from django.urls import path
from book import views


urlpatterns = [
    path('', views.index), #首页
    path('admin/', admin.site.urls),
    path('book/',views.book),
    path('book/index/<book_id>/<book_kind>', views.book_info)

]
```

![](image/1555398353531.png)

**方式二**

```
#views.py
def author_info(request):
    author_name = request.GET.get('name')
    text = "作者姓名是 %s"%author_name
    return HttpResponse(text)
		
		
#urls.py
urlpatterns = [
    path('book/author/',views.author_info)
]
```

![](image/1555398395175.png)

> 为什么需要URL命名

因为URL是经常变化的。如果在代码中写死可能会经常修改代码。给URL起个名字，以后使用URL时候就使用它的名字进行反转就可以了。

```
urlpatterns = [
    path('index',views.index, name='index')
]
```

> App命名

多个APP之间 可能产生同名的URL。为了避免变量应该命名 AppName.参数


