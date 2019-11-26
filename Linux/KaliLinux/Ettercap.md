

> Ettercap

是一款免费开源的网络安全工具，用于对局域网进行中间人攻击



### 使用

```shell
ettercap -G
```

### DNS欺骗

配置Ettercap使用的NDS配置文件

```shell
vim /etc/ettercap/etter.dns

# 添加
* A 121.42.124.130
```

- 扫描主机

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-03-10-image.png)

- 查看主机列表

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-03-45-image.png)

- 选择攻击的目标主机，添加到目标1

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-05-16-image.png)

- 开始ARP毒化



![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-06-14-image.png)

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-06-33-image.png)

- 激活dns欺骗插件

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-07-45-image.png)



- 开始执行攻击

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-08-26-image.png)

- 主机

```shell
ipconfig flush
```

- 结果

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-04-21-23-02-image.png)
