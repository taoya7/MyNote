**FTP**

文件传输协议



## Vsftpd

一款运行在Linux操作系统上的FTP服务程序

### 安装

```shell
yum install vsftpd
```

iptables防火墙牧人禁止FTP传输协议的端口号。

```shell
iptables -F # 清空iptables防火墙的默认策略
```

### 配置文件

主配置文件`/etc/vsftpd/vsftpd.conf`

查看配置文件

```shell
vim /etc/vsftpd/vsftpd.conf

anonymous_enable=YES
local_enable=YES
write_enable=YES
local_umask=022
dirmessage_enable=YES 
xferlog_enable=YES
connect_from_port_20=YES
xferlog_std_format=YES
listen=NO
listen_ipv6=YES

pam_service_name=vsftpd
userlist_enable=YES
tcp_wrappers=YES
```

**解析**


| 参数                                              | 作用                                                         |
| ------------------------------------------------- | ------------------------------------------------------------ |
| `listen=[YES\|NO]`                                | 是否以独立运行的方式监听服务                                 |
| listen_address=IP地址                             | 设置要监听的IP地址                                           |
| listen_port=21                                    | 设置FTP服务的监听端口                                        |
| download_enable＝[YES\|NO]                        | 是否允许下载文件                                             |
| userlist_enable=[YES\|NO] userlist_deny=[YES\|NO] | 设置用户列表为“允许”还是“禁止”操作                           |
| max_clients=0                                     | 最大客户端连接数，0为不限制                                  |
| max_per_ip=0                                      | 同一IP地址的最大连接数，0为不限制                            |
| `anonymous_enable=[YES\|NO]`                      | 是否允许匿名用户访问                                         |
| anon_upload_enable=[YES\|NO]                      | 是否允许匿名用户上传文件                                     |
| anon_umask=022                                    | 匿名用户上传文件的umask值                                    |
| anon_root=/var/ftp                                | 匿名用户的FTP根目录                                          |
| anon_mkdir_write_enable=[YES\|NO]                 | 是否允许匿名用户创建目录                                     |
| anon_other_write_enable=[YES\|NO]                 | 是否开放匿名用户的其他写入权限（包括重命名、删除等操作权限） |
| anon_max_rate=0                                   | 匿名用户的最大传输速率（字节/秒），0为不限制                 |
| `local_enable=[YES\|NO]`                          | 是否允许本地用户登录FTP                                      |
| local_umask=022                                   | 本地用户上传文件的umask值                                    |
| `local_root=/var/ftp`                             | 本地用户的FTP根目录                                          |
| chroot_local_user=[YES\|NO]                       | 是否将用户权限禁锢在FTP目录，以确保安全                      |
| local_max_rate=0                                  | 本地用户最大传输速率（字节/秒），0为不限制                   |

### 启动

```shell
systemctl restart vsftpd # 重启
systemctl enable vsftpd # 加入启动项
```

## Vsftpd服务程序

1. 匿名开放模式
2. 本地用户模式
3. 虚拟用户模式

### 匿名访问模式

无需密码验证直接登录到FTP服务器

| 参数                        | 作用                               |
| --------------------------- | ---------------------------------- |
| anonymous_enable=YES        | 允许匿名访问模式                   |
| anon_root=/var/ftp/pub      | 设置匿名用户的登录目录             |
| anon_umask=022              | 匿名用户上传文件的umask值          |
| anon_upload_enable=YES      | 允许匿名用户上传文件               |
| anon_mkdir_write_enable=YES | 允许匿名用户创建目录               |
| anon_other_write_enable=YES | 允许匿名用户修改目录名称或删除目录 |

```shell
anonymous_enable=YES 
anon_umask=022
anon_upload_enable=YES
anon_mkdir_write_enable=YES
anon_other_write_enable=YES

...
```

**测试连接**

匿名用户其账号统一anonymous密码为空

```shell
[root@iZuf6aezijrwku50v6qusrZ ~]# ftp 172.19.68.108
	Connected to 172.19.68.108 (172.19.68.108).
	220 (vsFTPd 3.0.2)
	Name (172.19.68.108:root): anonymous
	230 Login successful.
	Remote system type is UNIX.
	Using binary mode to transfer files.
ftp> 
```





### 本地用户模式

| 参数                  | 作用                                                         |
| --------------------- | ------------------------------------------------------------ |
| anonymous_enable=NO   | 禁止匿名访问模式                                             |
| local_enable=YES      | 允许本地用户模式                                             |
| write_enable=YES      | 设置可写权限                                                 |
| local_umask=022       | 本地用户模式创建文件的umask值                                |
| userlist_deny=YES     | 启用“禁止用户名单”，名单文件为ftpusers和user_list            |
| userlist_enable=YES   | 开启用户作用名单文件功能                                     |
| file_open_mode=0755   | 本地用户上传档案后的档案权限                                 |
| chroot_local_user=YES | 用于指定用户列表文件中的用户不允许切换到上级目录。默认值为NO。 |

```shell
anonymous_enable=NO

local_enable=YES
write_enable=YES
local_umask=022
dirmessage_enable=YES
xferlog_enable=YES
connect_from_port_20=YES
xferlog_std_format=YES
listen=NO
listen_ipv6=YES
pam_service_name=vsftpd
userlist_enable=YES
tcp_wrappers=YES
```

**添加一个FTP用户**

```shell
useradd ftpuser -s /sbin/nologin
```

**为用户添加密码**

```shell
passwd ftpuser
```

**查看黑名单**

```shell
[root@iZuf6aezijrwku50v6qusrZ ~]# cat /etc/vsftpd/user_list 
# vsftpd userlist
# If userlist_deny=NO, only allow users in this file
# If userlist_deny=YES (default), never allow users in this file, and
# do not even prompt for a password.
# Note that the default vsftpd pam config also checks /etc/vsftpd/ftpusers
# for users that are denied.
root
bin
daemon
adm
lp
sync
shutdown
halt
mail
news
uucp
operator
games
nobody
```

```shell
[root@iZuf6aezijrwku50v6qusrZ ~]# cat /etc/vsftpd/ftpusers 
# Users that are not allowed to login via ftp
root
bin
daemon
adm
lp
sync
shutdown
halt
mail
news
uucp
operator
games
nobody
```

所以我们在使用root用户登录的时候会显示失败

**启动服务**

```shell
systemctl restart vsftpd
```

















