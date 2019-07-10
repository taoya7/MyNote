---
title: CSV文件存储
date: 2019-4-30 17:00:14
tags: ["爬虫"]
categories: Python
---

# CSV文件存储

CSV，（Comma-Separated Values）其文件以纯文本形式存储表格数据。

### 写入

每一行数据是一个列表

**以列表数据写入CSV**

```python
import csv

with open('data.csv', 'w', encoding='utf-8', newline='\n') as f: #打开data.csv文件
    writer = csv.writer(f, delimiter=',')#分隔符为逗号
    writer.writerow(['ID','NAME','AGE']) #writerow()可以写入一行数据
    writer.writerow(['001','Tashi',20])
    writer.writerow(['002', 'Aha', 18])
    writer.writerow(['003', 'Yile', 16])
```
> 说明

- writerow()写入一行数据
- writerows()写入多行数据

**以字典数据写入CSV**

```python
import csv

headers = ["name","age","sex"]
values = [
    {
        "name":"Tony",
        "age":15,
        "sex":"男"
    },
    {
        "name":"Amy",
        "age":18,
        "sex":"女"
    }
]
with open('data.csv','w', encoding='utf-8', newline='\n') as f:
    writer = csv.DictWriter(f,headers)
    writer.writeheader() #写入表头数据
    writer.writerows(values)
```

### 读取

打印cdv的每一行数据

```python
import csv

with open('data.csv', 'r') as f:
    reader = csv.reader(f)
    for row in reader:
        print(row,type(row))
>>> 
['name', 'age', 'sex'] <class 'list'>
['张三', '18', '男'] <class 'list'>
['李斯', '15', '女'] <class 'list'>
['王五', '15', '男'] <class 'list'>
```        

**除标题的数据**

`next()`

```python
import csv

with open('data.csv', 'r') as f:
    reader = csv.reader(f)
    next(reader)
    for row in reader:
        print(row,type(row))

['张三', '18', '男'] <class 'list'>
['李斯', '15', '女'] <class 'list'>
['王五', '15', '男'] <class 'list'>
```

**以字典的方式读取数据**

上面的例子读取出来的都是列表格式，只能以下标的方式来读取特定的字段

使用字典的方式读取字段不容易出错。

`DictReader()`

```python
import csv

with open('data.csv', 'r') as f:
    reader = csv.DictReader(f) #创建reader对象 不会包含标题行
    for row in reader:
        print(row,row['name'])

OrderedDict([('name', '张三'), ('age', '18'), ('sex', '男')]) 张三
OrderedDict([('name', '李斯'), ('age', '15'), ('sex', '女')]) 李斯
OrderedDict([('name', '王五'), ('age', '15'), ('sex', '男')]) 王五
```


        

