# JSON格式

**是什么**

Json(JavaScript Obect Notation)轻量级的数据交换格式

### JSON转化Python对象

`load()`

```python
import json

str = """
[
    {   
        "id":"001",
        "name":"Aha",
        "age":"18"
    },
    {
        "id":"002",
        "name":"Tashi",
        "age":"20"
    }
]
"""
print(type(str))
data = json.loads(str) #可以将JSON文本字符串转换JSON对象
print(data)
print(type(data))

<class str>
[{'id': '001', 'name': 'Aha', 'age': '18'}, {'id': '002', 'name': 'Tashi', 'age': '20'}]
<class list>
```

### 输出JSON

**dumps()**这样即可将Pyhton的列表、字典转成Json数据格式

在Python中只有基本数据类型才能转换成Json格式的字符串

- int
- float
- str
- list
- dict
- tuple
  
```python
import json

data =[
    {   
        "id":"001",
        "name":"Aha",
        "age":"18"
    },
    {
        "id":"002",
        "name":"Tashi",
        "age":"20"
    }
]

str = json.dumps(data)
print(str,type(str))

[{"id": "001", "name": "Aha", "age": "18"}, {"id": "002", "name": "Tashi", "age": "20"}] 
<class str>
```

### 写入JSON格式文件

**dump()**

```python
import json

data =[
    {   
        "id":"001",
        "name":"Aha",
        "age":"18"
    },
    {
        "id":"002",
        "name":"Tashi",
        "age":"20"
    }
]

with open('data.json', 'w', encoding='utf-8') as f:
    f.write(json.dumps(data, indent=4, ensure_ascii=False))
```
- ensure_ascii 是否转化ascii
- indent 格式化json的空格数量

