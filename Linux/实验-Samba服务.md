---
title: Samba服务实验
date: 2019-05-15 20:17:10
tags: [Linux]
published: true
hideInList: false
categories: ["Linux"]

---
# Samba服务器

Samba服务程序现在已经成为在Linux系统与Windows系统之间共享文件的最佳选择

**安装**

```shell
# 查看是否安装
[admin@localhost 桌面]$ rpm -qa|grep samba

samba4-pidl-4.0.0-55.el6.rc4.i686
samba-common-3.6.9-151.el6.i686
samba-winbind-clients-3.6.9-151.el6.i686
samba-winbind-3.6.9-151.el6.i686

# 安装
yum install samba
```

Ubuntu

```shell
sudo apt-get install samba
sudo apt-get install smbfs
```

## 配置文件参数

```bash
cat /etc/samba/smb.conf
```

配置文件包括**全局配置参数**和**区域配置参数**。全局配置参数用于设置整体的资源共享环境，对里面的每一个独立的共享资源都有效。区域配置参数则用于设置单独的共享资源，且仅对该资源有效。


查看一下这个配置参数

```conf
[global]
	workgroup = SAMBA #工作组名称
	security = user 	#安全验证的方式，总共有4种

	passdb backend = tdbsam 	#定义用户后台的类型，共有3种

	printing = cups
	printcap name = cups
	load printers = yes	#设置在Samba服务启动时是否共享打印机设备
	cups options = raw	#打印机的选项

[homes]
	comment = Home Directories
	valid users = %S, %D%w%S
	browseable = No
	read only = No
	inherit acls = Yes

[printers]
	comment = All Printers
	path = /var/tmp
	printable = Yes
	create mask = 0600
	browseable = No

[print$]
	comment = Printer Drivers
	path = /var/lib/samba/drivers
	write list = @printadmin root
	force group = @printadmin
	create mask = 0664
	directory mask = 0775
```




> 共享文件设置参数

|参数|作用|
| ------- | ------- |
|[database]|	共享名称为database|
|comment = Do not arbitrarily modify the database file|	警告用户不要随意修改数据库|
|path = /home/database|	共享目录为/home/database|
|public = no	|关闭“所有人可见|
|writable = yes	|允许写入操作|
|create mask = 0700        | #创建的文件权限为700|
|directory mode = 0700       | #创建的文件目录为 700|

> 添加Samba账户


**注意**

创建samba用户必须创建系统用户，系统用户和samba用户名一样，但密码可以设置成不一样

```
useradd test01

passwd test01

smbpasswd -a test01
```


smbpasswd命令的常用方法

- smbpasswd -a 增加用户（要增加的用户必须以是系统用户）
- smbpasswd -d 冻结用户，就是这个用户不能在登录了
- smbpasswd -e 恢复用户，解冻用户，让冻结的用户可以在使用
- smbpasswd -n 把用户的密码设置成空.要在global中写入 null passwords -true
- smbpasswd -x  删除用户

## samba软件结构

- `/etc/samba/smb.conf`（#samba服务的主要配置文件）
- `/etc/samba/lmhosts`（#samba服务的域名设定，主要设置IP地址对应的域名，类似linux系统的/etc/hosts）
- `/etc/samba/smbusers`（#samba服务设置samba虚拟用户的配置文件）
- `/var/log/samba` （#samab服务存放日志文件）
- `/var/lib/samba/private/{passdb.tdb,secrets.tdb}`（#存放samba的用户账号和密码数据库文档


## 测试


**安装**

`yum install samba`

**创建Samba配置**

```shell
sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.bak

vim /etc/samba/smb.conf
```

添加

```
[share] #共享参数
	path = /database
	public = yes
	writable = yes #定义是否可以执行写入操作
	create mask = 0777
	directory mask = 0777
	browseable = no #指定共享信息是否在“网上邻居”中可见
```

**创建SMB账户**

```shell
pdbedit -a -u taoshare

chown -Rf taoshare:taoshare /database
mkdir /database # 创建分享目录
chmod -Rf 777 /database # 修改文件目录权限
```

**重启服务**

```shell
systemctl restart samba
```

## 出现的问题


> 课堂上虚拟机与主机ping不同。

> init.d启动找不到samba


## 参考


[https://www.linuxprobe.com/chapter-12.html](https://www.linuxprobe.com/chapter-12.html)

[https://www.cnblogs.com/colinsupport/articles/9646994.html](https://www.cnblogs.com/colinsupport/articles/9646994.html)

[https://www.cnblogs.com/zoulongbin/p/7216246.html](https://www.cnblogs.com/zoulongbin/p/7216246.html)