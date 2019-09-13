### 概念

>  负载均衡服务器：

就是分摊到多个操作单元上进行执行

>  正向代理服务器：

![](http://itaolaity.com/20190725220853.png)

**比如**

使用代理进行上网。比如谷歌在谷内可能使访问不了的 但是使用代理VPN就可以访问。

访问流程就是

浏览器`->`正向代理服务器`->`因特网

> 反向代理服务器：

![](http://itaolaity.com/20190725220006.png)

反向代理充当对外提供服务者，属于服务提供。

**比如**

我们打电话10086 其中10086号码就作为的反向代理。

### Nginx的组成

- Nginx二进制可执行文件
- Nginx.conf配置文件
- access.log访问日志
- error.log错误日志

# 安装

## Linux

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
yum -y install gcc make gcc-c++ wget
yum install openssl openssl-devel

## 开始
cd /usr/local/src

wget http://nginx.org/download/nginx-1.16.0.tar.gz #下载

tar -xvf nginx-1.16.0.tar.gz #解压

cd nginx-1.16.0
```

![](http://itaolaity.com/20190725204813.png)

```shell
./configure --prefix=/home/nginx
make
make install
```

 在编译的时候**参数说明**

| 参数                                         | 说明                                                         |
| -------------------------------------------- | ------------------------------------------------------------ |
| --prefix=`<path>`                            | Nginx安装路径。如果没有指定，默认为 /usr/local/nginx。       |
| --sbin-path=`<path>`                         | Nginx可执行文件安装路径。只能安装时指定，如果没有指定，默认为`<prefix>`/sbin/nginx。 |
| --conf-path=`<path>`                         | 在没有给定-c选项下默认的nginx.conf的路径。如果没有指定，默认为`<prefix>`/conf/nginx.conf。 |
| --pid-path=`<path>`                          | 在nginx.conf中没有指定pid指令的情况下，默认的nginx.pid的路径。如果没有指定，默认为 `<prefix>`/logs/nginx.pid。 |
| --lock-path=`<path>`                         | nginx.lock文件的路径。                                       |
| --error-log-path=`<path>`                    | 在nginx.conf中没有指定error_log指令的情况下，默认的错误日志的路径。如果没有指定，默认为 `<prefix>`/- logs/error.log。 |
| --http-log-path=`<path>`                     | 在nginx.conf中没有指定access_log指令的情况下，默认的访问日志的路径。如果没有指定，默认为 `<prefix>`/- logs/access.log。 |
| --user=`<user>`                              | 在nginx.conf中没有指定user指令的情况下，默认的nginx使用的用户。如果没有指定，默认为 nobody。 |
| --group=`<group>`                            | 在nginx.conf中没有指定user指令的情况下，默认的nginx使用的组。如果没有指定，默认为 nobody。 |
| --builddir=DIR                               | 指定编译的目录                                               |
| --with-rtsig_module                          | 启用 rtsig 模块                                              |
| --with-select_module --without-select_module | 允许或不允许开启SELECT模式，如果 configure 没有找到更合适的模式，比如：kqueue(sun os),epoll (linux kenel 2.6+), rtsig(- 实时信号)或者/dev/poll(一种类似select的模式，底层实现与SELECT基本相 同，都是采用轮训方法) SELECT模式将是默认安装模式 |
| --with-poll_module --without-poll_module     | Whether or not to enable the poll module. This module is enabled by, default if a more suitable method such as kqueue, epoll, rtsig or /dev/poll is not discovered by configure. |
| --with-http_ssl_module                       | Enable ngx_http_ssl_module. Enables SSL support and the ability to handle HTTPS requests. Requires OpenSSL. On Debian, this is libssl-dev. 开启HTTP SSL模块，使NGINX可以支持HTTPS请求。这个模块需要已经安装了OPENSSL，在DEBIAN上是libssl |
| --with-http_realip_module                    | 启用 ngx_http_realip_module                                  |
| --with-http_addition_module                  | 启用 ngx_http_addition_module                                |
| --with-http_sub_module                       | 启用 ngx_http_sub_module                                     |
| --with-http_dav_module                       | 启用 ngx_http_dav_module                                     |
| --with-http_flv_module                       | 启用 ngx_http_flv_module                                     |
| --with-http_stub_status_module               | 启用 "server status" 页                                      |
| --without-http_charset_module                | 禁用 ngx_http_charset_module                                 |
| --without-http_gzip_module                   | 禁用 ngx_http_gzip_module. 如果启用，需要 zlib 。            |
| --without-http_ssi_module                    | 禁用 ngx_http_ssi_module                                     |
| --without-http_userid_module                 | 禁用 ngx_http_userid_module                                  |
| --without-http_access_module                 | 禁用 ngx_http_access_module                                  |
| --without-http_auth_basic_module             | 禁用 ngx_http_auth_basic_module                              |
| --without-http_autoindex_module              | 禁用 ngx_http_autoindex_module                               |
| --without-http_geo_module                    | 禁用 ngx_http_geo_module                                     |
| --without-http_map_module                    | 禁用 ngx_http_map_module                                     |
| --without-http_referer_module                | 禁用 ngx_http_referer_module                                 |
| --without-http_rewrite_module                | 禁用 ngx_http_rewrite_module. 如果启用需要 PCRE 。           |
| --without-http_proxy_module                  | 禁用 ngx_http_proxy_module                                   |
| --without-http_fastcgi_module                | 禁用 ngx_http_fastcgi_module                                 |
| --without-http_memcached_module              | 禁用 ngx_http_memcached_module                               |
| --without-http_limit_zone_module             | 禁用 ngx_http_limit_zone_module                              |
| --without-http_empty_gif_module              | 禁用 ngx_http_empty_gif_module                               |
| --without-http_browser_module                | 禁用 ngx_http_browser_module                                 |
| --without-http_upstream_ip_hash_module       | 禁用 ngx_http_upstream_ip_hash_module                        |
| --with-http_perl_module                      | 启用 ngx_http_perl_module                                    |
| --with-perl_modules_path=PATH                | 指定 perl 模块的路径                                         |
| --with-perl=PATH                             | 指定 perl 执行文件的路径                                     |
| --http-log-path=PATH                         | Set path to the http access log                              |
| --http-client-body-temp-path=PATH            | Set path to the http client request body temporary files     |
| --http-proxy-temp-path=PATH                  | Set path to the http proxy temporary files                   |
| --http-fastcgi-temp-path=PATH                | Set path to the http fastcgi temporary files                 |
| --without-http                               | 禁用 HTTP server                                             |
| --with-mail                                  | 启用 IMAP4/POP3/SMTP 代理模块                                |
| --with-mail_ssl_module                       | 启用 ngx_mail_ssl_module                                     |
| --with-cc=PATH                               | 指定 C 编译器的路径                                          |
| --with-cpp=PATH                              | 指定 C 预处理器的路径                                        |
| --with-cc-opt=OPTIONS                        | Additional parameters which will be added to the variable CFLAGS. With the use of the system library PCRE in FreeBSD, it is necessary to indicate --with-cc-opt="-I /usr/local/include". If we are using select() and it is necessary to increase the number of file descriptors, then this also can be assigned here: --with-cc-opt="-D FD_SETSIZE=2048". |
| --with-ld-opt=OPTIONS                        | Additional parameters passed to the linker. With the use of the system library PCRE in - FreeBSD, it is necessary to indicate --with-ld-opt="-L /usr/local/lib". |
| --with-cpu-opt=CPU                           | 为特定的 CPU 编译，有效的值包括：pentium, pentiumpro, pentium3, pentium4, athlon, opteron, amd64, sparc32, sparc64, ppc64 |
| --without-pcre                               | 禁止 PCRE 库的使用。同时也会禁止 HTTP rewrite 模块。在 "location" 配置指令中的正则表达式也需要 PCRE 。 |
| --with-pcre=DIR                              | 指定 PCRE 库的源代码的路径。                                 |
| --with-pcre-opt=OPTIONS                      | Set additional options for PCRE building.                    |
| --with-md5=DIR                               | Set path to md5 library sources.                             |
| --with-md5-opt=OPTIONS                       | Set additional options for md5 building.                     |
| --with-md5-asm                               | Use md5 assembler sources.                                   |
| --with-sha1=DIR                              | Set path to sha1 library sources.                            |
| --with-sha1-opt=OPTIONS                      | Set additional options for sha1 building.                    |
| --with-sha1-asm                              | Use sha1 assembler sources.                                  |
| --with-zlib=DIR                              | Set path to zlib library sources.                            |
| --with-zlib-opt=OPTIONS                      | Set additional options for zlib building.                    |
| --with-zlib-asm=CPU                          | Use zlib assembler sources optimized for specified CPU, valid values are: pentium, pentiumpro |
| --with-openssl=DIR                           | Set path to OpenSSL library sources                          |
| --with-openssl-opt=OPTIONS                   | Set additional options for OpenSSL building                  |
| --with-debug                                 | 启用调试日志                                                 |
| --add-module=PATH                            | Add in a third-party module found in directory PATH          |

## Windows

1. 下载

2. 解压

   ![](http://itaolaity.com/20190725204902.png)

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

# 配置文件

查看默认的配置文件

```shell

// 默认配置语法
#user  nobody;
worker_processes  1;  # Nginx进程数
#error_log  logs/error.log;  # 全局错误日志定义类型 [debug|info|notice|warn|error|crit]
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;
#pid        logs/nginx.pid;  # 进程文件

// event事件模块
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
server{
	listen 8080;
	
	location / {
  		root   html; # 访问域名根目录
  		index  index.html index.htm; #设置访问主页
	}
}
```

root定义了工作空间。**子配置不存在时，直接使用父配置块；子配置存在时，直接覆盖父配置块**

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

# 日志访问

```shell
log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                  '$status $body_bytes_sent "$http_referer" '
                  '"$http_user_agent" "$http_x_forwarded_for"';
server{
    listen 9801;
    server_name localhost; #指定域名
    index index.html index.php; #优先级
    root Tashi;

    access_log logs/9801.access.log main; #main就是上面定义日志格式的名字
    location / {

    }

    error_page 404 /404.html;
    error_page 500 502 503 504 /50x.html;
}
```

配置文件`nginx.conf`的log_format是定义log样式的。main是日志格式名称，可以自定义。

```shell
//配置语法
Syntax: log_format name [escape=default|json] string ...;
Default:    log_format combined "...";
Context:    http
```



当访问`127.0.0.1:9801`时候即可在`logs`看到日志文件`9801.access.log`

```shell
127.0.0.1 - - [24/Jul/2019:13:16:00 +0800] "GET / HTTP/1.1" 304 0 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36" "-"
127.0.0.1 - - [24/Jul/2019:13:16:08 +0800] "GET / HTTP/1.1" 304 0 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36" "-"
```

**日志参数说明**

| 参数                    | 说明                                    |
| ----------------------- | --------------------------------------- |
| `$remote_addr`          | 客户端IP(公网IP)                        |
| `$remote_port`          | 客户端的端口                            |
| `$remote_user`          | 已经经过Auth Basic Module验证的用户名。 |
| `$http_x_forwarded_for` | 代理服务器的IP                          |
| `$time_local`           | 服务器本地时间                          |
| `$host`                 | 访问主机名                              |
| `$request`              | Request请求行 Get方法 协议版本          |
| `$request_uri`          | 包含请求参数的原始URI                   |
| `$request_method`       | 客户端请求的动作，通常为GET或POST       |
| `$status `              | 状态码                                  |
| `$http_referer`         | 上一级页面 防盗链 用户行为分析          |
| `$http_user_agent`      | 客户端agent信息                         |
| `$http_cookie`          | 客户端cookie信息                        |
| `$content_type`         | 请求头中的Content-Type字段              |

### Nginx状态监控

```shell
--with-http_stub_status_module 记录Nginx客户端基本访问状态信息
```

```shell
# 基本使用
Syntax: stub_status;
Default: -
Context: server, location
```

**使用**

```shell
server{
	listen 9806;
	server_name localhost;
	root html;

	location /{
		stub_status on;
	}
}
```

![](http://itaolaity.com/20190726184148.png)

通过访问看到Nginx运行状态信息

**解析**

```shell
Active connections: 2  //Nginx当前活跃连接数
server accepts handled requests
 5 5 3 
Reading: 0 Writing: 1 Waiting: 1 

server表示Nginx处理理接收握⼿手总次数
accepts表示Nginx处理理接收总连接数
handled requests，表示总共处理理了的请求

Reading     Nginx读取数据
Writing 	Nginx写的情况
Waiting     Nginx开启keep-alive⻓长连接情况下, 既没有读也没有写, 建⽴立连接情况
```

### Nginx作为下载站点

```shell
Syntax: autoindex on|off;
Default: off;
Context: http, server, location


//常用参数
autoindex_exact_size off; # 是否显示文件确切大小 单位bytes

autoindex_localtime on; # 显示文件的事件

charset utf-8,gbk; # 解决中文乱码问题
```

**使用**

```shell
server{
	listen 9807;
	server_name localhost;

	location /Learning {
		root E:\Tashi\Desktop;

		autoindex on;
		autoindex_localtime on;
		autoindex_exact_size on;
	}
}
```

![](http://itaolaity.com/20190726185827.png)

**出现的问题**

1. 404页面

比如`location /down{...}` 

`root: pass`

那么真实访问的路径就是 `pass+/down`

2. 访问遇到中文路径则报错

因为访问时候URI转Unicode乱码

所以添加`charset utf-8,gbk;`

### Nginx访问限制

- 连接频率限制`limit_conn_module`
- 请求频率限制`limit_req_module`

```shell
http{
	limit_req_zone $binary_remote_addr zone=req_zone:10m rate=1r/s;
	...
	server{
		location / {
			//1r/s只接收⼀一个请求,其余请求拒绝处理理并返回错误码给客户端
            limit_req zone=req_zone;
            
            //请求超过1r/s,剩下的将被延迟处理理,请求数超过burst定义的数量量, 多余的请求返回503
            #limit_req zone=req_zone burst=3 nodelay;
		}
	}
}
```

### Nginx访问控制

- IP的访问控制`http_access_module`
- 登录认证`http_auth_basic_module`

```shell
//允许配置语法
Syntax: allow address | CIDR | unix: | all;
Default:    —
Context:    http, server, location, limit_except

//拒绝配置语法
Syntax: deny address | CIDR | unix: | all;Nginx访问控制
Default:    —
Context:    http, server, location, limit_except

//配置拒绝某⼀一个IP, 其他全部允许
location / {   
	root /usr/share/nginx/html;  
    index index.html;   
    deny 192.168.1.9;   
    allow all;
}

//只允许某⼀一个⽹网段访问,其它全部拒绝
location / {   
	root   html;    
	index  index.php index.html index.htm;    
	allow   192.168.1.0/24;
	deny all;
}
```



## 使用GoAccess

<https://goaccess.io/download>

# 案例

### 搭建一个静态资源Web服务器

1. 文件读取高效`sendfile`

   ```conf
   Syntax: sendfile on | off;
   Default: sendfile off;
   Context: http, server, location, ifin location
   ```

2. 提高网络传输速率`nopush`

   ```shell
   Syntax: tcp_nopush on | off;
   Default: tcp_nopush off;
   Context: http, server, location
   
   作⽤: sendfile开启情况下, 提⾼高⽹网络包的'传输效率'
   ```

3. `tcp_nodeley`

   ```conf
   Syntax: tcp_nodelay on | off;
   Default: tcp_nodelay on;
   Context: http, server, location
   
   作⽤: 在keepalive连接下,提⾼高⽹网络的传输'实时性'
   ```

4. 图片压缩

   ```conf
   server{
   	listen:80;
   	server_name 192.168.56.22;
   	sendfile on;
   	access_log /nginx/log/static_access.log main;
   	
   	location ~.*\.(jpg|gif|png)$ {
   		gzip on;
   		gzip_http_version 1.1;
   		gzip_comp_level 2;
   		gzip_types text/plain application/x-javascript text/css application/xml; #压缩类型，默认就已经包含text/html，所以下面就不用再写了，写上去也不会有问题，但是会有一个warn。
   		root /www/images;
   	}
   }
   ```

5. **缓存**`expires`

   ```conf
   Syntax: expires [modified] time;
   expires epoch | max | off;
   Default: expires off;
   Context: http, server, location, ifinlocation
   
   作⽤用: 添加Cache-Control Expires头
   
   比如
   location /{
   	root /www/static;
   	expires 1h;
   }
   ```

6. 静态资源防盗链

   思路：http_refer区别请求是否正常

   格式

   ```conf
   Syntax: valid_referers none | blocked | server_names | string ...;
   Default: —
   Context: server, location
   ```

   例子

   ```conf
   location ~.\*.(jpg|gif|png)${
   	valid_referers none blocked www.itaolaity.com;
   	if($invalid_referer){
   		return 403;
   	}
   	root /www;
   }
   ```

   

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

`$limit_rate` 可以限制连接速率

### 搭建具有缓存功能的的反向代理服务器

开启上游服务

一个SpringBoot应用

```shell
java -jar .\app-0.0.1-SNAPSHOT.jar
```

![](http://itaolaity.com/20190725204923.png)

`127.0.0.1:8080`即可看到

接下来配置Nginx使请求转发上游服务器，并从服务器得到的结果返回给连接的客户端

- 配置`nginx.conf`

```shell
server{
	listen 9805;
	server_name localhost;

	location / {
		proxy_set_header Host $host;
		proxy_set_header X-Real-IP $remote_addr; 
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for; # HTTP的请求端真实的IP
		proxy_set_header X-Forwarded-Proto $scheme;      # 为了正确地识别实际用户发出的协议是 http 还是 https

		proxy_pass http://127.0.0.1:8080;
		proxy_redirect off;
	}
}
```

这样当我们访问`127.0.0.1:9805`即看到`127.0.0.1:8080`页面

![](http://itaolaity.com/20190725204940.png)

`Nginx`所代表的角色是**负载均衡服务器**或者**反向代理服务器**，所有的请求首先到`Nginx`上，再由`Nginx`根据配置好的转发规则，将客户端发来的请求转发到某一个Tomcat上。

复杂配置

```shell
server {
    #侦听的80端口
    listen       80;
    server_name  localhost;
    location / {
        proxy_pass   http://localhost:8080; #上游服务器地址
        
         #后端的Web服务器可以通过X-Forwarded-For获取用户真实IP
        proxy_redirect             off;
        proxy_set_header           Host $host;
        
        client_max_body_size       100m; #允许客户端请求的最大单文件字节数
        client_body_buffer_size    128k; #缓冲区代理缓冲用户端请求的最大字节数
        
        proxy_connect_timeout      300; #nginx跟后端服务器连接超时时间(代理连接超时)
        proxy_send_timeout         300; #后端服务器数据回传时间(代理发送超时)
        proxy_read_timeout         300; #连接成功后，后端服务器响应时间(代理接收超时)
        proxy_buffer_size          4k; #设置代理服务器（nginx）保存用户头信息的缓冲区大小
        proxy_buffers              4 32k; #proxy_buffers缓冲区，网页平均在32k以下的话，这样设置
        proxy_busy_buffers_size    64k; #高负荷下缓冲大小（proxy_buffers*2）
        proxy_temp_file_write_size 64k; #设定缓存文件夹大小，大于这个值，将从upstream服务器传
    }
}
```

| 指令                   | 说明                                                         |
| ---------------------- | ------------------------------------------------------------ |
| proxy_connect_timeout  | Nginx从接受请求至连接到上游服务器的最长等待时间              |
| proxy_send_timeout     | 后端服务器数据回传时间(代理发送超时)                         |
| proxy_read_timeout     | 连接成功后，后端服务器响应时间(代理接收超时)                 |
| proxy_cookie_domain    | 替代从上游服务器来的Set-Cookie头的domain属性                 |
| proxy_cookie_path      | 替代从上游服务器来的Set-Cookie头的path属性                   |
| proxy_buffer_size      | 设置代理服务器（nginx）保存用户头信息的缓冲区大小            |
| proxy_buffers          | proxy_buffers缓冲区，网页平均在多少k以下                     |
| proxy_set_header       | 重写发送到上游服务器头的内容，也可以通过将某个头部的值设置为空字符串，而不发送某个头部的方法实现 |
| proxy_ignore_headers   | 这个指令禁止处理来自代理服务器的应答。                       |
| proxy_intercept_errors | 使nginx阻止HTTP应答代码为400或者更高的应答。                 |

# 查阅

<https://www.cnblogs.com/cy-8593/p/10021296.html>

<https://www.cnblogs.com/xuey/p/7631690.html>

<https://github.com/jaywcjlove/nginx-tutorial>

极客时间-Nginx核心知识100讲

