<style type="text/css">
    h1 {

        font-weight: 400;
    }
    img {
        border-radius: 10px;
        box-shadow: 0 2px 8px rgba(0,0,0,.3);
    }
    body {
        background-color: #FDF6E3;
/*      margin:0;
        outline:none;
        border:none;
        max-width: 100%;*/

    }
    import::before{
        display: inline-block;
        content: "";
        width: 10px;
        height: 10px;
        background-color: red;
        margin-right: 10px;
        border-radius: 50%;
    }
    import {
        font-size:14px;
        font-weight: bold;
        padding:0.55rem;
        border-radius:5px; 
        color:#eb5055;
    }
    .markdown-body blockquote{
        border-left: 4px solid tomato;
    }
</style>
# <center>Python API</center>

### 数据库的安装  
```
pip install PyMySQL
```
> 连接数据库

```python
import pymysql

# 打开数据库
db = pymysql.connect(host='localhost',user='root',password='',port=3306)
# 使用cursor()方法创建一个游标对象cursor
cursor = db.cursor()
# 使用execute() 方法执行SQL查询
cursor.execute('SELECT VERSION()')
# 使用fetchone()  方法获取单条数据 
data = cursor.fetchone()

print(data)

# 关闭数据库连接
db.close()
```

>  创建表

```python
import pymysql

# 打开数据库连接
db = pymysql.connect(host='localhost',user='root',password='',port=3306,db='python_test')

# 使用cursor() 方法获取操作游标
cursor = db.cursor()

# SQL插入语句
sql = """
    create table if not exists students(
        id int primary key  auto_increment,
        name varchar(255) not null,
        age int not null
    )
"""

try:
    cursor.execute(sql)
except:
    # 发生错误时回滚
    db.rollback()

# 关闭数据库
db.close()
```

### 数据库查询操作
**Python查询Mysql使用 fetchone() 方法获取单条数据, 使用fetchall() 方法获取多条数据**

- cursor.fetchone()  #接收返回的第一行数据
- cursor.fetchmany(n) #接收返回的n行数据
- cursor.fetchall() #接受全部的返回结果行
- rowcount():这是一个只读属性，并返回执行execute()方法后影响的行数

获取所有记录
```python
import pymysql

# 打开数据库连接
db = pymysql.connect(host='localhost',user='root',password='',port=3306,db='python_test')

# 使用cursor() 方法获取操作游标
cursor = db.cursor()

sql = """
    select * from students

"""

try:
    # 执行语句
    cursor.execute(sql)

    # 获取所有记录
    results = cursor.fetchall()
    for row in results:
        print(row)

except Exception as e:
    print(e)


(1, 'zs', 18)
(2, 'ls', 15)
(4, 'wu', 15)
```

### 数据库中的增加，删除，修改操作

- conn.commit()方法可实现数据的插入。对于数据的插入，更新，删除操作，都需要调用该方法才能生效

- rollback()方法执行数据回滚，相当于什么都没有发生过

标准写法
```python
try:
	cursor.execute(sql)
	db.commit()
except:
	db.rollback()
```

> 插入一条记录

```python
import pymysql

# 打开数据库连接
db = pymysql.connect(host='localhost',user='root',password='',port=3306,db='python_test')

# 使用cursor() 方法获取操作游标
cursor = db.cursor()

# SQL插入语句
sql = """
    insert into students(id,name,age) values(7,'a',16)
"""

try:
    cursor.execute(sql) #执行sql语句
    db.commit() #提交到数据库执行
except:
    # 发生错误时回滚
    db.rollback()

# 关闭数据库
db.close()
```

> 更新操作

```python
# SQL插入语句
sql = """
    # 将姓名位zs的 改为tashi
    update students set name='tashi' where name='zs'
"""

try:
    cursor.execute(sql) #执行sql语句
    db.commit() #提交到数据库执行
except:
    # 发生错误时回滚
    db.rollback()

# 关闭数据库

db.close()
```
> 删除一条数据

```python
# SQL插入语句
sql = """
    delete from students where name = 'tashi'
"""

try:
    cursor.execute(sql) #执行sql语句
    db.commit() #提交到数据库执行
except:
    # 发生错误时回滚
    db.rollback()

# 关闭数据库
db.close()
```