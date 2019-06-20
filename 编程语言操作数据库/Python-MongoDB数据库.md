# MongoDB

MongoDB,是一个基于分布式文件存储的开源数据库系统

**下载安装**

[官网下载](http://downloads.mongodb.com/)

**运行MongoDB**

1. 添加环境变量

2. 运行`mongod --dbpath D:\MongoDB\Server\4.0\data` 

**将MongoDB制作Windows服务**

1. 创建配置文件mongod.cfg

```cfg
logpath=D:\MongoDB\Server\4.0\log\mongod.log
dbpath=D:\MongoDB\Server\4.0\data
```

1. 安装mongodb服务

```bash
mongod --config "D:\MongoDB\Server\4.0\mongod.cfg" install 
```

## MongoDB常见概念



|   SQL术语   | MongoDB术语 |             说明              |
| :---------: | :---------: | :---------------------------: |
|  database   |  database   |            数据库             |
|    table    | collection  |        数据库的表/集合        |
|     row     |  document   |         数据的行/文档         |
|   column    |    field    |          数据字段/域          |
|    index    |    index    |             索引              |
|    table    |    joins    |      表连接MongoDB不支持      |
| primary key | primary key | 主键MongoDB自动将id设置为主键 |

**MongoDB**

- 数据库
- 集合
- 文档

**文档**

就是数据库的一行，文档是一个对象由 `键值对 `构成 是Json的扩展形式

```json	
{"name":"Tony","age":"18"}
```

**集合**

相当于关系型数据库的表，可以存储多个文档，结构不固定



## MongoDB数据类型

| 类型      | 说明                        |
| --------- | --------------------------- |
| Object ID | 文档的ID （唯一且自动创建） |
| String    | 字符串                      |
| Boolean   | 布尔值                      |
| Integer   | 整数值                      |
| Double    | 浮点类型                    |
| Arrays    | 数据类型                    |
| Object    | 嵌入式文档即嵌套文档        |
| Null      | 存储Null值                  |
| Timestamp | 时间戳                      |
| Date      | 日期或时间类型              |



## MongoDB基本操作

1. `db`查看当前的数据库
2. `show dbs` 查看所有的数据库
3. `use db_name` 切换数据库|创建数据库
4. `db.dropDatabase()`删除当前指向的数据库

5. `db.set_name.insert(value)` 添加数据到指定集合中
6. `db.set_name.find()`从指定集合查找数据
7. 查看已有的集合`show collections` 或者`show tables`

### 删除数据库

```bash
use student #创建数据库

db.score.insert({"name":"Tony","math":"96"}) #插入数据

show dbs #查看所有数据库

db.dropDatabase() #删除数据库
```

### 删除集合

`drop()`方法来删除集合。成功返回true失败返回false。

```bash
use test #切换数据库

db.createCollection("score") #创建集合 
> { "ok" : 1 }

show tables #查看所有集合
> score

db.score.drop() #删除集合
> true
```

### 创建集合

`db.createCollection(name, options)`

- name 创建集合的名称
- options 
  - `capped` 是否为固定集合
  - `autoIndexId` 自动在 _id 字段创建索引
    - true
    - false（默认）
  - `size` 集合包含文档的最大数量
  - `max`  集合指定最大值

### 文档

`insert() `或者`save()`方法可以向集合中插入文档

```bash
db.score.insert({ 
	"title":"学生信息",
	"by":"linis",
	"like":"999"
})

db.score.find() #查看已插入文档
```

`update()` 或者`save()`方法来更新文档

```bash
db.score.update(
	查询条件,
	更新对象,
	{
		upsert: Boolean,
		multi: Boolean,
		writeConcern: document
	}
)
```

- `upsert `如果不存在update的记录，是否插入objNew,true为插入，默认是false，不插入

- `multi`默认是false,只更新找到的第一条记录，如果这个参数为true,就把按条件查出来多条记录全部更新
- `writeConcern`抛出异常的级别

## Python操作

> 安装

```bash
pip install pymongo
```



1. **连接MongoDB**

   - 方式一

     ```python
     import pymongo
     
     client = pymongo.MongoClient("127.0.0.1",port=27017)
     ```

   - 方式二

     ```python
     import pymongo
     
     client = pymongo.MongoClient("mongodb://localhost:27017/")
     ```

     

2. **指定数据库**
```python
#写法一
db = client.test

#写法二
db = client['test']
```
3. **指定集合**
```python
collection = db.score
```

4. **插入数据**

   `insert()` `insert_one()` `insert_many()`

```python
import pymongo

client = pymongo.MongoClient("mongodb://localhost:27017/")
db = client.test
collection = db.score
student = {
"id":"20173030222",
"name":"Tony",
"age":18
}
result =collection.insert(student)
print(result) # 返回_id 
```

当然也可以插入多条数据

```bash
result = collection.insert([data_1,data_2])
```

5. **查询操作**

   `find()` `find_one()` 


查询单条数据返回结果是一个字典

```python
result = collection.find_one({"name":"Tony"}) #查询姓名为Tony的文档
print(result)
```

查询多条数据返回结果是一个Cursor类型

```python
result = collection.find({"name":"Tony"})
for item in result:
    print(item)
print(result)

>>>
{'_id': ObjectId('5d06070fcdd965b2dd284b72'), 'id': '20173030222', 'name': 'Tony', 'age': 18}
{'_id': ObjectId('5d06086820c6220b2f4729d9'), 'name': 'Tony', 'age': 666.0}
<pymongo.cursor.Cursor object at 0x000001E8741CD898>
```

6. **计数**

看看查询结果的返回数量

`count()`

```python
count = collection.find().count()
print(count)
```

7. **排序**

```python
res = collection.find().sort("field_name", pymongo.ASCENDING)
```

- pymongo.ASCENDING 升序
- pymongo.DESCENDING 降序

8. **偏移**

筛选性的查询元素

`skip(num)` `limit(num)`

9. **更新操作**

`update()` `update_one()`

```python
import pymongo

client = pymongo.MongoClient("mongodb://localhost:27017/")
db = client.test
collection = db.score

stu = collection.find_one({"name":"Tony"})  #查找

res = collection.update(stu, {"$set":{"name":"Amy"}})

print(res)
```

10. **删除文档操作**

`delete()` 

`delete_one()`

`remove()`

```
res = collection.remove({"name":"Amy"})
print(res)

>>> {'n': 1, 'ok': 1.0}
```

















   







