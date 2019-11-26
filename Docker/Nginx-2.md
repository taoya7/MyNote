> 配置参数

空间的单位

- bytes

- k/K

- m/M

- g/G

时间的单位

- ms 毫秒

- s 秒

- m 分钟

- h 小时

- d 天

- w 周

- M 月

- y 年

### 指令

#### Listen指令

四种操作符

- `=` 精确匹配
- `~` 根据正则表达式进行匹配
- `~*` 根据正则表达式进行匹配不区分大小写
- `^~` 不根据正则表达式进行匹配

其中`=`优先级最高 其次`^~` 默认的是`/`

#### Location指令

```shell
http{ //协议
    server{ //服务
        location / { //请求
            root html;
            index index.html;            
        }        
    }    
}
```

location通过指定模式来与请求URI匹配。

`location [=|~|~*|^~|@] pattern{……}`

- 没有修饰符
  
  必须以指定模式开始
  
  ```shell
  server {
      server_name localhost;
      root html
      location /abc{
             
      }
  }
  
  路径 html/abc/index.html
  
  可访问
  127.0.0.1/abc?name=Tonny&age=8
  ```

- =表示精确匹配
  
  ```shell
  server {
   server_name localhost;
   root html
   location = /abc{
    }
  }
  
  路径 html/abc/index.html
  可访问
  http://127.0.0.1/abc/?name=1&age=1
  ```

- `~`表示正则表达式区分大小写

- `~*`不许分大小写
  
  ```shell
  server{
          listen 9810;
          server_name 127.0.0.1:9810;
  
          root Work;
  
          location ~* /ABC {
  
              index index.html;
          }
      }
  
  路径
  html/abc/index.html
  可访问
  http://127.0.0.1:9810/ABC/
  http://127.0.0.1:9810/abc/
  ```

> 踩坑

1. 出现404

```shell
//先看一段配置

location /images {
    root /home/linis/WWW/intro;
}

访问URL所查找的路径是 /home/linis/WWW/intro/images

而不是 /home/linis/WWW/intro;
```

注意细节

- 查找优先级
  
  - `=`匹配优先
  
  - 没有修饰符
  
  - 正则表达式
  
  - `^~`

> `root` 与`alias`区别 

```shell
location /abc {

    root Work;
    index index.html;
}
路径 
html/Work/abc 拼接方式


location /def {
    alias Work/def;
    index index.html;
}
路径
html/Work/def 不做拼接
```

#### rewrite

主要实现url地址重写，以及重定向。

- 格式

```shell
Syntax: rewrite regex replacement [flag];
Default: --
Context: server,location,if

rewrite ^(.*)$ http://www.itaolaity.com break;
```

rewrite 最后一项flag参数：

| 标记符号      | 说明                              |
| --------- | ------------------------------- |
| last      | 本条规则匹配完成后继续向下匹配新的location URI规则 |
| break     | 本条规则匹配完成后终止，不在匹配任何规则            |
| redirect  | 返回302临时重定向                      |
| permanent | 返回301永久重定向                      |

> `redirect` 对比 `permanent`

302与301的区别。301永久重定向，停止服务也会进行跳转。302则停止服务相应也不会跳转。

> 地址优化

```shell
server {
        listen 9810;


        location / {
            index index.html;
            root Work/111/222;

            # http://127.0.0.1:9810/111-222.html -> http://www.itaolaity.com;
            # rewrite ^/(\d+)-(\d+).html http://www.itaolaity.com; 

            # http://127.0.0.1:9810/home/
            # 访问 http://127.0.0.1:9810/111-222.html -> http://127.0.0.1:9810/home/
            # 真实路径 Work/111/222/home

            rewrite ^/(\d+)-(\d+).html /home break;

        }
}
```

### 重定向

- `premanent` 永久重定向 301

- `redirect` 临时重定向 302

#### 单页

```shell
location /def {
    alias Work/def;
    index index.html;
    return 302 http://127.0.0.1:9810/abc;
}    

实际访问
127.0.0.1:9810/abc
```

---

### 代理

**正向代理**

```shell
server{
	listen 80;
	location / {
		proxy_pass http://$http_host$request_uri;
	}
}
```

#### 负载均衡

作用：

- 提升吞吐量
- 提升请求性能
- 提高容灾

Nginx实现负载均衡用到了`proxy_pass`代理模块核心配置，将客户端请求代理转发到一组`upstream`虚拟服务池

**格式**

```shell
Syntax: upstream name {...}
Default: -
Context: http

http{

    ...
    # 自定义名称
    upstream libs{ 

            # -域名
            # -域名:端口
            # -IP:端口

         # upstream的负载均衡，weight是权重，可以根据机器配置定义权重。weigth参数表示权值，权值越高被分配到的几率越大。
         server 127.0.0.1:9901;
         server 127.0.0.1:9902 weight=5;
         server 127.0.0.1:9903;
    }

    server {
        location / {
            proxy_pass http://libs;
        }
    }
}
```

每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除

后端服务器器在负载均衡调度中的**状态**

| 状态           | 概述                         |
| ------------ | -------------------------- |
| down         | 当前的server暂时不不参与负载均衡        |
| backup       | 预留留的备份服务器器                 |
| max_fails    | 允许请求失败的次数                  |
| fail_timeout | 经过max_fails失败后, 服务暂停时间     |
| max_conns    | 限制最⼤大的接收连接数                |
| weight       | 设置一个服务器的访问权重，数值越高，收到的请求也越多 |

```shell
# 例子
upstream load_pass {
    server 127.0.0.1:9901 down; 
    server 127.0.0.1:9902 backup;
}

server /{
    proxy_pass http://load_pass;
}
```

#### 调度策略

- 负载均衡
  
  - 轮询
    
    默认情况下使用轮询算法，不需要配置指令来激活它，它是基于在队列中谁是下一个的原理确保访问均匀地分布到每个上游服务器
  
  - IP哈希
    
    通过ip_hash指令来激活，Nginx通过IPv4地址的前3个字节或者整个IPv6地址作为哈希键来实现，同一个IP地址总是能被映射到同一个上游服务器。解决session
  
  - 最少连接数
    
    通过least_conn指令来激活，该算法通过选择一个活跃数最少的上游服务器进行连接。如果上游服务器处理能力不同，可以通过给server配置weight权重来说明，该算法将考虑到不同服务器的加权最少连接数

```shell
upstream libs{
         server 127.0.0.1:9901 weight=6;
         server 127.0.0.1:9902 weight=9;

         server 127.0.0.1:9903;
    }
```

```shell
upstream libs{
        ip_hash;
         server 127.0.0.1:9901 weight=6;
         server 127.0.0.1:9902 weight=9;
         server 127.0.0.1:9903;
    }
```

```shell
upstream backend {
    hash $request_uri;
    hash_method crc32;
    server localhost:8080;
    server localhost:8081;
}
```

### 场景

#### 跨域问题

通过添加请求头

```shell
server {
  listen 80;
  server_name server.itaolaity.com;

  add_header 'Access-Control-Allow-Origin' '*';
  add_header 'Access-Control-Allow-Credentials' 'true';
  add_header 'Access-Control-Allow-Methods' 'GET,POST,HEAD';

  location / {
    proxy_pass http://127.0.0.1:8080;

    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host  $http_host;    
  } 
}
```

#### 强制http转https

```shell
server{
    listen 80;
    server_name main.com;
    rewrite ^https://$http_host$request_uri?permanent;
    # 在错误页面和“服务器”响应头字段中启用或禁用显示nginx版本
     server_tokens off;
}
```

#### 防盗链

基于`http_refer`防盗链

```shell
location ~* \.(gif|jpg|png|swf|flv)$ {
   alios html;

   valid_referers none blocked server.itaolaity.com;

   if ($invalid_referer) {
          return 404;
   }
}
```

- **none** 代表请求的 refer 为空，也就是直接访问

- **blocked** refer 来源不为空

当请求的 `refer` 是合法的，即可以被后面任一参数所匹配， `$invalid_referer` 的值为0， 若不匹配则值为 1

![](E:\Tashi\Desktop\static\images\2019-09-22-10-13-29-image.png)

注意：只有在服务器条件下才可以被识别。

---

**反向代理**

```js
upstream local{
    server 127.0.0.1:8080;
}

server{
    listem 80;
    localhost / {
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        # HTTP的请求端真实的IP
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
           
        proxy_set_header X-Forwarded-Proto $scheme;      # 为了正确地识别实际用户发出的协议是 http 还是 https
        proxy_redirect off;            
        proxy_pass local;
    }
}
```
