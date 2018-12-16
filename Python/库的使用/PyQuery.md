#
PyQuery

强大又灵活的网页解析库

> ### 初始化

### 字符串初始化

```python

html = """
<div class="box">
<ul>
<li>1</li>
<li>2</li>
<li>3</li>
<li>4</li>
</ul>
</div>

<div class="container">
<p>这是一段文字</p>
<span>很酷！</span>
</div>
"""
from pyquery import PyQuery as pq
doc = pq(html)
print(doc('li'))

#打印所有的li标签
```

**注释**

首先引入PyQuery对象 取名pq。将HTML字符串当作参数传递给PyQuery类，就完成了初始化

### URL初始化

```python
from pyquery import PyQuery as pq
doc = pq(url="http://www.itaolaity.com")
print(doc('title'))

>>> <title>Tashi-丛林深处有书和黄金屋</title>

```

### 文件初始化

```python
from pyquery import PyQuery as pq
doc = pq(filename='Demo.html')
print(doc('title'))
```

## CSS选择器

**基本用法**

```python
html = """
<div class="box">
<ul>
<li>1</li>
<li>2</li>
<li>3</li>
<li>4</li>
</ul>
</div>

<div class="container">
<p>这是一段文字</p>
<span>很酷！</span>
</div>
"""
from pyquery import PyQuery as pq
doc = pq(html)
print(doc(".box li")) # 选取CLSS为box的节点 然后再选取内部的li节点
```

### 查找结点

* **子节点**

> find\(\) 查找范围是节点的所有子孙节点
>
> children\(\) 方法查找子节点

```python
html = """
<div class="box">
<div class="init1"></div>
<div class="init2">
<p>Tashi</p>
<h1>丛林深处有书和黄金屋</h1>
</div>

<div class="init3"></div>
</div>

"""

from pyquery import PyQuery as pq

doc = pq(html) # 初始化操作

# print(doc('.box'))
# print(type(doc('.box'))) # <class 'pyquery.pyquery.PyQuery'>
f = doc('.box')
s = f.find('.init2')
print(s)
## 注释 先找到父节点box 然后通过父节点的find()方法 找子节点为class="init2"的
```

```python
html = """

<div class="box">

<div class="init1"></div>



<div class="init2">

<p>Tashi</p>

<h1 id="sign">丛林深处有书和黄金屋</h1>

</div>



<div class="init3"></div>

</div>

"""

from pyquery import PyQuery as pq

doc = pq(html)

f = doc('.box')

s = f.find('#sign') # 子孙节点都可以找到

s = f.children('#sign') # 找不到 只可以寻找子节点

print(s)
```

* **父节点**

> parent\(\) 父节点
>
> parents\(\) 祖先节点

* **兄弟节点**

> siblings\(\)

## 遍历

```python

html = """
<div class="box">
<ul class="nav1">
<li><a href="#">百度</a></li>
<li><a href="#">淘宝</a></li>
<li><a href="#">丘比特</a></li>
<li><a href="#">Python</a></li>
<li><a href="#">Tashi</a></li>
</ul>
<ul class="nav2">
<ol>
<li>Gulu</li>
<li>Heng</li>
</ol>
</ul>
</div>
"""

from pyquery import PyQuery as pq
doc = pq(html)
lis = doc('li')
print(type(lis))
print(lis)
lis = doc('li').items()
print(type(lis))
for li in lis:
print(li)

```

## 获取信息

#### 获取属性

.attr\('属性值'\)

#### 获取文本

.text\(\)

## 节点操作

#### 增加节点和移除节点

* addClass\(\)

* removeClass\(\)


```python
tml = """
<div class="box1"></div>
<div class="box2"></div>
"""

from pyquery import PyQuery as pq
doc = pq(html) # 初始化
box = doc('div')
print(box)
box.addClass('haha') ###
print(box)
box.removeClass('box1') ###
print(box)
```

```python
html = """



<section></section>

"""

from pyquery import PyQuery as pq
doc = pq(html) # 初始化
art = doc('section')
print(art) # 寻找
art.attr('class', 'artO')
print(art) # 增加一个class
art.text('这是一个文章')
print(art)
art.html("<span>真酷</span>")
print(art)
```

## 伪类选择器

```python

html = """
<div class="box">
<ul>
<li>1</li>
<li>2</li>
<li>3</li>
<li>4</li>
<li>5</li>
</ul>
</div>
"""
from pyquery import PyQuery as pq
doc = pq(html) #初始化
li = doc('li:first-child')
print(li)
li = doc('li:last-child')
print(li)
li = doc('li:nth-child(3)')
print(li)
```
