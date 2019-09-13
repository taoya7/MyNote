## Docker

**是什么**

docker是linux容器的一种封装，提供简单易用的容器使用接口。它是最流行的Linux容器解决方案。

docker的接口相当简单，用户可以方便的创建、销毁容器。

docker将应用程序与程序的依赖，打包在一个文件里面。运行这个文件就会生成一个虚拟容器。

程序运行在虚拟容器里，如同在真实物理机上运行一样，有了docker，就不用担心环境问题了

### 核心概念

**镜像images**

Docker镜像是用于创建Docker容器的模板

**容器Container**

容器是独立运行的一个或一组应用

**仓库Registy**

Docker仓库用来保存镜像



## 安装

**下载**

[Download](https://get.daocloud.io/)

Docker镜像<https://hub.daocloud.io/>

### Linux

**安装**

CenterOS

```shell
yum install docker

systemctl start/status docker

docker version
```



确认APT添加Https协议和CA证书

```bash
apt-get install apt-transport-https ca-certificates
```

添加新的GPG密钥

```bash
apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
```

```bash
apt-get install docker.io
```

检查是否安装成功

```bash
docker -v
>>> ocker version 18.09.1, build 4c52b90
```


![](http://itaolaity.com/20190620161505.png)

```shell
Usage:
docker [OPTIONS] COMMAND

A self-sufficient runtime for containers.

Options:
  --config=~/.docker              Location of client config files  #客户端配置文件的位置

  -D, --debug=false               Enable debug mode  #启用Debug调试模式

  -H, --host=[]                   Daemon socket(s) to connect to  #守护进程的套接字（Socket）连接

  -h, --help=false                Print usage  #打印使用

  -l, --log-level=info            Set the logging level  #设置日志级别

  --tls=false                     Use TLS; implied by--tlsverify  #

  --tlscacert=~/.docker/ca.pem    Trust certs signed only by this CA  #信任证书签名CA

  --tlscert=~/.docker/cert.pem    Path to TLS certificate file  #TLS证书文件路径

  --tlskey=~/.docker/key.pem      Path to TLS key file  #TLS密钥文件路径

  --tlsverify=false               Use TLS and verify the remote  #使用TLS验证远程

  -v, --version=false             Print version information and quit  #打印版本信息并退出



Commands:

    attach    Attach to a running container  #当前shell下attach连接指定运行镜像

    build     Build an image from a Dockerfile  #通过Dockerfile定制镜像

    commit    Create a new image from a container's changes  #提交当前容器为新的镜像

    cp    Copy files/folders from a container to a HOSTDIR or to STDOUT  #从容器中拷贝指定文件或者目录到宿主机中

    create    Create a new container  #创建一个新的容器，同run 但不启动容器

    diff    Inspect changes on a container's filesystem  #查看docker容器变化

    events    Get real time events from the server#从docker服务获取容器实时事件

    exec    Run a command in a running container#在已存在的容器上运行命令

    export    Export a container's filesystem as a tar archive  #导出容器的内容流作为一个tar归档文件(对应import)

    history    Show the history of an image  #展示一个镜像形成历史

    images    List images  #列出系统当前镜像

    import    Import the contents from a tarball to create a filesystem image  #从tar包中的内容创建一个新的文件系统映像(对应export)

    info    Display system-wide information  #显示系统相关信息

    inspect    Return low-level information on a container or image  #查看容器详细信息

    kill    Kill a running container  #kill指定docker容器

    load    Load an image from a tar archive or STDIN  #从一个tar包中加载一个镜像(对应save)

    login    Register or log in to a Docker registry#注册或者登陆一个docker源服务器

    logout    Log out from a Docker registry  #从当前Docker registry退出

    logs    Fetch the logs of a container  #输出当前容器日志信息

    pause    Pause all processes within a container#暂停容器

    port    List port mappings or a specific mapping for the CONTAINER  #查看映射端口对应的容器内部源端口

    ps    List containers  #列出容器列表

    pull    Pull an image or a repository from a registry  #从docker镜像源服务器拉取指定镜像或者库镜像

    push    Push an image or a repository to a registry  #推送指定镜像或者库镜像至docker源服务器

    rename    Rename a container  #重命名容器

    restart    Restart a running container  #重启运行的容器

    rm    Remove one or more containers  #移除一个或者多个容器

    rmi    Remove one or more images  #移除一个或多个镜像(无容器使用该镜像才可以删除，否则需要删除相关容器才可以继续或者-f强制删除)

    run    Run a command in a new container  #创建一个新的容器并运行一个命令

    save    Save an image(s) to a tar archive#保存一个镜像为一个tar包(对应load)

    search    Search the Docker Hub for images  #在docker
hub中搜索镜像

    start    Start one or more stopped containers#启动容器

    stats    Display a live stream of container(s) resource usage statistics  #统计容器使用资源

    stop    Stop a running container  #停止容器

    tag         Tag an image into a repository  #给源中镜像打标签

    top       Display the running processes of a container #查看容器中运行的进程信息

    unpause    Unpause all processes within a container  #取消暂停容器

    version    Show the Docker version information#查看容器版本号

    wait         Block until a container stops, then print its exit code  #截取容器停止时的退出状态值
```

启动|停止

```bash
service docker start
service docker stop
service docker status
chkconfig docker on #设置开机启动
```

```bash
docker run hello-world
```

![](http://itaolaity.com/20190620161541.png)



升级|卸载

```bash
sudo apt-get upgrade 

sudo apt-get purge docker.io*
```

```bash
sudo groupadd docker #添加docker用户组

sudo gpasswd -a $USER docker #将登录用户加入到docker用户组

newgrp docker #更新 用户组

docker ps #测试
```

## 镜像

### 搜索镜像

```bash
docker search mysql
```

### 下载镜像

```bash
docker pull 镜像名[:标签]
```

### 列出下载的镜像

```bash
docker images

kali# docker images
REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
daocloud.io/library/mysql   latest              c7109f74d339        8 days ago          443MB
hello-world                 latest              fce289e99eb9        5 months ago        1.84kB
```

### 删除镜像

```bash
docker rmi 镜像ID
```

## 容器

容器就像一个类的实例

### 启动容器

```bash
docker run <选项参数> <镜像名称> <运行的文件>
-i -t 让容器的标准输入保持打开 让Docker分配一个伪终端，并绑定到容器的标准输入上
-d 后台运行
--name 指定容器的名称 如果不指定会随机的名称进行指定
-p 端口映射，将主机的端口映射到容器的一个端口 主机端口:内部容器
-c 运行一段shell命令

kali# docker run -d -p 8888:8080 tomcat
771845e1556a72d06614af22e03586f70f288c2349fbd11b18608e5d9c945b16
```

![](http://itaolaity.com/20190620161558.png)



### 查看容器列表

```bash
docker ps  #查看运行中的容器

-a 查看所有的容器

kali# docker ps                          
CONTAINER ID        IMAGE                  COMMAND             CREATED             STATUS              PORTS               NAMES
38a27b6fcfd6        tomcat:8.5-jre8-slim   "catalina.sh run"   2 minutes ago       Up 2 minutes        8080/tcp            mtomcat
```

### 停止容器

```bash
docker stop 容器ID

kali# docker stop 38a27b6fcfd6
38a27b6fcfd6
```

###  启动容器

```bash
docker start

kali# docker ps -a
CONTAINER ID        IMAGE                  COMMAND             CREATED             STATUS                     PORTS               NAMES
38a27b6fcfd6        tomcat:8.5-jre8-slim   "catalina.sh run"   15 hours ago        Exited (255) 2 hours ago   8080/tcp            mtomcat
ed24dd3bd2b1        hello-world            "/hello"            19 hours ago        Exited (0) 19 hours ago                        boring_rosalind
kali# docker start 38a27b6fcfd6
38a27b6fcfd6
kali# docker ps
CONTAINER ID        IMAGE                  COMMAND             CREATED             STATUS              PORTS               NAMES
38a27b6fcfd6        tomcat:8.5-jre8-slim   "catalina.sh run"   16 hours ago        Up 3 seconds        8080/tcp            mtomcat
```

### 删除容器

```bash
docker rm 容器ID
```

### 容器日志

```bash
docker logs 容器ID
```

![](http://itaolaity.com/20190620161613.png)

### 进入容器

```shell
docker exec -it [ID] 
```





## 其他

### MySQL

```shell
curl -sSL https://get.daocloud.io/daotools/set_mirror.sh | sh -s http://95822026.m.daocloud.io #配置加速器

docker search mysql 

docker pull mysql #下载安装

docker images # 查看是否安装成功|拥有

docker run --name imysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql #端口映射
```

![](http://itaolaity.com/20190620161638.png)

**使用自定义MySQL配置文件**

默认以`/etc/mysql/my.cnf`为配置文件

创建自己的配置文件目录 `/my/mysql/config.cnf`

```shell
docker run --name imysql2 -v /my/mysql/config.cnf:/etc/mysql/my.cnf -e MYSQL_ROOT_PASSWORD=1234 -d mysql
```





### 部署Nginx

1. 下载Nginx镜像

```shell
kali# docker pull daocloud.io/library/nginx:1.13.0-alpine
1.13.0-alpine: Pulling from library/nginx
b2388ca7fa65: Pull complete 
7947be538089: Pull complete 
d16f692df913: Pull complete 
0dbd6ee41762: Pull complete 
Digest: sha256:5c36f962c506c379bd63884976489c9c5e700c1496a6e8ea13dc404b1d258f76
Status: Downloaded newer image for daocloud.io/library/nginx:1.13.0-alpine
```

2. 启动

```shell
docker run --name my-nginx -d -p 8080:80 nginx 
```

```shell
docker run --name my-nginx \
			-v /host/path/nginx.conf:/etc/nginx/nginx.conf:ro \
			-v /some/html:/usr/share/nginx/html:ro \
			-p 8080:80 \
			-d nginx
```

`-v` 参数语法为 `-v host dir:container dir[:ro|rw]`

### 参考

<https://github.com/jaywcjlove/docker-tutorial>









