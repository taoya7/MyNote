#### 1. 查看ssh服务状态

```
# /etc/init.d/ssh status
```

#### 2.修改配置文件

```
# vim /etc/ssh/sshd_config
```

```
#PermitRootLogin prohibit-password
PermitRootLogin yes
```

```shell
#PasswordAuthentication yes
PasswordAuthentication yes
```

#### 3. 启动ssh服务

```
# /etc/init.d/ssh start
```

- 查看服务状态
  `# /etc/init.d/ssh status`

- 开机启动

```shell
# update-rc.d ssh enable
```

