### 安装CouchDB

[下载地址](https://couchdb.apache.org/#download)

用 Web Scraper 爬取下来的数据是不按照顺序排序的，其实也有解决的办法，去安装 CouchDB 然后设置一下就可以了，从此 Web Scraper 爬取下来的数据都是有序的

安装完毕打开 <http://127.0.0.1:5984/_utils/>



> 设置爬虫存储

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-07-10-22-49-image.png)

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-07-10-24-04-image.png)

### WebScraper控制翻页

**范围指示器**

```shell
http://example.com/page/1
http://example.com/page/2
http://example.com/page/3
- http://example.com/page/[1-3]
- http://example.com/page/[0-100:25]  // 也可以设置步长
```

### 导入与导出

如何使用别人的爬虫和如何导出自己的让别人使用

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-07-11-16-37-image.png)

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-11-07-11-17-08-image.png)





### 选择

- `S` - 按下键盘S键，选择选中的元素

- `P` - 按下键盘P键，选择选中元素的父节点

- `C`- 按下键盘C键，选择选中元素的子节点


