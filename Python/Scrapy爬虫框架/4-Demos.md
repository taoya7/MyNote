## 宝马之家

```shell
// 新建项目
scrapy startproject card

cd card

scrapy genspider giulia car.autohome.com.cn
```

配置设置

```python
ITEM_PIPELINES = {
   'card.pipelines.CardPipeline': 300,
}


DEFAULT_REQUEST_HEADERS = {
  'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
  'Accept-Language': 'en',
'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36'
}

ROBOTSTXT_OBEY = False
```

`items.py`

```python
import scrapy


class CardItem(scrapy.Item):
    title = scrapy.Field()
    urls = scrapy.Field()
```

`giulia.py`

```python
# -*- coding: utf-8 -*-
import scrapy
from ..items import CardItem

class GiuliaSpider(scrapy.Spider):
    name = 'giulia'
    allowed_domains = ['car.autohome.com.cn']
    start_urls = ['https://car.autohome.com.cn/pic/series/3825.html']

    def parse(self, response):
        uiboxs = response.xpath("//div[@class='uibox']")[1:]
        for uibox in uiboxs:
            title = uibox.xpath(".//div[@class='uibox-title']/a/text()").get()
            urls = uibox.xpath(".//ul/li/a/img/@src").getall()
            urls = list(map(lambda url:response.urljoin(url), urls))
            item = CardItem(title=title, urls=urls)
            yield item
```

`pipelines.py`

```python
import scrapy
from ..items import CardItem

class GiuliaSpider(scrapy.Spider):
    name = 'giulia'
    allowed_domains = ['car.autohome.com.cn']
    start_urls = ['https://car.autohome.com.cn/pic/series/3825.html']

    def parse(self, response):
        uiboxs = response.xpath("//div[@class='uibox']")[1:]
        for uibox in uiboxs:
            title = uibox.xpath(".//div[@class='uibox-title']/a/text()").get()
            urls = uibox.xpath(".//ul/li/a/img/@src").getall()
            urls = list(map(lambda url:response.urljoin(url), urls))
            item = CardItem(title=title, urls=urls)
            yield item
```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-31-15-21-42-image.png)



**选择Scrapy内置下载文件的方法**



优点：

1. 避免重新下载最近已经下载过的文件

2. 可以方便的指定文件存储的路径

3. 可以将下载的图片转换成通用的格式

4. 可以方便的生成缩略图

5. 可以监测图片的宽度和高度

6. 异步下载，速度快



Images Pipeline 流程：

1. 定义好一个Item， 然后在Item定义两个属性。
   
   - `image_urls`
   
   - `images`

2. 当文件下载完成后，会把文件下载的相关信息存储到item的images属性中。比如下载路径，下载的url

3. 在配置文件`settings.py`配置`IMAGES_STORE`, 这个配置是用来设置图片下载下来的路径

4. 启动`pipeline`  在 `ITEM_PIPELINES` 中设置`scrapy.pipelines.images.ImagesPipeline:1`

修改`items.py`

```python
import scrapy


class CardItem(scrapy.Item):
    title = scrapy.Field()
    image_urls = scrapy.Field()
    images = scrapy.Field()
```

修改`settings.py`

```python
# 1
ITEM_PIPELINES = {
   # 'card.pipelines.CardPipeline': 300,
    'scrapy.pipelines.images.ImagesPipeline':1
}

# 2 添加以下代码
import os
IMAGES_STORE = os.path.join(os.path.dirname(os.path.dirname(__file__)), "images")
```



这样下载的图片不会根据分类来区分图片 而是将图片全部下载到一个文件夹里面了。



**自定义图片下载路径**



`pipelines.py`

```python
from scrapy.pipelines.images import ImagesPipeline
from card import settings // 导入设置选项

class GiuliaDownImages(ImagesPipeline):
    def get_media_requests(self, item, info):
        request_objs = super(GiuliaDownImages, self).get_media_requests(item, info)
        for request_obj in request_objs:
            request_obj.item = item
        return request_objs
    def file_path(self, request, response=None, info=None):
        path = super(GiuliaDownImages, self).file_path(request, response, info)
        title = request.item.get("title")
        images_store = settings.IMAGES_STORE
        title_path = os.path.join(images_store, title)
        if not os.path.exists(title_path):
            os.mkdir(title_path)

        image_name = path.replace("full/", "")
        image_path = os.path.join(title_path, image_name)
        return image_path
```
