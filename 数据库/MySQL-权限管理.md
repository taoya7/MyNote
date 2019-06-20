
# 权限管理

> 什么是权限

限制一个用户能够做什么事情，SQL中，可以设置全局权限，指定数据库权限，指定表权限，指定字段权限

> 有哪些权限

* **create ** 创建数据库，表，索引权限
* **drop**  删除数据库或表权限
* **alter**  更改表权限
* **delete ** 删除数据权限
* **index ** 索引权限
* **insert**  插入权限
* **select ** 查询权限
* **update**  更新权限
* **create view**  创建视图权限
* **execute**  执行存储过程权限

> 创建用户

```
create user '用户名'@'localhost'identified by '密码';
```

> 删除用户

```
drop user '用户名'@'localhost';
```

> 分配权限

```
grant 权限 (columns) on 数据库对象 to 用户 identified by '密码' with grant option
```

> 查看权限

```
show grands for root@localhost
```

> 删除权限

```
revoke 权限 on 数据库对象 from 用户;
```

**Demo**

创建一个超级管理员Tashi密码1234拥有所有权限

```mysql
grant all privileges on *.* to Tashi@localhost
identified by '1234'
with grant option;
```



