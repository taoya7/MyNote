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

# CSV文件存储

CSV，（Comma-Separated Values）其文件以纯文本形式存储表格数据。

### 写入

```python
import csv

with open('data.csv', 'w') as f: #打开data.csv文件
    writer = csv.writer(f, delimiter=',')#分隔符为逗号
    writer.writerow(['ID','NAME','AGE']) #writerow()可以写入一行数据
    writer.writerow(['001','Tashi',20])
    writer.writerow(['002', 'Aha', 18])
    writer.writerow(['003', 'Yile', 16])
```
> 说明

- writerow()写入一行数据
- writerows()写入多行数据

### 读取

```python
import csv

with open('data.csv', 'r') as f:
    reader = csv.reader(f)
    for row in reader:
        print(row)
```        
        

