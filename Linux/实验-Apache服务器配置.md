---
title: Apache服务器配置
date: 2019-05-22 12:37:18
tags: [Linux]
published: true
hideInList: false
feature: 
categories: ["Linux"]
---
# 配置Java环境

1. 解压安装JDK

   ```shell
   tar -zxvf 压缩包
   ```

2. 配置环境变量

   ```xml
   export JAVA_HOME=/home/admin/java/jdk1.8
   export PATH=$JAVA_HOME/bin:$PATH 
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar 
   ```

3. 更新

   ```bash
   source /etc/profile
   ```

   

# Apache

1、配置文件：

```xml
/etc/httpd/conf/httpd.conf 
/etc/httpd/conf.d/*.conf
```
2、服务脚本：
```xml
/etc/rc.d/init.d/httpd 
配置文件：/etc/sysconfig/httpd
```
3、主程序文件：

```xml
/usr/sbin/httpd 
/usr/sbin/httpd.event 
/usr/sbin/httpd.worker
```
4、日志文件目录：：
```xml
/var/log/httpd 
-> access_log: 访问日志 
-> error_log：错误日志
```
5、站点文档目录：

```xml
/var/www/html
```
6、模块文件路径：
```xml
/usr/lib64/httpd/modules
```
7、配置文件的组成：

```xml
# grep “Section” /etc/httpd/conf/httpd.conf 
### Section 1: Global Environment 
### Section 2: ‘Main’ server configuration 
### Section 3: Virtual Hosts
```
8、配置格式：

```xml
directive value 
directive：不区分字符大小写 
value：为路径时，取决于文件系统
```


### 服务器搭建

1. 编辑一下配置文件`/etc/httpd/conf/httpd.conf `

```conf
#
#ServerName www.example.com:80

ServerName localhost:80

#
# UseCanonicalName: Determines how Apache constructs self-referencing 
```

2. 启动httpd服务

```bash
service httpd restart
```

3. 修改监听端口

```properties
Listen 80
Listen 127.0.0.1:8080
```

4. 添加HTML文件

```bash
touch /var/www/html/index.html
```

5. 打开浏览器访问`127.0.0.1`

### 配置文件

> 修改家目录

打开`UserDir disabled`,`UserDir public_html` 将前面的#去掉

默认的网站数据保存在/var/www/html目录中如果修改目录需要在配置文件中将`DocumentRoot "/var/www/html"` 修改成自己的目录即可

**注意**

我将家目录修改成了/home里的目录造成打不开。这是因为SELinux服务

```
setenforce 0
getenforce

>>> Permissive
```

>　口令验证


添加账户与密码

htpasswd

-c: 自动创建passwordfile，因此，仅应该在添加第一个用户时使用； 
-m: md5加密用户密码； 
-s: sha1加密用户密码； 
-D: 删除指定用户

```
htpasswd -c /etc/httpd/passwd tao
```

打开配置文件，进行修改

```
<Directory "/var/www/html">
    AllowOverride all
    # 生成出来的密码验证文件保存路径
	authuserfile "/etc/httpd/passwd"
	# 当用户尝试访问个人用户网站时的提示信息
	authname "My privately website"
	authtype basic
	# 用户进行账户密码登录时需要验证的用户名称
	require user tao
</Directory>
```


### 虚拟主机

有三种方法

1. 基于ip
2. 基于port
3. 基于hostname

> 基于IP

1. 首先把配置文件的DocumentRoot注释
2. 配置文件添加主机参数

```
<VirtualHost 192.168.126.128>
	ServerName "www.001.com"
	DocumentRoot "/var/www/html/001"
</VirtualHost>
```


> 基于HostName

1. 去掉配置文件中NameVirtualHost的注释

在html目录下创建三个文件 里面有各自的index目录.然后在配置文件添加三个
各自的虚拟主机参数

```
<VirtualHost 192.168.126.128:80>
	ServerName sample001.com
	DocumentRoot "/var/www/html/001"
	
</VirtualHost>

<VirtualHost 192.168.126.128:80>
	ServerName sample002.com
	DocumentRoot "/var/www/html/002"
	
</VirtualHost>

<VirtualHost 192.168.126.128:80>
	ServerName sample003.com
	DocumentRoot "/var/www/html/003"
</VirtualHost>

```

2. 添加解析内容到 /etc/hosts文件中

> 端口号

基于端口号的虚拟主机功能可以让用户通过指定的端口号来访问服务器上的网站资源。一般来说，使用80、443、8080等端口号来提供网站访问服务。

配置文件

```
1、 添加监听端口

Listen 9001
Listen 9002
Listen 9003

2. 追加写入两个基于端口号的虚拟主机网站参数

<VirtualHost 192.168.126.128:9001>
	DocumentRoot "/var/www/html/001"
	ServerName sample.com
</VirtualHost>

<VirtualHost 192.168.126.128:9002>
	DocumentRoot "/var/www/html/002"
	ServerName sample.com
</VirtualHost>

<VirtualHost 192.168.126.128:9003>
	DocumentRoot "/var/www/html/003"
	ServerName sample.com
</VirtualHost>
```

3. 访问


##### 补充

1. 如果访问主机的时候在后边加上server-status可以查看本机服务器的状态页面

需要在配置文件中，允许本地网段访问

```xml
<Location /server-status>
    SetHandler server-status
    Order deny,allow
    Deny from all
    Allow from 192.168.126.128
</Location>
```



[https://www.oschina.net/](https://www.oschina.net/)

