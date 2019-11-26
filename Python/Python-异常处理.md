# Python-异常处理

> ## 异常与错误

#### 错误

- 错误
	- 语法错误
	- 逻辑错误

#### 异常

多指程序在执行的过程中, 出现的未知错误; 语法和逻辑都是正确的; 可以通过其他代码进行处理修复

> ## 常见的系统异常

- 除零异常（ZeroDivisionError）
```python
1/0
```

- 名称异常（NameError）

- 类型异常（TypeError）

- 索引异常（IndexError）

- 键异常（KeyError）
```python
dic = {"name": "sz", "age": 18}
dic["add"]
```

- 值异常(ValueError)

- 属性异常(AttributeError)
```python
name = "sz"
print(name.xx)
```

- 迭代器异常(StopIteration)
```python
it = iter([1, 2])
print(next(it))
print(next(it))
print(next(it))
```

- 系统异常类继承树
	- BaseException 所有内建的异常的基类
		- SystemExit (由sys.exit()函数引发。当它不处理时，Python 解释器退出)
		- KeyboardInterrupt (当用户点击中断键 （通常ctrl + C） 时引发)
		- GeneratorExit （当调用一种generator的close()方法时引发。）
		- Exception （所有内置的、 非系统退出异常是从该类派生的。应该从该类派生所有用户定义的异常。）

## 解决异常

预防和解决。

### 方案一
```python
try:
	#可能会出现异常的代码
except '你要捕获的异常类别' as '接受异常的形参':
	#对于这个异常的处理
else:
	#对于没有出现异常时的处理
finally:
	#不管有没有异常，都会执行的代码
```

**注意**

- try语句没有捕获到异常，先执行try代码段后，在执行else，最后执行finally

- 如果try捕获异常，首先执行except处理错误，然后执行finally

- 如果异常名称不确定, 而又想捕捉, 可以直接写Exception


### 方案二

适用于执行某一段代码A之前, 进行预处理, 执行代码A结束后, 进行清理操作。不管出现了什么异常, 最终都要执行一些清理操作

```python
with context_expression [as e]:
	with-body
```

**执行流程**

- context_expression 要返回一个上下文管理器对象

- 调用‘上下文管理器的__enter__方法’ 如果写了 as target语句，则吧__enter__方法的返回值赋值给target

- 执行语句体

- 执行‘上下文管理器的__exit__方法’

**Demo**
```python
# 1
f = open('xxx.txt')
f.radnline()
f.close()

# 2
with open('xxx.txt', 'r') as f:
	f.read()
```

### 方案三


自定义上下文管理器

```python
class Test:
    def __enter__(self):
        print('Enter')
    def __exit__(self, exc_type, exc_val, exc_tb):
        print(self, exc_type, exc_val, exc_tb)
        print('Exit')

with Test():
    print('Body')

Enter
Body
<__main__.Test object at 0x0000017FBD1D92E8> None None None
Exit
```

```python
class Test:
    def __enter__(self):
        print('Enter')
        return 'Tao'
    def __exit__(self, exc_type, exc_val, exc_tb):
        print(self, exc_type, exc_val, exc_tb)
        print('Exit')

with Test() as f:
    print('Body',f)


Enter
Body Tao
<__main__.Test object at 0x000001C4E44996D8> None None None
Exit
```

```python
class Test:
    def __enter__(self):
        return 'Tao'
    def __exit__(self, exc_type, exc_val, exc_tb):
        # print(self, exc_type, exc_val, exc_tb)
        import traceback
        print(traceback.extract_tb(exc_tb))
with Test() as f:
    1/0

[<FrameSummary file E:/Tashi/Desktop/Code/Python/test.py, line 9 in <module>>]
  File "E:/Tashi/Desktop/Code/Python/test.py", line 9, in <module>
    1/0
ZeroDivisionError: division by zero
```


## 手动抛出异常



```python
def set_age(age):
    if age<=0 or age>200:
        raise ValueError('取值错误')
    else:
        print("成年")
try:
    set_age(-20)
except ValueError as e:
    print(e)

取值错误
```

## 自定义异常

```python
class LessEero(Exception):
    def __init__(self,msg):
        self.msg = msg

def set_age(age):
    if age<=0 or age>200:
        raise LessEero('小于0')
    else:
        print("成年")

try:
    set_age(-5)
except LessEero as e:
    print(e)

```