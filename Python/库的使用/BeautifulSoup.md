# BeautifulSoup

“BeautifulSoup是一个可以从HTML或XML文件中提取数据的Python库，它能够通过你喜欢的转换器实现惯用的文档导航，查找，修改文档的方式”


> ## 节点选择器

**选取属性**
```html
<div class="box">
    <ul class="box-ul">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
    </ul>
</div>
```
```python
from bs4 import  BeautifulSoup
# 将HTML作为第一个参数传给BoeautifulSoup对象，第二个参数为解析器的类型
soup = BeautifulSoup(html,'lxml')
print(soup.ul) #则会返回ul下的内容
print(soup.head)
```
**提取信息**

获取标签名称 **`name`**
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.li) #则会返回ul下的内容
print(soup.li.name)

>>> <li>1</li>
>>> li
```
获取属性 **`attrs`**
```python
soup = BeautifulSoup(html,'lxml')
print(soup.div.attrs)  # {'class': ['box']}
```
获取内容   **text**
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.ul.text) #ul 下的文字
```


**子节点和子孙节点**

子节点 contents/children
```html  
<div class="box">
    <center><h1>BOOT</h1></center>
    <P>这是一段文字</P>
    <a href="#">百度</a>
    <span>标记</span>
</div>
```
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.div.contents)  # 获取div下的子节点


>>> ['\n', <center><h1>BOOT</h1></center>, '\n', <P>这是一段文字</P>, '\n', <a href="#">百度</a>, '\n', <span>标记</span>, '\n']
```
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.div.children)
```
子孙节点 descendants
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.div.descendants)
```

**父节点和祖先节点**

- 父节点parent

- 父节点parents

**兄弟节点**
- next_sibling # 下一个兄弟节点

- next_siblings # 所有后面的兄弟节点

- previous_sibling  # 上一个兄弟节点

- previous_siblings

> ## 方法选择器

- **find_all()**
    - 返回一个list 数组类型


name根据节点名
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.find_all(name="ul"))  # 根据节点名查找
```

attrs根据属性来查询
```python  
soup = BeautifulSoup(html,'lxml')
print(soup.find_all(attrs={'class':'box'}))
```

text匹配节点的文本
```python   

soup = BeautifulSoup(html,'lxml')
print(soup.find_all(text=re.compile('like')))
```

- **find()** 返回单个元素

- **find_parents()**

- **find_parent()**

- **find_next_siblings()**
- **find_next_siling()**



> ## CSS 选择器

- **select()**
```python  
print(soup.select('ul','li')) # 返回ul节点下的li
```

获取属性
```python  
for ul in soup.select('ul'):
  print(ul['id'])
  print(ul.attrs['id'])
```

获取文本 get_text()
```python   
em = soup.select('li')
print(em.get_text())
```
