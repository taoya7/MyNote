# Scrapy Shell

**开始**

```shell
scrapy     shell [链接]
```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-30-22-13-47-image.png)

## Response对象

1. `meta` 从其他请求传过来的meta属性

2. `encoding` 返回当前字符串编码和解码的格式

3. `text` 将返回的数据作为`unicode`字符串返回

4. `body` 将返回的数据作为`bytes` 字符串返回

5. `xpath` xpath选择器

6. `css` css选择器

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-30-23-19-17-image.png)

## Request对象

1. `url` 请求的url

2. `callback` 在下载器下载完相应的数据后执行的回调函数

3. `method` 请求的方法。 

4. `headers` 请求头。
   
   可以在`settings.py`中指定

5. `meta` 

6. `encoding` 编码 默认为`utf-8`

7. `dont_filter` 表示不由调度器过滤。

8. `errback` 在发生错误的时候执行的函数

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-30-23-13-10-image.png)
