# Ajax

**是什么**

网站与服务器交换数据并更新部分网页的艺术，在不重新加载整个页面的情况

### Get请求

- 前端
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="index.php" method="get">
        <input type="text" name="UserName"><br>
        <input type="password" name="Password"><br>
        <input type="submit" value="提交"><br>
    </form>
</body>
</html>
```
- 后端
```php
<?php



$path_dst = "D:/wamp/www/index.php";

print_r($_GET); //Php接受GET的全局变量

echo $_GET["UserName"];
echo $_GET["Password"];

?>
```

### Post请求

- 前端
```html
   <!-- Post -->
	<form action="index.php" method="post">
        <input type="text" name="UserName"><br>
        <input type="password" name="Password"><br>
        <input type="submit" value="提交"><br>
    </form>
```
- 后端
```php
<?php



$path_dst = "D:/wamp/www/index.php";

print_r($_POST); //Php接受post的全局变量

echo $_POST["UserName"];
echo $_POST["Password"];

?>
```

**get与post**

- get请求的数据放到URL后面（任何信息都是可见的）
- post请求的数据放到请求头中（对其他人是不可见的）
- Get请求的数据大小有限制 | Post请求的数据大小没有限制

### 文件上传

- 前端
```html
<form action="index.php" method="post" enctype="multipart/form-data">
    <input type="file" name="upFile"><br>
    <input type="submit" name="" value="上传"><br> 
</form>
```

- Php
```php
<?php



$path_dst = "D:/wamp/www/index.php";

print_r($_FILES); 
$file = $_FILES["upFile"];

//获取上传名称
$file_name = $file['name'];
//获取上传临时路径
$file_path = $file["tmp_name"];

echo $file_path;
echo $file_name;

/*移动文件*/
move_uploaded_file($file_path, "File/".$file_name);
?>
```

**大文件上传**

修改服务器的配置文件

### 1. Ajax-GET

1. 创建一个异步对象
2. 设置请求方式与请求地址`open()`
3. 发送请求`send()`
4. 监听状态的变化`onreadystatechange`
5. 处理返回的结果

```js
var btn = document.querySelector("button");
btn.onclick = function(ev1){
    //1. 创建一个异步对象
    var http = new XMLHttpRequest();
    //2. 设置请求方式与请求地址
    /*
    * open()
    * method:请求的方式 GET or POST
    * url:文件在服务器上的位置
    * async:true(异步) or false(同步)
    * */
    http.open("GET", "index.php", true);
    //3. 发送请求
    http.send();
    //4. 监听状态的变化
    /*
    * onreadystatechange 状态
    * 0:请求未初始化
    * 1：请求连接已建立
    * 2：请求已接受
    * 3：请求处理中
    * 4：请求完成，且响应已就绪
    * */
    http.onreadystatechange = function(){
            if(http.readyState == 4){
                // 判断是否请求成功
                if(http.status == 200){
                    // 打印请求文字
                    /*
                    * responseText(); 以字符串形式的响应
                    * responseXML(); 获得XML形式的响应
                    * */
                    alert(http.responseText);
                }
                //5. 处理返回的结果
                alert("接受到！");
            }

    };
}
```

**IE-兼容问题**

```js
if(window.XMLHttpRequest){
	http = new XMLHttpRequest();
}else{
	http = new ActiveXObject("Microsoft.XMLHttp");
}
```

保证访问的URL一直变化

```js
对象.open("GET","file_path?t="+(new Date().getTime()),true);
```

**封装-GET**

```js
/*处理字符串的函数*/
function toStr(obj){
    obj.t = new Date().getTime();
    var res = [];
    for(var key in obj){
        //URL不可以出现中文 所以需要转码
        res.push(encodeURIComponent(key)+"="+encodeURIComponent(obj[key]));
    }
    return res.join("&");
}


/*封装方法*/
function ajax(url,obj,timeout, success, error){
    //1. 对象转换字符串
    var str = toStr(obj);
    //2. 建立对象
    var http, timer;
    if(window.XMLHttpRequest){
        http = new XMLHttpRequest();
    }else{
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }
    http.open("GET", url+"?"+str, true);
    //3. 发送请求
    http.send();
    //4. 监听状态的变化
    http.onreadystatechange = function(){
        if(http.readyState === 4){
        	//5. 处理
            if(http.status >=200 && http.status <300 || http.status==304){
                success(http);
            }else{
                error(http);
            }
        }
    };
    //超时处理
    if(timeout){
        timer = setInterval(function(){
            http.abort();
            clearInterval(timer);
        }, timeout);
    }
}
```


### 2. Ajax-POST

```js
// open() CODE

// post CODE
http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

// send() CODE
```

**封装-Post**

```js
/*处理字符串的函数*/
function toStr(data){
    data.t = new Date().getTime();
    var res = [];
    for(var key in data){
        //URL不可以出现中文 所以需要转码
        res.push(encodeURIComponent(key)+"="+encodeURIComponent(data[key]));
    }
    return res.join("&");
}


/*封装方法*/
function ajax(option){
    //1. 对象转换字符串
    var str = toStr(option.data);
    //2. 建立对象
    var http, timer;
    if(window.XMLHttpRequest){
        http = new XMLHttpRequest();
    }else{
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if(option.type.toLowerCase() === "GET"){
    	http.open(option.type, option.url+"?"+str, true);
    	//3. 发送请求
   		http.send();
    }else{
    	http.open(option.type, option.url, true);
    	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	http.send(str);
    }
    
    
    //4. 监听状态的变化
    http.onreadystatechange = function(){
        if(http.readyState === 4){
        	//5. 处理
            if(http.status >=200 && http.status <300 || http.status==304){
                option.success(http);
            }else{
                option.error(http);
            }
        }
    };
    //超时处理
    if(option.timeout){
        timer = setInterval(function(){
            http.abort();
            clearInterval(timer);
        }, option.timeout);
    }
}
```

### 3. JQuery-Ajax

```js
$.ajax({
	type: "GET",

	url:"index.php",

	data:"UserName=Tashi&Password=123456",

	success: function(msg){
		alert(msg);
	},

	error:function(xhr){
		alert(xhr.status);
	}
});
```

**调用自己**

```js
ajax({
	type:"get",

	timeout:3000,

	url:"index.php",

	data:"UserName=Tashi&Password=123456",

	success:function(msg){
		//Code
	},

	error: function(meg){
		//Code
	}
});

```



### 4. ajax与json

**对象转化JSON字符串**`JSON.stringify()`

**JSON转换对象**`JSON.parse()`



