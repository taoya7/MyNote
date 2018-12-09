# JSON格式

- 转换JSON
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

- 输出JSON
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

- 写入JSON格式文件
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
    f.write(json.dumps(data, indent=2, ensure_ascii=False))
```