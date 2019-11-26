

### 准备工作

```shell
pip install redis
```

## 操作

### 连接数据库

 传入连接的地址，运行端口，使用的数据库和密码

- 连接方式1

```python


from redis import StrictRedis

# 1. 声明Strict对象
redis = StrictRedis(host="127.0.0.1", port=6379, db=0)



redis.set("name", "Linis")

print(redis.get("name"))
```

- 连接方式2

  redis-py使用connection pool来管理对一个redis server的所有连接，避免每次建立、释放连接的开销。默认，每个Redis实例都会维护一个自己的连接池。可以直接建立一个连接池，然后作为参数Redis，这样就可以实现多个Redis实例共享一个连接池

  ```python
  from redis import StrictRedis, ConnectionPool
  
  pool = ConnectionPool(host="127.0.0.1", port="6379", db=0)
  redis = StrictRedis(connection_pool=pool)
  
  redis.set("name", "tashi")
  print(redis.get("name"))
  ```

  `ConnectionPool` 同时也可以传入不同的URL来连接

  ```shell
  redis://[:password]@host:port/db    # TCP连接
  rediss://[:password]@host:port/db   # Redis TCP+SSL 连接
  unix://[:password]@/path/to/socket.sock?db=
  ```

  

### 键操作

判断一个key是否存在`existe(name)`

```shell
redis.exists("name") # 1
```

删除一个key

```shell
redis.delete("name") # 1 or 0
```

查看类型key

```shell
redis.type(name) # b'string'
```

查看所有的key

```shell
redis.keys(pattern)  # pattern 匹配规则


# 例子
redis.keys("*")
```

重命名key

```shell
rename(src, now)
```

获取随机的一个key

```shell
randomkey()
```

获取当前数据库中key的个数

```shell
dbsize()
```

设置key的过期时间，单位为秒

```shell
expire(key, time)

# 例子
redis.expire("name", 10)
```

获取key的过期时间,单位为秒

```shell
ttl(key_name)
```

移动key到其他数据库

```shell
move(name, db)
```

删除当前选择数据库中的所有key

```shell
flushdb()
```

删除所有数据库中的key

```shell
flushall()
```







