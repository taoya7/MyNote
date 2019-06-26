---
title: DHCP动态管理主机地址
date: 2019-05-08 14:17:09
tags: [Linux]
categories: ["Linux"]
---
> DHCP动态管理主机地址

# DHCP(Dynamic Host Configuration Protocol)

DHCP是动态主机配置协议，动态主机设置协议是一个局域网的网络协议，使用UDP协议工作。该协议允许服务器向客户端动态分配 IP 地址和配置信息。DHCP协议支持C/S结构，主要分为客户端和服务端。

用于自动管理局域网内主机的IP地址，子网掩码、网关地址、Dns地址。

有效的提升IP地址的利用率，提高配置效率，降低管理与维护成本


## 安装

**是否安装**

npm -qa|grep dhcp


**安装**

yum install dhcp

**查看配置文件内容**

cat /etc/dhcp/dhcpd.conf

```
# DHCP Server Configuration file.
# see /usr/share/doc/dhcp*/dhcpd.conf.example
# see dhcpd.conf(5) man page
```

**启动**

service dhcpd start //RHEL6

systemctl restart dhcpd //RHEL7

## 配置

![](https://i.loli.net/2019/05/08/5cd239ccaf61e.png)

- 网段声明：以subnet字段开头

- 主机声明：以host字段开头

- 配置选项：由“option”引导，后面跟具体的配置关键字和对应的值

- 配置参数：由配置关键字和对应的值组成，总是以分号“；”结束

这是示例配置文件

```
# dhcpd.conf
#
# Sample configuration file for ISC dhcpd
#

# option definitions common to all supported networks...
option domain-name "example.org";
option domain-name-servers ns1.example.org, ns2.example.org;

default-lease-time 600;
max-lease-time 7200;

# Use this to enble / disable dynamic dns updates globally.
#ddns-update-style none;

# If this DHCP server is the official DHCP server for the local
# network, the authoritative directive should be uncommented.
#authoritative;

# Use this to send dhcp log messages to a different log file (you also
# have to hack syslog.conf to complete the redirection).
log-facility local7;

# No service will be given on this subnet, but declaring it helps the 
# DHCP server to understand the network topology.

subnet 10.152.187.0 netmask 255.255.255.0 {
}

# This is a very basic subnet declaration.

subnet 10.254.239.0 netmask 255.255.255.224 {
  range 10.254.239.10 10.254.239.20;
  option routers rtr-239-0-1.example.org, rtr-239-0-2.example.org;
}

# This declaration allows BOOTP clients to get dynamic addresses,
# which we don't really recommend.

subnet 10.254.239.32 netmask 255.255.255.224 {
  range dynamic-bootp 10.254.239.40 10.254.239.60;
  option broadcast-address 10.254.239.31;
  option routers rtr-239-32-1.example.org;
}

# A slightly different configuration for an internal subnet.
subnet 10.5.5.0 netmask 255.255.255.224 {
  range 10.5.5.26 10.5.5.30;
  option domain-name-servers ns1.internal.example.org;
  option domain-name "internal.example.org";
  option routers 10.5.5.1;
  option broadcast-address 10.5.5.31;
  default-lease-time 600;
  max-lease-time 7200;
}

# Hosts which require special configuration options can be listed in
# host statements.   If no address is specified, the address will be
# allocated dynamically (if possible), but the host-specific information
# will still come from the host declaration.

host passacaglia {
  hardware ethernet 0:0:c0:5d:bd:95;
  filename "vmunix.passacaglia";
  server-name "toccata.fugue.com";
}

# Fixed IP addresses can also be specified for hosts.   These addresses
# should not also be listed as being available for dynamic assignment.
# Hosts for which fixed IP addresses have been specified can boot using
# BOOTP or DHCP.   Hosts for which no fixed address is specified can only
# be booted with DHCP, unless there is an address range on the subnet
# to which a BOOTP client is connected which has the dynamic-bootp flag
# set.
host fantasia {
  hardware ethernet 08:00:07:26:c0:a5;
  fixed-address fantasia.fugue.com;
}

# You can declare a class of clients and then do address allocation
# based on that.   The example below shows a case where all clients
# in a certain class get addresses on the 10.17.224/24 subnet, and all
# other clients get addresses on the 10.0.29/24 subnet.

class "foo" {
  match if substring (option vendor-class-identifier, 0, 4) = "SUNW";
}

shared-network 224-29 {
  subnet 10.17.224.0 netmask 255.255.255.0 {
    option routers rtr-224.example.org;
  }
  subnet 10.0.29.0 netmask 255.255.255.0 {
    option routers rtr-29.example.org;
  }
  pool {
    allow members of "foo";
    range 10.17.224.10 10.17.224.250;
  }
  pool {
    deny members of "foo";
    range 10.0.29.10 10.0.29.230;
  }
}
```

### **全局配置**

- option domain-name 默认搜索区域

- option domain-name-servers  DNS服务器地址

- option subnet-mask 定义客户端的子网掩码

- ignore client-updates; 忽略客户端更新DNS记录

- default-lease-time 默认租约时间（单位为秒）
表示客户端可以从DHCP服务器租用某个IP地址的默认时间

- max-lease-time 最大租约时间（单位为秒）
表示允许DHCP客户端请求的最大租约时间

- ddns-update-style 动态DNS更新模式
	- none 不支持动态更新
	- interim 互动更新模式
设置与DHCP服务相关联的DNS数据动态更新模式

### **网段配置**

只作用指定的网段，可以声明网段可以有多个

- range 	定义用于分配的IP地址池
- option routers 192.168.1.254; 定义客户端的网关地址
- option broadcast-address 192.168.1.255; 定义客户端的广播地址
- default-lease-time 600; 默认续约时间
- max-lease-time 7200; 最大续约时间

```
subnet 192.168.1.0 netmask 255.255.255.224 {
  range 192.168.1.100 192.168.1.200; //IP地址池
  option domain-name-servers ns1.internal.example.org;
  option domain-name "internal.example.org";
  option routers 192.168.1.254;
  option broadcast-address 192.168.1.255;
  default-lease-time 600;
  max-lease-time 7200;
}
```

### **host主机声明（可选）**

host声明用于设置单个主机的网络属性，通常用于为网络打印机或个别服务器分配固定的IP地址（保留地址），这些主机的共同特点是要求每次获取的IP地址相同，以确保服务的稳定性

- hardware 指定网卡接口的类型与MAC地址
- fixed-address 	将某个固定的IP地址分配给指定主机

```
host prtsvr{ //设置Host关键字 
	hardware ethernet 00:0c:c3:32:23:21; //网卡地址
	fixed-address 192.168.1.100; //指定保留的IP地址
}
```

## 启动

```
systemctl start dhcpd  启动Dhcp服务

systemctl enable dhcpd  加入开机启动项
```




