# Django

### 安装Django

- 安装
```python
pip install django
```
- 检查是否安装成功
```python
import django
django.get_version()
```


### 创建项目

- 创建一个Django项目
```
django-admin startproject 目录名称
```

- 或者Pycham
```
Pycharm中新建Django工程，File-->New project-->Django
```

- 测试运行
```
python manage.py runserver
```

- 打开浏览器
```
http://127.0.0.1:8000
```
### 改变端口号

终端运行加上端口号

```
python manage.py runserver 8080
```
### 局域网其他设备访问本机项目

在项目后 加上host 0.0.0.0
```
python manage.py runserver 0.0.0.0:8080
```

在Pycham中配置Host：0.0.0.0

最后在`setting.py`在ALLOWED_HOSTS = []中添加本地IP

### 项目结构介绍

1. `manage.py` 以后和项目交互基本上都是基于这个文件。在终端输入`python manage.py [子命令]`

2. `settings.py`本项目的设置项，项目配置放在这里

3. `urls.py`配置URL路由的，

4. `wsgi.py`项目于与WSGI协议兼容的WEB服务器入口，部署的时候需要用到




