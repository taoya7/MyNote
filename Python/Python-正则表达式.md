# Python中的正则表达式

**是什么**

正则表达式(Regular Expression)目的是为了字符串模式匹配，从而实现搜索和替换功能。所以它是一种用来描述规则的表达式。

## 字符

既然字符串是为了字符串的模式匹配

所以组成

1. **字符**
2. **元字符**

字符就是数字、英文字母。而元字符就是特殊字符，可以表示特殊语义的字符。

我们所要做的就是利用元字符构造强大的表达式

### 单个字符-基本匹配

比如在一个字符串中匹配固定的字符

```python
#在apple中匹配ple
print(re.findall("ple","apple"))

> ['ple']
```

转义字符

|特殊字符	    |正则表达式    |	 记忆方式  |
| ------------ | ------------ | ------------ |
|换行符	|\n	|new line|
|换页符	|\f	|form feed|
|回车符	|\r	|return|
|空白符	|\s	|space|
|制表符	|\t	|tab|
|垂直制表符	| \v|	vertical tab|
|回退符|	[\b]	|backspace,之所以使用[]符号是避免和\b重复|


### 多个字符

单个字符的映射关系是一对一的，即正则表达式的被用来筛选匹配的字符只有一个。而这显然是不够的，只要引入集合区间和通配符的方式就可以实现一对多的匹配了

```python
#匹配1 2 3三个字符
print(re.findall("[123]","[123456123]"))

 > ['1', '2', '3', '1', '2', '3']
```

**-**表示区间范围

```python
print(re.findall("[1-3]","[123456]"))

> ['1', '2', '3']
```

利用-表示区间范围，所以[0-9]就能匹配所有数字 [a-z] 就能匹配所有小写字母

|匹配区间	|正则表达式|
| ------------ | ------------ |
|除了换行符之外的任何字符|	.	|
|单个数字, [0-9]|	\d	|
|除了[0-9]	|\D|
|包括下划线在内的单个字符，[A-Za-z0-9_]	|\w|
|非单字字符	|\W	|
|匹配空白字符,包括空格、制表符、换页符和换行符	|\s	|
|匹配非空白字符	|\S|

## 循环与重复

如何同时匹配多个字符。要实现多个字符的匹配我们只要多次循环，重复使用我们的之前的正则规则就可以了

**分类**
- 0次
- 1次
- 多次
- 执行次

**？**代表匹配一个或0个

**\***代表匹配0个或无数个

**+** 表示匹配1次或多次

**{ }** 表示匹配指定次数
- {n} n次
- {min,max} 介于
- {min,}至少min次
- {0,max}至多max次


```python
import re
#0~9的数字匹配三个
str = "a123456789"
print(re.search('[0-9]{3}',str)) #返回对象

<re.Match object; span=(1, 4), match='123'>

# 返回数字
str = "abc1s5df92f"
re.findall("[0-9]",str)

['1', '5', '9', '2']

# 从头匹配直到看到2
re.search('.*2','1a2bcc')
> 1a2
```

## 边界

在长文本字符串查找过程中，我们常常需要限制查询的位置。比如我只想在单词的开头结尾查找

### 单词边界

单词是构成句子和文章的基本单位，一个常见的使用场景是把文章或句子中的特定单词找出来。

```python
re.findall("cat","more cat cat catkk")
> ['cat', 'cat', 'cat']
```

**/b**匹配单词边界

它匹配一个单词的边界

```python

re.findall( r'\bbc\b' , "abc bc abbbc bcd" )
> ['bc']


re.findall( r'bc\b' , "abc bc abbbc bcd" )
> ['bc', 'bc', 'bc']
```

**/B**匹配非边界

```python
#匹配包含bc但不以bc开头的 和以bc结尾的  
re.findall( r'\Bbc\b' , "abc bc abbbc bcd" )
> ['bc', 'bc']
```


### 字符串边界

**^** 表示字符串的开头

**$** 表示字符串的结尾

```python
#匹配以he开头的
re.findall("^he","he is hello ")

> ['he']
```

## 分组

> 分组命名

格式

`?P<组名>`

调用

`.groupdict()`



```python
import re

data = "tashi123ale"
res = re.search('([a-z]*)([0-9]*)([a-z]*)',data)
print(res.groups())
print(res.group(1))

#身份证验证
data = "140830199810040017"
res = re.search('(?P<province>\d{3})(?P<city>\d{3})(?P<year>\d{4})',data)
print(res.groups())
print(res.groupdict()) #转变字典


('tashi', '123', 'ale')
tashi
('140', '830', '1998')
{'province': '140', 'city': '830', 'year': '1998'}
```

## 匹配分割

```python
import re

data = "小红,小明,小花"
res = re.split(',',data)
print(res)

['小红', '小明', '小花']
```

## 匹配替换

参数1 匹配规则

参数2 要替换的字符

参数3 替换的数据

参数4 替换几个

```python
import re

# help(re)
data = "小红,小明,小花"
res = re.sub(',','__',data,count=1)
print(res)

小红__小明,小花
```



## 总结

|正则表达式	|代表的匹配字符|
| ------------ | ------------ |
|.      |   代表任意字符 |
|    \  |         逻辑或操作符         |
|   [ ]    |     匹配内部的任一字符或子表达式          |
|    [^]   |        对字符集和取非       |
|    -   |          定义一个区间     |
|     *  |     匹配前面的字符或者子表达式0次或多次          |
|    *?   |      惰性匹配上一个         |
|    +   |     匹配前一个字符或子表达式一次或多次          |
|        ?   |     匹配前一个字符或子表达式0次或1次重复          |
|       {n}    |          匹配前一个字符或子表达式     |
|       ^	    |   匹配字符串的开头            |
|      \A     |     匹配字符串开头          |
|      $     |      匹配字符串结束         |
|   \d  |   单个数字0-9|
|   \D  |   除了0-9|
|   \w  |   单个字符a-z A-Z 0-9|
|   \W  |   除了[A-Za-z0-9]|
|   \s  |   匹配空白字符,包括空格、制表符、换页符和换行符|
|   \S  |   匹配非空白字符	|

>  **Python**API

- `re.match()` 从头开始匹配,找到返回

- `re.search()` 全局匹配包含

  在指定字符串查找，返回第一个匹配内容。找到了返回match对象，否则返回None

- `re.findall()` 搜索字符串,以列表形式返回全部能匹配的子串

- `re.split() `以匹配到的字符当作列表分隔符

- `re.sub()` 匹配字符并替换

  `sub(pattern, repl, string, count=0, flags=0)`

- `re.fullmatch()`全部匹配

**标识符**

```
re.I          使匹配对大小写不敏感
re.L          使本地化识别匹配
re.M          多行匹配，影响^和$
re.U          根据Unicode字符集解析字符。这个标志影响\w  \W  \b \B
re.X          更容易理解
re.S          使匹配包括换行在内的所有字符
```


## Demo

```python
# 请写出一个正则来处理数字千分位，如12345替换为12,345
import re
str = "12345"
re.findall("(\d{0,})(\d{3})",str)
```

1. 验证手机号码

规则以1开头 第二位34578中的一位 ，一共11位

```python
import re

text = "18735673472"
res = re.match('1[34578]\d{9}',text) # 1 [34578] \d{9}
print(res)
```

2. 验证邮箱

```python
import re

email = "569781231@qq.com"
res = re.match("\w+@[a-z 0-9]+\.[a-z]+", email)
print(res)
```

3. 匹配0-100之间的数字

```python
import re

text = "sda123ababatony"

res = re.search("[1-9]\d?$|100", text)
print(res)
> 12
```



---

# JavaScript的正则表达式

**使用方式1**

```js
str.match()
```

**使用方式2**

JavaScript RegExp 对象

通过new来定义RegExp对象

RegExp对象有三个方法

- `test()` 检索字符串中的指定值。返回值是 true 或 false
- `exec()`检索字符串中的指定值。返回值是被找到的值。如果没有发现匹配，则返回 null
- `compile()`用于改变 RegExp

---

- 验证字母+数字

```javascript
var mes = "abc569"

var check = new RegExp("[a-z]")

console.log(check.test(mes)) //True 表示匹配到
```





