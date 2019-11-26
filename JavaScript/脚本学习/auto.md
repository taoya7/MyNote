## 电脑调试

1. 安装`VSCode`

2. 安装插件`hyb1996`

3. 开启服务
   
   按 Ctrl+Shift+P 或点击"查看"->"命令面板"可调出命令面板，输入 Auto.js 可以看到几个命令，移动光标到命令Auto.js: Start Server

4. 连接手机
   
   将手机连接到电脑启用的Wifi或者同一局域网中。通过命令行ipconfig(或者其他操作系统的相同功能命令)查看电脑的IP地址。在[Auto.js](https://github.com/hyb1996/Auto.js)的侧拉菜单中启用调试服务，并输入IP地址，等待连接成功

5. 运行
   
   快捷键`F5`

## 大纲

- app: 应用。启动应用，卸载应用，使用应用查看、编辑文件、访问网页，发送应用间广播等。
- console: 控制台。记录运行的日志、错误、信息等。
- device: 设备。获取设备屏幕宽高、系统版本等信息，控制设备音量、亮度等。
- engines: 脚本引擎。用于启动其他脚本。
- events: 事件与监听。按键监听，通知监听，触摸监听等。
- floaty: 悬浮窗。用于显示自定义的悬浮窗。
- files: 文件系统。文件创建、获取信息、读写。
- http: HTTP。发送HTTP请求，例如GET, POST等。
- images, colors: 图片和图色处理。截图，剪切图片，找图找色，读取保存图片等。
- keys: 按键模拟。比如音量键、Home键模拟等。
- shell: Shell命令。
- threads: 多线程支持。
- ui: UI界面。用于显示自定义的UI界面，和用户交互。

## APP应用

```shell
//启动微信
launch("com.tencent.mm");
```

## 和Java一起工作

```shell
importPackage(java.lang)


toast(new java.util.Date())
```

将创建的对象放在Js变量中，也可以调用它的方法

```shell

```

## 控件寻找

> **了解**

**常见的单个控件**

`TextView` `ImageView` `CheckBox` `EditText` `View`

**容器控件**

`LinearLayout` `RelativeLayout` `FrameLayout` `ListView` `RecyclerView` `ScrollView`

> **寻找方式**

通常使用一个控件的属性来找到这个控件。控件有各种属性比如文本，描述，类名等

```shell
// 基于文本(text)控件 并且点击

var send= text("发送").findOne();

send.click();

- findOne() 表示基于这个条件找到一个符合条件的
```

```shell
// 基于描述来寻找控件
desc("搜索").findOne().click();
```

```shell
// 基于ID来寻找控件
id("action_search").findOne().click()
```

- `findOne()` 表示基于这个条件找到一个符合条件的控件

**文本**

- `text`
  - `textContains`  为当前选择器附加控件"text需要包含字符串str"的筛选条件
  - `textStartsWith(prefix)`
  - `textEndsWith(suffix)`
  - `textMatches(reg)`
- `desc(str)`
  - `descContains(str)`
  - `descStartsWith(prefix)`
  - `descEndsWith(suffix)`
  - `descMatches(reg)`
- `id(resId)`
- `waitFor()`



> 匹配

- Contains

- StartWidth

- EndWidth

- Matches

`parent()`

返回该控件的父控件。

## 控件交互

> 点击

`Click(text,[,i])`

- `text` {string} 要点击的文本

- `i` {number} 如果相同的文本在屏幕中出现多次，则i表示要点击第几个文本

> 长按

`longClick(text[, i]))`

- `text` {string} 要长按的文本
- `i` {number} 如果相同的文本在屏幕中出现多次，则i表示要长按第几个文本

## 设备

> `device.width`

设备屏幕分辨率的宽度

> device.height

设备屏幕分辨率的高度

> device.device

设备在工业设计中的名称

> deivce.model

设备型号

> device.hardware

设备的硬件名称。比如麒麟980

> device.getBrightness()

返回当前的(手动)亮度。范围为0~255

[文档连接](https://hyb1996.github.io/AutoJs-Docs/#/device)

## 文件

> files.listDir(path[, filter])

列举文件夹下的所有文件与文件夹

---

## 网络

### Get请求

> http.get(url[, options, callback])

- `url` {string} 请求的URL地址，需要以"[http://"或"https://"开头。如果url没有以"http://"开头，则默认为"http://"。](http://xn--%22%22https-mz8v//%22%E5%BC%80%E5%A4%B4%E3%80%82%E5%A6%82%E6%9E%9Curl%E6%B2%A1%E6%9C%89%E4%BB%A5%22http://%22%E5%BC%80%E5%A4%B4%EF%BC%8C%E5%88%99%E9%BB%98%E8%AE%A4%E4%B8%BA%22http://%22%E3%80%82)
- `options` {Object} 请求选项。参见[http.request()][]。
- `callback` {Function} 回调函数，可选，其参数是一个[Response][]对象。如果不加回调函数，则该请求将阻塞、同步地执行。

```c
let url = "http://www.baidu.com";
var res = http.get(url);

if (res.statusCode == 200) {
  console.show();
  log(res.body.string());
} else {
  toast("请求失败" + res.statusMessage);
}

```

> 自定义Header

```c
var res = http.get(url, {
  headers: {
    "User-Agent":
      "Mozilla/5.0(Macintosh;IntelMacOSX10_7_0)AppleWebKit/535.11(KHTML,likeGecko)Chrome/17.0.963.56Safari/535.11"
  }
});
```

### Post请求

`http.post(url, data[, options, callback])`

- `url` {string} 请求的URL地址，需要以"[http://"或"https://"开头。如果url没有以"http://"开头，则默认为"http://"。](http://xn--%22%22https-mz8v//%22%E5%BC%80%E5%A4%B4%E3%80%82%E5%A6%82%E6%9E%9Curl%E6%B2%A1%E6%9C%89%E4%BB%A5%22http://%22%E5%BC%80%E5%A4%B4%EF%BC%8C%E5%88%99%E9%BB%98%E8%AE%A4%E4%B8%BA%22http://%22%E3%80%82)
- `data` {string} | {Object} POST数据。
- `options` {Object} 请求选项。
- `callback` {Function} 回调，其参数是一个[Response][]对象。如果不加回调参数，则该请求将阻塞、同步地执行。

```c
var url = "https://login.taobao.com/member/login.jhtml";
var username = "你的用户名";
var password = "你的密码";
var res = http.post(url, {
    "TPL_username": username,
    "TPL_password": password
});
var html = res.body.string();
if(html.contains("页面跳转中")){
    toast("登录成功");
}else{
    toast("登录失败");
}
```



`http.postJson(url[, data, options, callback])`

### 文件上传

`http.postMultipart(url, files[, options, callback])`

- `url` {string} 请求的URL地址，需要以"[http://"或"https://"开头。如果url没有以"http://"开头，则默认为"http://"。](http://xn--%22%22https-mz8v//%22%E5%BC%80%E5%A4%B4%E3%80%82%E5%A6%82%E6%9E%9Curl%E6%B2%A1%E6%9C%89%E4%BB%A5%22http://%22%E5%BC%80%E5%A4%B4%EF%BC%8C%E5%88%99%E9%BB%98%E8%AE%A4%E4%B8%BA%22http://%22%E3%80%82)
- `files` {Object} POST数据。
- `options` {Object} 请求选项。
- `callback` {Function} 回调，其参数是一个`Response`对象。如果不加回调参数，则该请求将阻塞、同步地执行。

```c
upurl = "http://192.168.1.102:8088/upload";

var res = http.postMultipart(upurl, {
  file: open("/sdcard/123.jpg")
});

toast(res.body.string());
```

- 上传单个文件`file: open("/sdcard/1.txt")`

- 上传单个文件`[fileName, filePath]`
  
  - `file: ["1.txt", "/sdcard/1.txt"]`

- `[fileName, mimeType, filePath]` 
  
  - `file: ["1.txt", "text/plain", "/sdcard/1.txt"]`

### 请求响应

无论是get请求还是post请求服务器都会响应回给我们信息。

- `Response.statusCode` - `{number}`
  
  - 当前响应的HTTP状态码

- `Response.statusMessage` - `{string}`
  
  - 当前响应的HTTP状态信息。例如"OK", "Bad Request", "Forbidden"

- `Response.headers` - `{Object}`
  
  - 当前响应的HTTP头部信息。该对象的键是响应头名称，值是各自的响应头值

- `Response.body` - `{Object}`
  
  - 当前响应的内容
    
    - `bytes() {Array} `以字节数组形式返回响应内容
    - `contentType {string} `当前响应的内容类型
    - `string() {string}` 以字符串形式返回响应内容
    - `json() {Object}` 把响应内容作为JSON格式的数据并调用JSON.parse，返回解析后的对象

- `Response.url` - `{Object}`
  
  - 当前响应所对应的请求URL

- `Response.method` - `{string}`
  
  - 当前响应所对应的HTTP请求的方法
