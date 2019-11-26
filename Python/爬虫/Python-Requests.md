> Requests库

### Get请求

```python
import response

response = requests.get('http://www.baidu.com')
print(response) #<Response [200]>

# 打印状态码
print(response.status_code)
# 打印请求url
print(response.url)
# 打印头信息
print(response.headers)

# 打印cookie信息
print(response.cookies)

#以文本形式打印网页源码
print(response.text)

# 以字节流形式打印
print(response.content)  # 通常用于二进制 图片 音乐

# 编码
print(response.encoding)  # ISO-8859-1
```

### **带参数的请求方式**
```python
import requests

# 将数据保存在一个字典中
data = {
  'name' : '张三',
  'age' :19
}

response = requests.get('http://www.baidu.com', params=data)
print(response.url)

> http://www.baidu.com/?name=张三&age=19
```

**网页的返回类型实际是str类型的却是json格式,所以添加json()即可转换**
```python
import requests

response = requests.get('http://www.baidu.com').json()
print(response)
```

**添加headers**

```python
import requests

headers = {
  'User-Agent':'Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Mobile Safari/537.36'
}
response = requests.get('https://www.zhihu.com/', headers=headers)
print(response.text)
```

**抓取二进制文件**
```python
import requests

r = requests.get('https://www.baidu.com/img/bd_logo1.png?qua=high')


with open('logo.jpg','wb') as f:
    f.write(r.content)


# 这样就会抓取下百度的logo
```

## Post请求

```python
import requests

data = {
    'name':'tom',
    'age':'22'
}

response = requests.post('http://httpbin.org/post', data=data)

print(response.text)
```

**编码问题**

response有text与content指响应内容

## Ip代理

```python
import requests

headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36'
}
proxy = {
        'http': '120.25.253.234:812',
        'https' '163.125.222.244:8123'
    }
response = requests.get(url=url,headers=headers,proxies=proxy)
```

## Cookie

```python

import requests


response = requests.get('http://www.baidu.com')

print(response.cookies)  # <RequestsCookieJar[<Cookie BDORZ=27315 for .baidu.com/>]>

print(type(response.cookies))  # <class 'requests.cookies.RequestsCookieJar'>


run_cookie = response.cookies.get_dict()   # kookie转换
print(type(run_cookie))  # <class 'dict'>

print(run_cookie)  # {'BDORZ': '27315'}
```

## 文件上传

```python
import requests

files = {'file', open('logo.jpg','rb')}

r = requests.post('http://www.baidu.com', files=files)
print(r.text)
```

## 会话维持

```python
import requests

s = requests.Session()
s.get('http://www.baidu.com')
# 这样就会保持会话维持
```

## 超时设置

```python
import requests

response = requests.get('http://www.baidu.com', timeout=1)
print(response.text)
```

