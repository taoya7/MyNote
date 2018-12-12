# Python-文件操作

#### 打开文件

文件模式【读】

```python
# 打开文件
f = open('test.txt', mode='r')

# 操作文件
content = f.read()
print(content)

# 关闭文件
f.close()
```

文件模式【写】

```python
# 打开文件
f = open('test.txt', mode='w')

# 操作文件
f.write('我写了一段文字')

# 关闭文件
f.close()

# 注意 1.写入的新内容，会覆盖旧的内容
#      2.文件不存在，会自动创建一个新文件
```

文件模式【追加】

```python
# 打开文件
f = open('test.txt', mode= 'a')

# 操作文件
f.write('我又写了一段文字')

# 关闭文件
f.close()


# 注意 1. 写入的内容，会新增到文件末尾
#      2. 文件不存在，会自动创建一个新文件
```

读取二进制文件【读】

```python
# 打开文件
f = open('1.jpg', 'rb')
# 2。操作文件 
content = f.read()
print(content)  #输出二进制
# 3。关闭文件 
f.close()
```

读取二进制文件【写】

```python
"""
把一张图片的前半部分 写入到 另一张图片中
"""

# 打开、操作、关闭
fromFile = open('1.jpg','rb')
fromContent = fromFile.read()
fromFile.close()
# 打开、操作、关闭
toFile = open('2.jpg','wb')
content = fromContent[0: len(fromContent)//2]
toFile.write(content)
toFile.close()
```

**tell() 与 seek()**

```python
f = open('test.txt', 'rb+')
print(f.tell())   #查看当前位置
f.seek(-9,2)  # 从末尾指向开头
print(f.tell())  # 0
```

**读取指定数据**

```python
f = open('test.txt', 'r')
content = f.read(4)  # 字节数  下标会后移相应的位数
print(content)
```

```python
f = open('test.txt', 'r')

content = f.readline()  # 读取一行数据
print(content)
content = f.readline()  # 读取一行数据
print(content)
content = f.readline()  # 读取一行数据
print(content)
```

```python

f = open('test.txt', 'r')

# f.readlines()
# 	会自动的将文件按换行符进行处理
# 	将处理好的每一行组成一个列表返回

content = f.readlines()  # 读取一行数据
print(content)

['123456789\n', '12345上山打老虎\n', '54321哈啊哈哈哈']
```

**关闭文件**


`f.close()`

为什么需要关闭文件？

- 打开状态, 会占用系统资源
- 会把缓冲区内容清空到磁盘文件中

`f.flush()`

- 清空缓冲区内容到磁盘文件中

### 常用方法

**创建文件夹**

- os.mkdir('path')  # 创建单个目录
- os.makedirs()    # 创建多级目录

**删除文件夹**

- os.remove('path')
- os.rmdir('path')  #不是空 报错
- os.removedirs("r'path'") # 删除所有文件

**获取当前文件工作路径**

- os.getcwd()

**获取目录列表**

- os.listdir('path')

**改变工作路径**

- os.chdir('path')

**文件重命名**

- os.rename('oldFile','newFile')

**返回path规范化的绝对路径**

- os.path.abspath(path)


**将path分隔成目录 和 文件名二元组返回**

- os.path.split(path)

**获取路径名**

- os.path.dirname()

**获取文件名**

- os.path.basename()

**检测路径是否存在**

- os.path.exists(path)

**获取文件属性**

- os.stat(file)

**修改文件权限与时间戳**

- os.chmod(file)

**终止当前进程**

- os.exit()

**获取文件大小**

- os.path.getsize(fileNameS)

**判断文件是否存在**

- os.path.exists(test_file.txt)

**判断文件夹是否存在**

- os.path.exists(test_dir)

**只检查文件**

- os.path.isfile("test-data")

**判断是否可做读写操作**

- os.access(path, mode)
	- os.F_OK: 检查文件是否存在;

	- os.R_OK: 检查文件是否可读;

	- os.W_OK: 检查文件是否可以写入;

	- os.X_OK: 检查文件是否可以执行



### Demo

- 文件分类

```python
'''
1.遍历所有的文件
2.分解文件的后缀名
3.查看一下，是否存在同名的目录
4.如果不存在这样的目录 -> 直接创建一个这样名称的目录
5.目录存在 -> 移动过去
'''
import os
import shutil
#路径
path = 'Test'
if not os.path.exists(path):
    exit()
os.chdir(path)
file_list = os.listdir('./')
#1.遍历所有的文件
for file_name in file_list:
    index = file_name.rfind('.')        #从右往左查找
    if index == -1: #如果找不到
        continue
    extension = file_name[index+1:] #获取文件后缀
    if not os.path.exists(extension):   #判断 文件是否存在
        os.mkdir(extension)
    shutil.move(file_name,extension)
```