## 介绍

charles相当于一个插在服务器和客户端之间的“过滤器”。

当客户端向服务器发起请求的时候，先到charles进行过滤，然后charles在把最终的数据发送给服务器；

注意：此时charles发给服务器的数据，不一定是客户端请求的数据；charles在接到客户端的请求时可以自由的修改数据，甚至可以直接Block客户端发的请求；

服务器接收请求后的返回数据，也会先到charles，经过charles过滤后再发给客户端；

同理：客户端接收的数据，不一定就是服务器返回的数据，而是charles给的数据；

## 安装

**官网下载**

官方下载地址: [https://www.charlesproxy.com/download/](https://www.charlesproxy.com/download/)

**破解链接**

[https://www.zzzmode.com/mytools/charles/](https://www.zzzmode.com/mytools/charles/)

用下载charles.jar文件替换本地charles.jar文件


## 设置系统代理

Charles 是通过将自己设置成代理服务器来完成抓包的，勾选系统代理后，系统本地发出去的请求都能被截取下来。

`proxy->windows proxy` 打勾

如果你的charles无法截取 Chrome 和 Firefox 浏览器的网络请求内容。需要在浏览器里做下修改。在 Chrome 中设置成使用系统的代理服务器设置即可，或者直接将代理服务器设置成 127.0.0.1:8888 也可达到相同效果


## 获取移动设备上的请求

主要是对手机的应用访问数据进行抓取

**步骤**

1. 设置端口`proxy->proxySettings`

![](http://itaolaity.com/20190512102001.png)

2. 查看本机IP代理地址`Help-Local IP Addresses`

3. 进入手机的WIFI配置添加代理

![](http://itaolaity.com/20190512102237.png)

这个时候Charles会弹出是否允许访问点击Allow

4. 查看是否成功 `Proxy->Access Control Settings`

这里可以看到代理服务器列表

![](http://itaolaity.com/20190512114114.png)


## 限制网速功能

模拟3G 4G网络速度的测试，高延迟的处理

`proxy->Throttle Settings` 点击`Enable Throttling`

![](http://itaolaity.com/20190512120227.png)

## 过滤网络请求

通常情况下，我们需要对网络请求进行过滤，只监控向指定目录服务器上发送的请求。

`Proxy->Recording Settings`点击`include`

![](http://itaolaity.com/20190512120749.png)

填写需要监控的协议、主机地址、端口号。只截取目标网站的封包


## 出现的问题

> ###  问题1:unknow与乱码

打开Charles抓包出现unknow和乱码的问题。需要我们安装电脑证书与手机证书。

**安装证书**

电脑直接在`Help->SSL Proxying` 点击`Install Charles Root Certificate`

移动设备点击网站[http://charlesproxy.com/getssl](http://charlesproxy.com/getssl)会自动下载证书文件然后再设置中安装证书

![](http://itaolaity.com/20190512111848.png)

最后配置抓取Https端口`Proxy->SSL Proxying Settings`

![](http://itaolaity.com/20190512112822.png)
