# Selenium

自动化测试工具，支持多种浏览器，爬虫中主要用来解决JavaScript渲染的问题

> ### 基本使用

```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait

browser = webdriver.Chrome()
try:
    browser.get('http://www.baidu.com')
    input = browser.find_element_by_id('kw') #寻找元素
    input.send_keys('Python')
    input.send_keys(Keys.ENTER)
    wait = WebDriverWait(browser,10)
    wait.until(EC.presence_of_element_located((By.ID,'content_left')))
    print(browser.current_url)#打印访问的URL
    print(browser.get_cookies()) #打印cookie信息
finally:
    browser.close()
```

> ### 声明浏览器对象

```python
from selenium import WebDriverWait

driver = webdriver.Chrome()

driver = webdriver.FireFox()

driver = webdriver.Edge()

driver = webdriver.PhantomJS()

driver = webdriver.Safari()
```

> ### 访问页面

```python
from selenium import webdriver
driver = webdriver.Chrome()
driver.get('http://www.taobao.com')
print(driver.page_source)
driver.close()
```

- get()方法可以访问页面


> ### 查找元素

**单个元素**

```python
driver.find_element_by_id('kw')
driver.find_element(By.ID,'KW')
```

#### 常用的查找元素的方法

* find\_element\_by\_name
* find\_element\_by\_id
* find\_element\_by\_xpath
* find\_element\_by\_link\_text
* find\_element\_by\_partial\_link\_text
* find\_element\_by\_tag\_name
* find\_element\_by\_class\_name
* find\_element\_by\_css\_selector \#通过CSS选择器

**多个元素**

* find\_elements\_by\_name
* find\_elements\_by\_xpath


#### 常用的方法

* driver.get\(\) 方法会打开请求的URL
* driver.page\_source 打印网站的源码
* driver.click\(\) 会点击按钮
* driver.implicitly\_wait\(30\) 智能等待30s
* driver.quit\(\)   全部关闭
* driver.close\(\)  当前窗口关闭
* refresh\(\) 刷新当前页面

> ### 元素交互

* send\_keys\(str\) \#输入内容
* click\(\) \#点击
* clear\(\) \#清除文本框

> ### 获取ID，位置，标签名

打开百度首页 然后找到搜索框

```python
from selenium import webdriver

browser = webdriver.Chrome()
url = 'https://www.baidu.com'
browser.get(url)
#找到输入框的元素
input = browser.find_element_by_id('kw')

print(input.id)
print(input.location)
 print(input.tag_name)
print(input.size)


0.6769866408286407-1

{'x': 206, 'y': 231}

input

{'height': 22, 'width': 500}
```

> ### 获取文本值

打印知乎”提问“按钮的文本

```python
from selenium import webdriver

browser = webdriver.Chrome()

url = 'https://www.zhihu.com/explore'

browser.get(url)

input = browser.find_element_by_class_name('zu-top-add-question')

print(input.text)
```

* text\(\) 获取文本值

> ### 获取元素的属性

```python
from selenium import webdriver

browser = webdriver.Chrome()

url = 'https://www.zhihu.com/explore'

browser.get(url)

logo = browser.find_element_by_id('zh-top-link-logo')

print(logo)

print(logo.get_attribute('class'))
```

* get\_attribute\(\)获取属性 

> ### 执行JavaScript

用js打开一个网站

```python
win = "window.open('http://www.baidu.com')"

driver.execute_script(win)
```

下拉进度条

```python
from selenium import WebDriver

driver = webdriver.Chrome()
driver.get('http://www.zhihu.com/explore')
driver.execute_script('window.scrollto(0,document.body.scrollHeight)')
driver.execute_script('alert("Boon!")')
```



> ### 窗口之间的切换

```python
handles = self.driver.window_handles
self.driver.switch_to.window(handles[1])
```

* 获取当前窗口句柄集合 browser.window\_handles

> ### 键盘操作

* 导入 from selenium.webdriver.common.keys import Keys

* 组合键盘

  * send\_keys\(Keys.CONTROL,'a'\) \#全选（Ctrl+A）

  * send\_keys\(Keys.CONTROL,'c'\) \#复制（Ctrl+C）

  * send\_keys\(Keys.CONTROL,'x'\) \#剪切（Ctrl+X）

  * send\_keys\(Keys.CONTROL,'v'\) \#粘贴（Ctrl+V）

* 常用键

  * 回车键 Keys.ENTER

  * 删除键 Keys.BACK\_SPACE

  * 空格键 Keys.SPACE

  * 制表键 Keys.TAB

  * 回退键 Keys.ESCAPE

  * 刷新键 Keys.F5

> ### 等待

**隐式等待**

* driver.implicitly\_wait\(10\)

**显式等待**

```
from selenium import webdriver

from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC

from selenium.webdriver.support.wait import WebDriverWait
```

* title\_is 标题是否是某内容
* title\_contains 标题包含某内容
* presence\_of\_element\_located 元素加载出，传入定位元祖 如（By.ID,'p'）
* visibility\_of\_element\_located 元素可见，传入定位元组
* text\_to\_be\_present\_in\_element 某个元素文本包含某文字
* text\_to\_be\_present\_in\_element\_value 某个元素值包含某文字

> ### 前进后退

* driver.back\(\)

* driver.forward\(\)

> ### Cookies

* get\_cookies\(\) \#获取cookies

* add\_cookie\(\) \#增加cookie

* delete\_all\_cookies\(\) \#删除所有cookies

> ### 截图

桌面保存一张百度首页的截图，图片后缀是png。注意路径是要两个\

* save\_screenshot\(\)  截取整个窗口

* save_screenshot\_as\_file\(\)_

* screenshot\(\)  元素截图

```
import time
from selenium import webdriver

driver = webdriver.Chrome()
driver.maximize_window() #最大化
driver.implicitly_wait(6)
driver.get("http://www.itaolaity.com")
time.sleep(1)

driver.get_screenshot_as_file("C:\\Users\\Tahi\\Desktop\\baidu.png") #截图并保存
driver.quit()
```

注意：只能对当前窗口截屏



> ### 异常处理

```python
from selenium import webdriver
from selenium.common.exceptions import TimeoutException, NoSuchElementException

driver=webdriver.Chrome()

try:
	driver.get('http://www.itaolaity.com')
except TimeoutException:
	print('Time.Out')
try:
	driver.find_element_by_id('Hello')
except NoSuchElementException:
	print('No Element')
finally:
	driver.close()
```


> ### 代理

```python

from selenium import WebDriver
proxy = '127.0.0.1:6666'
chrome_options = webdriver.ChromeOptions()
Chrome_options.add_argument('--proxy-server=http://'+proxy)
driver = WebDriver.Chrome(chrome_options=chrome_options)

driver.get('http://www.baidu.com')
```