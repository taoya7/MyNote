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
# <center>常用函数</center>

### 什么是函数
- 函数是事先提供好的一些功能可以直接使用

- 函数可以在select语句及其语句

- 也可可以在update、delete语句当中

### 函数的分类

- 字符串函数
- 数值函数
- 日期与时间函数
- 其他函数

> ### 字符串函数

### Concat\(s1,s2,s3...\)

* 将传入的字符串连接成一个完整的字符

```mysql
mysql> select concat(123,23,34454,123) as f;
+---------------+
| f             |
+---------------+
| 1232334454123 |
+---------------+
1 row in set (0.00 sec)
```

注意：任何字符串与null进行连接的结果都是null

### Concat\_ws\('-',s1,s2\)

* 以指定分隔符来拼接字符串

```mysql
mysql> select concat_ws('-','0359','123456');
+--------------------------------+
| concat_ws('-','0359','123456') |
+--------------------------------+
| 0359-123456                    |
+--------------------------------+
1 row in set (0.00 sec)
```

### Insert\(str,x,y,instr\)

* 将字符串str从x位置开始，y个字符长度替换为指定字符

```mysql
mysql> select insert('abcde',3,2,'oO');
+--------------------------+
| insert('abcde',3,2,'oO') |
+--------------------------+
| aboOe                    |
+--------------------------+
1 row in set (0.00 sec)
```

```mysql
mysql> select insert('abcde',3,5,'oO');
+--------------------------+
| insert('abcde',3,5,'oO') |
+--------------------------+
| aboO                     |
+--------------------------+
1 row in set (0.00 sec)
```

### lower\(\) 与 Upper\(\)

* 将字符串转换大小写

大写转小写

```mysql
mysql> select lower('ABCDE');
+----------------+
| lower('ABCDE') |
+----------------+
| abcde          |
+----------------+
1 row in set (0.00 sec)
```

小写转大写

```MYSQL
mysql> select upper('abcde');
+----------------+
| upper('abcde') |
+----------------+
| ABCDE          |
+----------------+
1 row in set (0.00 sec)
```

### Left\(str,n\) 与 Right\(str,n\)

* 分别返回字符串 左边/右边 n个字符，如果第二个参数为NULL，不返回任何字符

左取

```mysql
mysql> select left('abcde',3);
+-----------------+
| left('abcde',3) |
+-----------------+
| abc             |
+-----------------+
1 row in set (0.00 sec)
```

右取

```mysql
mysql> select right('abcde',3);
+------------------+
| right('abcde',3) |
+------------------+
| cde              |
+------------------+
1 row in set (0.00 sec)
```

### Lpad\(str,n,pad\) 与 Rpad\(str,n,pad\)

* 用字符串pad对str左边/右边 进行填充直到填充到N个字符

填充abc 7个字符，所以只用了1234

```mysql
mysql> select lpad('abc',7,'123456789');
+---------------------------+
| lpad('abc',7,'123456789') |
+---------------------------+
| 1234abc                   |
+---------------------------+
1 row in set (0.00 sec)
```

```mysql
mysql> select rpad('abc',7,123456);
+----------------------+
| rpad('abc',7,123456) |
+----------------------+
| abc1234              |
+----------------------+
1 row in set (0.00 sec)
```

### Ltrim\(\) 与 Rtrim\(\) 与 trim\(\)

* 去掉字符串当中的空格

去掉左边空格

```mysql
mysql> select ltrim('     abc');
+-------------------+
| ltrim('     abc') |
+-------------------+
| abc               |
+-------------------+
1 row in set (0.00 sec)
```

去掉右边空格

```mysql
mysql> select rtrim('     abc     ');
+------------------------+
| rtrim('     abc     ') |
+------------------------+
|      abc               |
+------------------------+
1 row in set (0.00 sec)
```

去掉两边空格

```mysql
mysql> select trim('     abc     ');
+-----------------------+
| trim('     abc     ') |
+-----------------------+
| abc                   |
+-----------------------+
1 row in set (0.00 sec)
```

### Repeat\(str,x\)

* 返回字符串重复x次的结果

```mysql
mysql> select repeat('a',10);
+----------------+
| repeat('a',10) |
+----------------+
| aaaaaaaaaa     |
+----------------+
1 row in set (0.00 sec)
```

### Replace\(str,a,b\)

* 用字符串b替换字符串str中的所有字符串a

```mysql
mysql> select replace('Tom','om','ash');
+---------------------------+
| replace('Tom','om','ash') |
+---------------------------+
| Tash                      |
+---------------------------+
1 row in set (0.00 sec)
```

### substring\(str,x,y\)

* 返回字符串str第x位置起y个字符长度的字符

```mysql
mysql> select substring('abcde',2,3);
+------------------------+
| substring('abcde',2,3) |
+------------------------+
| bcd                    |
+------------------------+
1 row in set (0.00 sec)
```

### Length\(\)

* 返回字符串的长度

```mysql
mysql> select length('tashi');
+-----------------+
| length('tashi') |
+-----------------+
|               5 |
+-----------------+
1 row in set (0.00 sec)
```




> ### 数值函数

### Abs\(x\)

* 返回x的绝对值

### Ceil\(x\)

* 向上取整数

### Floor\(\)

* 向下取整数

### Mod\(x,y\)

* 返回x/y的模

### Rand\(\)

* 返回0-1的随机值

### Round\(m,n\)

* 返回参数m的四舍五入的n位小数的值

### Truncate\(m,n\)

* 截断m数n位小数的结果


> ### 日期与时间

**获取当前时间和日期**

### now\(\)

```mysql
mysql> select now();
+---------------------+
| now()               |
+---------------------+
| 2018-11-16 18:04:25 |
+---------------------+
1 row in set (0.00 sec)
```

### current\_timestamp\(\)

```mysql
mysql> select current_timestamp();
+---------------------+
| current_timestamp() |
+---------------------+
| 2018-11-16 18:05:01 |
+---------------------+
1 row in set (0.00 sec)
```

### localtime\(\)

```mysql
mysql> select localtime();
+---------------------+
| localtime()         |
+---------------------+
| 2018-11-16 18:05:43 |
+---------------------+
1 row in set (0.00 sec)
```

### sysdate\(\)

```mysql
mysql> select sysdate();
+---------------------+
| sysdate()           |
+---------------------+
| 2018-11-16 18:06:11 |
+---------------------+
1 row in set (0.00 sec)
```

**获取日期**

### curdate\(\)

```
mysql> select curdate();
+------------+
| curdate()  |
+------------+
| 2018-11-16 |
+------------+
1 row in set (0.00 sec)
```

### current\_date\(\)

```
mysql> select current_date();
+----------------+
| current_date() |
+----------------+
| 2018-11-16     |
+----------------+
1 row in set (0.00 sec)
```

**获取时间**

### curtime\(\)

```
mysql> select curtime();
+-----------+
| curtime() |
+-----------+
| 18:08:34  |
+-----------+
1 row in set (0.00 sec)
```

### current\_time\(\)

```
mysql> select current_time();
+----------------+
| current_time() |
+----------------+
| 18:09:06       |
+----------------+
1 row in set (0.00 sec)
```
>**显示日期和时间**

时间戳：从1970年1月1日开始所经过的秒数

* #### Unix方式显示

```
mysql> select  unix_timestamp();
+------------------+
| unix_timestamp() |
+------------------+
|       1542363925 |
+------------------+
1 row in set (0.00 sec)
```

转换

```
mysql> select from_unixtime(unix_timestamp(now()));
+--------------------------------------+
| from_unixtime(unix_timestamp(now())) |
+--------------------------------------+
| 2018-11-16 18:26:18                  |
+--------------------------------------+
1 row in set (0.00 sec)
```

* #### UTC方式显示

```
mysql> select utc_date();
+------------+
| utc_date() |
+------------+
| 2018-11-16 |
+------------+
1 row in set (0.00 sec)
```

```
mysql> select utc_time();
+------------+
| utc_time() |
+------------+
| 10:27:43   |
+------------+
1 row in set (0.00 sec)
```
> **计算日期和时间**

adddate\(\) 计算5天后的日期

```
mysql> select now(), adddate(curdate(),5);
+---------------------+----------------------+
| now()               | adddate(curdate(),5) |
+---------------------+----------------------+
| 2018-11-16 18:28:56 | 2018-11-21           |
+---------------------+----------------------+
1 row in set (0.00 sec)
```

subdate\(\) 计算5天前的日期

```
mysql> select now(), subdate(curdate(),5);
+---------------------+----------------------+
| now()               | subdate(curdate(),5) |
+---------------------+----------------------+
| 2018-11-16 18:29:52 | 2018-11-11           |
+---------------------+----------------------+
1 row in set (0.00 sec)
```

* datediff\(date1, date2\)     计算两天相隔的天数

```
mysql> select datediff(now(), adddate(curdate(),5)) as 两日期相隔;
+------------+
| 两日期相隔 |
+------------+
|         -5 |
+------------+
1 row in set (0.00 sec)
```















