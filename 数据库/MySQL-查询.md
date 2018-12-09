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

# <center>查询</center>

#### 分类
- 单表查询
- 连接查询
- 嵌套查询
- 集合查询
#### 格式
```
SELECT [ALL|DISTINCT] <目标列表达式>
                               [，<目标列表达式>] …
FROM <表名或视图名>[， <表名或视图名> ] …
[ WHERE <条件表达式> ]
[ GROUP BY <列名1> [ HAVING <条件表达式> ] ]
[ ORDER BY <列名2> [ ASC|DESC ] ]；
```

## 单表查询

查询仅涉及一个表，是一种最简单的查询操作


> 查询所有列

```sql
select *from 表名
```

> 结果集

- 数据库执行DQL语句不会对数据进行改变，而是让数据库发送结果集给客户端

- 通过查询语句查询出来的数据以表的形式展示我们称这个表为虚拟结果集。存放在内存中

- 查询返回的结果集是一张虚拟表

> 查询指定字段的数据

```sql
select field1, field2... from table_name;
```

> ###条件查询

条件查询就是在查询时给出WHERE子句，在WHERE子句中可以使用一些运算符及关键字

<import>条件查询运行符及关键字</import>

- =（等于）、!=（不等于）、<>（不等于）、<（小于）、<=（小于等于）、>（大于）、>=（大于等于）

- BETWEEN…AND；值在什么范围

- IN(set)；固定的范围值

- IS NULL；（为空） IS NOT NULL（不为空）   

- AND | OR | NOT

<import>Demo</import>

```sql
查找性别为男的记录
select *from table_name where sex="male";

查找性别为男或者地址为山西的
select *from table_name where sex="male" or address="shanxi";

查找id=1 2 3的记录
select *from table_name where id=1 or id=2 or id=3;
select *from table_name where id in(1,2,3);

查找name为null的记录
select *from table_name where name is null;

查找性别不为男的记录
select *from table_name where sex != "male";

查找年龄在18-20的记录
select *from table_name where age between 18 and 20;
```

> ### 模糊查询（[not]like）

<import>通配符</import>

-  _  ：任意一个字符
-  %：任意0-[n]个字符

<import>Demo</import>

```
查询姓名由5个字母构成的记录
select *from 表名 where  字段 like "_____"; /*一个下划线表示一个字符*/

查询姓名由5个查询姓名由5个字母构成的记录并且最后一个字没有为s
select *from 表名 where 字段 like "____s";

查询以m开头的字母
select *from 表名 where 字段 like 'm%';

查询第二个字没有为u的记录
select *from 表名 where 字段 like '_u%';

查询包含s的记录
select *from 表名 where 字段 like '%s%';

使用换码字符将通配符转义为普通字符
查询DB_Design课程的课程号和学分。
      SELECT Cno，Ccredit
      FROM Course
      WHERE Cname LIKE 'DB\_Design'      
                       ESCAPE '\'

```

<import>消除取值重复的行</import>

```sql
select distinct * from emp;
```
注意 DISTINCT短语的作用范围是所有目标列
```
错误的写法
	SELECT DISTINCT Cno，DISTINCT Grade
	FROM SC;
正确的写法
    SELECT DISTINCT Cno，Grade
    FROM SC; 
```


<import>给表起个别名</import>

```sql
select ename as 'Name' from emp;


select ename  'Name' from emp;
```


> ### 涉及空值的查询

- 使用谓词 IS NULL 或 IS NOT NULL
- “IS NULL” 不能用 “= NULL” 代替

```
查所有有成绩的学生学号和课程号。

      SELECT Sno,Cno
      FROM  SC
      WHERE  Grade IS NOT NULL;

```

> ### 对结果进行排序

- 升序：ASC；降序：DESC；缺省值为升序

<import>Demo</import>

```
-- 降序 在student表中对money字段降序

select *from student order by money desc;


-- 对所有学生的成绩进行升序排序，年龄降序排序

select *from student order by score asc, age desc;
```


> ### 使用函数


<import>常用函数</import>

- count()
- max()
- min()
- sum()
- avg()

> ### 分组查询

将查询结果按照1个或多个字段进行分组，字段值相同的为一组

```sql
select field from table_name GROUP BY field1;
```

<import>group by + group_concat()</import>

- group_concat(字段名)可以作为一个输出字段来使用

- 表示分组之后，根据分组结果，使用group_concat()来放置每一组的某字段的值的集合

```sql
SELECT gender,GROUP_CONCAT(name) from employee GROUP BY gender;
```

<import>group by + 聚合函数</import>

```
-- 查询每个部门的部门名称和每个部门的工资和
SELECT department, SUM(money) FROM employee GROUP BY department

-- 查询每个部门的部门名称以及每个部门的人数
SELECT department,COUNT(*) FROM employee GROUP BY department

-- 查询每个部门的部门名称以及每个部门工资大于1500的人数
SELECT department,COUNT(money) FROM employee WHERE money>1500 GROUP BY department;
```

<import>group by + having</import>

having作用和where一样，但having只能用于group by


<import>Having 与 Where 的区别</import>

Having是在分组后对数据进行过滤

Where是在分组前对数据进行过滤

Having后面可以使用统计函数

Where后面不可以使用分组函数


> ### limit查询

从哪一行开始查，总共要查几行

Limit 参数1，参数2

- 参数1：从哪一行开始查

- 参数2：一共要查几行

```
select * from 表名 limit 0,3;
```






## 连接查询

- 广义笛卡儿积

- 等值连接（含自然连接）

- 非等值连接查询

- 自身连接查询

- 外连接查询

- 复合条件连接查询


> 广义笛卡儿积

什么是笛卡尔集

假设集合A={a,b}，集合B={0,1,2}，则两个集合的笛卡尔积为{(a,0),(a,1),(a,2),(b,0),(b,1),(b,2)}。可以扩展到多个集合的情况

```
同时查询两个表，出现的就是笛卡尔积的结果

select count(*) from dept; -- 4
select count(*) from emp;   -- 14 

select count(*) from emp,dept; -- 56行数据
```


> 等值连接

两个表同时出现的id号（值）才显示

与多表联查约束主外键是一样，只是写法改变了

```sql
select count(*) from emp e, dept p where e.deptno=p.deptno;


select * from emp e
inner join dept p
on e.deptno=p.deptno;
```

> 外连接

- 左外连接
	- 左边表当中的数据全部查出，右边表当中，只查出满足条件的内容

- 右外连接
	- 右连接会把右当中的数据全部查出，左表当中只查出满足条件的数据

```sql
select * from stu s
left|right join score c
on s.id=c.id;
```


> 非等值连接

```sql
查询所有员工的姓名，工资，所在部门的名称以及工资的等级
select e.ename, e.salary, d.dname, g.grade from emp e
JOIN dept d ON e.deptno = d.deptno -- 员工表的 部门编号 与 部门表
JOIN salgrade g ON e.salary BETWEEN g.lowSalary AND g.heghsalary; -- 工资等级
```

> 自然连接

```sql
select * from stu
naturel join score;
```


> 自连接

自连接：自己连接自己，起别名


## 子查询

一个select语句中包含另一个完整的select语句

或两个以上SELECT，那么就是子查询语句了

- from形式

- where形式

<import>子查询的限制</import>

	- 不能使用order by子句



### 集合查询

> 合并结果集

合并结果集就是把两个select语句的查询结果合并到一起

- UNION    合并时去除重复记录

- UNION ALL     合并时不去除重复记录

```
select * from table_name1
union
select * from table_name2; 
```

注意：被合并的两个结果：列数、列类型必须相同



> 交操作

- intersect

> 差操作

- except