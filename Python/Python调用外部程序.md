Python中可以方便地使用os模块运行其他的脚本或者程序，这样就可以在脚本中直接使用其他脚本，或者程序提供的功能，而不必再次编写实现该功能的代码


1. os.system函数
2. ShellExecute()函数

### os.system函数运行其他程序

`os.system(command)`

command 要执行的命令，相当于在Windows的cmd窗口中输入的命令


关闭一个程序
```
taskkill /f /t /im 进程名
```


>  启动Chrome浏览器(添加环境变量)

```python
import os
os.system('Chrome')
```


### ShellExecute()函数

还可以使用win32api模块中的ShellExecute()函数

`ShellExecute(hwnd, op , file , params , dir , bShow )`

- hwnd：父窗口的句柄，如果没有父窗口，则为0。
- op：要进行的操作，为“open”、“print”或者为空。
- file：要运行的程序，或者打开的脚本。
- params：要向程序传递的参数，如果打开的为文件，则为空。
- dir：程序初始化的目录。
- bShow：是否显示窗口。

```python
>>> import win32api
# 打开记事本程序，在后台运行，即显示记事本程序的窗口
>>> win32api.ShellExecute(0, 'open', 'notepad.exe', '','',0)
# 打开记事本程序，在前台运行
>>> win32api.ShellExecute(0, 'open', 'notepad.exe', '','',1)
# 向记事本传递参数，打开python.txt
>>> win32api.ShellExecute(0, 'open', 'notepad.exe', 'python.txt','',1)
# 在默认浏览器中打开http://www.python.org网站
>>> win32api.ShellExecute(0, 'open', 'http://www.python.org', '','',1)
# 在默认的媒体播放器中播放E:\song.wma
>>> win32api.ShellExecute(0, 'open', 'E:\\song.wma', '','',1)
# 运行位于E:\book\code目录中的MessageBox.py脚本
>>> win32api.ShellExecute(0, 'open', 'E:\\book\\code\\MessageBox.py', '','',1)
```

> 打开一个网站

将FileName参数设置为“http:”协议格式，那么该函数将打开默认浏览器并链接 到指定的URL地址

```
win32api.ShellExecute(0, 'open', 'http://www.baidu.com', '','',1)
```

> 发送邮件

将FileName参数设置为“mailto:”协议格式，那么该函数将启动默认邮件客户 程序

```
win32api.ShellExecute(0, 'open', 'mailto:569781231@qq.com', '','',1)
```