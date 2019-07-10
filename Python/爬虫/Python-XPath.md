# Xpath  XML Path Language

`Xml Parh Language`

> XPath常用选择节点

```
nodename          选取此节点的所有子节点
/                 从当前节点选取直接子节点
//                从当前节点选取子孙节点
.                 选取当前节点
..                选取当前节点的父节点
@                 选取属性
```


> 体验

```python
import requests
from lxml import etree



url = "http://www.itaolaity.com"
response = requests.get(url)
text = response.text

html = etree.HTML(text) #HTML类来初始化文本
res = etree.tostring(html)
# print(res.decode('utf-8')) #转str类型
```

## 所有节点

**注意**
- 星号 表示所有节点
- 返回形式是一个列表，每个元素是Element类型 后面跟了节点的名称

```python
from lxml import etree

html = etree.parse('Test.html', etree.HTMLParser())
result = html.xpath('//*')



# 返回
<Element html at 0x27b2927eac8>
<Element head at 0x27b2927eb48>
<Element title at 0x27b292a3cc8>
<Element body at 0x27b292a3d08>
<Element box at 0x27b292a3d48>
<Element ul at 0x27b292a3d88>
<Element li at 0x27b292a3dc8>
<Element a at 0x27b292a3e08>
<Element li at 0x27b292a3e48>
<Element li at 0x27b292a3e88>
<Element li at 0x27b292a3ec8>
<Element li at 0x27b292a3f08>
```
```python
from lxml import etree

html = etree.parse('Test.html', etree.HTMLParser())
result = html.xpath('//li')

print(result)  # 会返回所有的li节点

<Element li at 0x2146ff0cf08>
<Element li at 0x2146ff3e0c8>
<Element li at 0x2146ff3e108>
<Element li at 0x2146ff3e148>
<Element li at 0x2146ff3e188>
```

## 子节点

**注意**
/ 或 // 可以查找子节点或子孙节点
```html
<ul class="You can see me">
    <li>1</li>
        <a href="#">Tashi</a>
    <li>2</li>
    <li>3</li>
    <li>4</li>
    <li>5</li>
</ul>
```
```python
# 获取ul节点下的li节点
result = html.xpath('//ul/li')
print(result)
```

## 父节点

```html
<ul class="You can see me">
    <li>1</li>
        <a href="#">Tashi</a>
    <li>2</li>
    <li>3</li>
    <li>4</li>
    <li>5</li>
</ul>
```
```python

result = html.xpath('//a[@href="#"]/../@class')
print(result)  # 选中href=# 的a节点， 然后获取父节点 再获取其class的属性

> You can see me
```
```python
result = html.xpath('//a[@href="#"]/parent::*/@class')
print(result)
>>> You can see me
```

## 属性匹配
```html
<ul>
    <li>1</li>
    <li class="item-3">2</li>
</ul>
```
```python
result = html.xpath('//li[@class="item-3"]')
```

## 文本获取

```html
<ul>
    <li>1</li>
    <li class="item-3">2</li>
</ul>
```
```python
result = html.xpath('//li[@class="item-3"]/text()')

>>> 2
```