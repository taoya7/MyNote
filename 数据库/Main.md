# 概念

**概念**


1. 数据库管理系统（DBMS） 系统软件

- DB:数据库
- DBS:数据库系统
- DBMS:数据库管理系统

~~关系：DB包括DB与DBMS~~

2. 返回主机名的函数是(HOST_NAME())

3. 关系代数运算中，五种基本运算为(并、差、选择、投影、乘积)

4. 数据库系统的特点
    - 数据共享
    - 数据存储
    - 数据应用
    - 数据保密
    



# 数据库的基本操作

数据库就是一个拥有特定排放顺序的文件柜，而数据库对象则是存放在文件柜中的各种文件

```mysql
# 创建数据库
create database monday;

# 查看存在的数据库
show databases;

# 选择数据库
use monday;

# 删除数据库
drop database monday;

# 查看数据库的编码
show variables like '%char%';
```

# 存储引擎与数据类型

为了提高数据库管理系统的使用效率与灵活性，可以根据需要选择存储引擎。

```mysql
# 查看支持哪些存储引擎
show engines;

# 查看默认的存储引擎
show variables like '%storage_engine%';
```

**数据类型**

整形、浮点型、时间与日期类型、字符串类型

- tinyint 1字节 
- smallint 2字节
- mediumint 3字节
- int  4字节
- integer 4字节
- bigint 8字节

--- 

- float
- double
- dec(M,D)|decimal(M,D)

--- 

- date 
- datetime
- temestamp
- time
- year

---

- char [0,255]
- varchar [0,65535]
- tinytext
- text
- mediumtext
- longtext
- binary
- varbinary
- tinyblob
- blob
- mediumblob
- longblob


# 表的操作

表是包含数据库中所有数据的数据库对象。一行代表一条记录，每一列代表记录中的一个字段

**DDL**

```mysql
# 创建表
create table students(name char, age int);

# 查看表的字段信息
desc students;

# 添加字段
alter table students add id int;

# 修改字段类型
altrr table students modify id char;

# 删除字段
alter table students drop id int;

# 修改表名
rename table students to stu;

# 查看表的创建细节
show create table stu;

# 修改表的字符集
alter table stu chacter set utf8;

# 修改表的列名
alter table stu change name newname varchar(50);

# 删除表
drop table stu;
```

**DML**

```mysql

# 插入
INSERT INTO students(id,name,age) values(3,"李四",20);


# 更新
update students set age=666;

# 删除
delete from students where id=2;
```