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
/*		margin:0;
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

# <center>数据完整性</center>

#### 分类

- 实体完整性

- 域完整性

- 引用完整性

> ### 实体完整性

<import>什么是实体完整性</import>

表中的一行(一条记录)代表一个实体（entity）

<import>实体完整性的作用</import>

标识每一行数据不重复。行级约束

<import>约束类型</import>

- 主键约束（primary key）

- 唯一约束(unique)

- 自动增长列(auto_increment)

### 主键约束

<import>特点</import>

数据唯一，而且不能为null

<import>添加方式</import>

```
-- 方式1
CREATE TABLE 表名 (
    字段  数据类型 primary key,
    字段  数据类型
);

-- 方式2
CREATE TABLE 表名 (
    字段1  数据类型,
    字段2  数据类型,
    字段3  数据类型,

    primary key(字段)
);

-- 方式3
CREATE TABLE 表名 (
    字段1  数据类型,
    字段2  数据类型,
    字段3  数据类型,

    primary key(主键1,主键2) --两个字段数据同时相同时,才违反联合主键约束
);

-- 方式4
ALTER TABLE student ADD constraint primary key(字段);
```

### 唯一约束

- 指定列的数据不可以重复

- 可以为空

```
CREATE TABLE 表名(
    字段  数据类型,
    字段  数据类型 UNIQUE
);
```


### 自动增长列

- 指定列的数据自动增长

- 即使删除数据，还是从删除的序号继续向下

```
CREATE TABLE 表名(
    字段 数据类型 PRIMARY KEY AUTO_INCREMENT,
    字段 数据类型
);
```


> ### 域完整性

限制此单元格的数据正确,不对照此列的其他单元格比较

- 数据类型（数值类型、日期类型、字符串类型）

- 非空约束（not null）

- 默认值约束（default）

```
CREATE TABLE 表名(
    字段 数据类型 not null,
    字段 数据类型
);
```

```
CREATE TABLE 表名(
    字段 数据类型 not null default"男",
    字段 数据类型
);
```

> ### 参照完整性

是表与表之间的一种对应关系，通常情况下可以通过设置两表之间的主键、外键关系，或者编写两表的触发器来实现。

有对应参照完整性的两张表格，在对他们进行数据插入、更新、删除的过程中，系统都会将被修改表格与另一张对应表格进行对照，从而阻止一些不正确的数据的操作。

数据库的主键和外键类型一定要一致；两个表必须得要是InnoDB类型

设置参照完整性后 ，外键当中的内值，必须得是主键当中的内容

```
CREATE TABLE student(
    id int PRIMARY KEY, --设置主键
    name char not null, --非空约束
    sex char default"男" --默认值约束
);


CREATE TABLE score(
    sid int,
    score double,
    constraint fk_stu_score_sid foreign KEY(sid) references student(id)
);
```