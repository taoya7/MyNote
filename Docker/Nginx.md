### Nginx组成

- Nginx二进制可执行文件
- Nginx.conf配置文件
- access.log访问日志
- error.log错误日志

### 安装

**步骤**

1. 下载

   [http://nginx.org/download/nginx-1.16.0.tar.gz](http://nginx.org/download/nginx-1.16.0.tar.gz)

2. Configure

3. 编译

4. 安装

```shell
## 安装依赖包
yum install pcre-devel
yum install zlib zlib-devel
yum install openssl openssl-devel

## 开始
cd /usr/local/src

wget http://nginx.org/download/nginx-1.16.0.tar.gz #下载

tar -xvf nginx-1.16.0.tar.gz #解压

cd nginx-1.16.0
```

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190717231517.png)

```shell
./configure
make
make install
```

 

## Windows

### 基本使用

1. 下载

2. 解压

   ![](E:\Tashi\Desktop\Learning\Docker\image\1563879587137-2233ddcf-a537-4893-80cb-90117c0c3414.png)

3. 测试

   ```shell
   start nginx 
   
   http://127.0.0.1:80
   ```

**一些命令**

帮助 `-? -h`

使用指定的配置文件 `-c`

指定配置指令 `-g`

指定运行目录 `-p`

发送信号 `-s`

- 立刻停止服务`stop`
- 优雅停止服务`quit`
- 重载配置文件`reload`
- 重新开始记录日志文件`reopen`

```shell
nginx -v #查看nginx的版本

start nginx #启动Nginx

nginx -s reload #重新加载配置文件

nginx -s stop #快速停止
nginx -s quit #完全停止

nginx -t #验证配置是否正确
```

## 配置文件

查看默认的配置文件

```shell
#user  nobody;

# Nginx进程数
worker_processes  1; 

# 全局错误日志定义类型 [debug|info|notice|warn|error|crit]
#error_log  logs/error.log; 
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

# 进程文件
#pid        logs/nginx.pid;


events {
	# 单个进程最大连接数量
    worker_connections  1024;
}

# Http服务器
http {
    include       mime.types; # 文件扩展名与文件类型映射表
    default_type  application/octet-stream; # 默认文件类型
	#charset utf-8; # 默认编码
    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on; # 开启高效文件传输模式 sendfile指令指定nginx是否调用sendfile函数来输出文件，对于普通应用设为 on，如果用来进行下载等应用磁盘IO重负载应用，可设置为off，以平衡磁盘与网络I/O处理速度，降低系统的负载。
    
    #tcp_nopush     on; #防止网络阻塞

    keepalive_timeout  65; # 长连接超时时间 单位为s

    gzip  on;

    server {
        listen       80; #监听端口
        server_name  localhost; # 域名列表 中间用空格分隔

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
        }

        error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}
```

**配置文件由指令与指令块构成**

1. 每条指令以分号结尾
2. { }将多条指令组织在一起
3. `include`允许组合多个配置文件以提升可维护性
4. `$`使用变量
5. **四大块**
   - `http`
   - `server`
   - `location`
   - `upstream`

```shell
location / {
  root   html; # 访问域名根目录
  index  index.html index.htm; #设置访问主页
}
```

root定义了工作空间

index决定那个优先显示

### gzip模块设置

```shell
gzip on; 			#开启gzip压缩输出
gzip_min_length 1k; #最小压缩文件大小
gzip_buffers 4 16k; #压缩缓冲区
    gzip_http_version 1.0; #压缩版本（默认1.1，前端如果是squid2.5请使用1.0）
gzip_comp_level 2;  #压缩等级
gzip_types text/plain application/x-javascript text/css application/xml; #压缩类型，默认就已经包含text/html，所以下面就不用再写了，写上去也不会有问题，但是会有一个warn。
gzip_vary on;
```

### 反向代理

```shell
proxy_pass http://127.0.0.1:8080;
proxy_redirect off;

proxy_set_header X-Real-IP $remote_addr;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for; #后端的Web服务器可以通过X-Forwarded-For获取用户真实IP

proxy_set_header Host $host;

client_max_body_size 10m; #允许客户端请求的最大单文件字节数
client_body_buffer_size 128k; #缓冲区代理缓冲用户端请求的最大字节数，
proxy_connect_timeout 90; #nginx跟后端服务器连接超时时间(代理连接超时)
proxy_send_timeout 90; #后端服务器数据回传时间(代理发送超时)
proxy_read_timeout 90; #连接成功后，后端服务器响应时间(代理接收超时)
proxy_buffer_size 4k; #设置代理服务器（nginx）保存用户头信息的缓冲区大小
proxy_buffers 4 32k; #proxy_buffers缓冲区，网页平均在32k以下的设置
proxy_busy_buffers_size 64k; #高负荷下缓冲大小（proxy_buffers*2）
proxy_temp_file_write_size 64k; 
```



## 日志访问

**日志参数说明**

| 参数                    | 说明             |
| ----------------------- | ---------------- |
| `$remote_addr`          | 客户端IP(公网IP) |
| `$http_x_forwarded_for` | 代理服务器的IP   |
| `$time_local`           | 服务器本地时间   |
| `$host`                 | 访问主机名       |
| `$request_uri`          | 访问的url地址    |
| `$status `              | 状态码           |
| `$http_referer`         |                  |
| `$http_user_agent`      |                  |

## 案例

### 搭建一个静态资源Web服务器

配置

```shell
server{
    listen 9804;
    server_name localhost; #指定域名
    index index.html index.php; #优先级

    access_log logs/book.access.log main;


    location / {
        alias Book;
        autoindex on; 
        index index.html;

        set $limit_rate 1k; #设置请求大小
    }
}
```

### 搭建具有缓存功能的的反向代理服务器

开启上游服务

一个SpringBoot应用

```shell
java -jar .\app-0.0.1-SNAPSHOT.jar
```

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190724002854.png)

`127.0.0.1:8080`即可看到

配置`nginx.conf`

```shell
server{
	listen 9805;
	server_name localhost;

	location / {
		proxy_set_header Host $host;
		proxy_set_header X-Real-IP $remote_addr;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

		proxy_pass http://127.0.0.1:8080;
		proxy_redirect off;
	}
}
```

这样当我们访问`127.0.0.1:9805`即看到`127.0.0.1:8080`页面

![](E:\Tashi\Desktop\Learning\Docker\image\QQ截图20190724004721.png)





### 查阅

<https://www.cnblogs.com/cy-8593/p/10021296.html>

<https://www.cnblogs.com/xuey/p/7631690.html>