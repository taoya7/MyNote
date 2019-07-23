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

 