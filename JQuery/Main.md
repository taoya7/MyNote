### JQuery入口函数的其他写法

```html
$(document).ready(function(){
	alert("Hello");
});
```

```
$(window).on('load',function(){
    alert("Hello");
});
```






### JQuery核心函数`$();`

**用法**

```js
// 接收一个函数
$(function(){
	alert("Hello");
});
```

```js
// 接收一个DOM元素
var div = $(div);
console.log(div)
```

```js
// 接收一个字符串选择器
var div = $(".box");
console.log(div)
/*返回一个JQuery对象，对象中保存了找到的DOM元素*/
```

```js
// 接受一个字符串代码片段
var p = $("<p>我是段落</p>")[0];
/*返回一个JQuery对象，对象中保存了创建的DOM元素*/
```

### 静态方法与实例方法

```js
//定义类
function AClass(){

}
//直接添加给类的是静态方法
AClass.staticMethod = function(){
    alert("StaticMethod!");
}
//调用
AClass.staticMethod()
```

```js
//1.创建类
function AClass(){

}
//2. 创建实例方法
AClass.prototype.instanceMethod = function(){
    alert("InstanceMethod");
}
// 3. 创建对象
var a =new  AClass();
// 4. 对象调用
a.instanceMethod();
```

**JQuery-foreach()**

```js
    /*原生ForEach
    *
    * 参数1：遍历到的数组
    * 参数2：当前遍历到的索引
    * 注意： 原生foreach只能遍历数组
    * */
    var arr = [1,3,5,7,9];
    arr.forEach(function(value, index){
        console.log(value, index);
    });


    /*
    * JQuery-ForEach
    * */
    var arr = [1,3,5,7,9];
    $.each(arr, function(value, index){
        console.log(value, index);
    });

    var obj = {"Name":"Aha", "Age":15};
    $.each(obj, function(key, value){
        console.log(key,value);
    });
```

**JQuery-map()**

```js
var arr = [1,3,5,7,9];
var obj = {"Name":"Aha", "Age":15};

/*原生JS-map遍历
*
* 参数1：当前遍历的元素
* 参数2：当前遍历的索引
* 参数3：当前遍历的数组
*
* 注意：只能遍历数组
* */
arr.map(function(value, index, array){
    console.log(value, index, array);
});

/*JQuery-map
* 参数1：要遍历的对象
* 参数2：遍历一个元素之后执行的回调函数
* */
$.map(arr, function(value, index, array){
    console.log(value, index, array);
});
$.map(obj, function(value, index, array){
    console.log(value, index, array);
});
```

> each()与map()

1. each静态方法默认的返回值就是,遍历谁就返回谁
2. map静态方法返回值是一个空数组

**其他静态方法**

```js
/*
* trim();
* 作用：去除字符串两端的空格
* 参数：需要去除的字符串
* 返回值：去除之后的字符串
* */
var str = "     Ha     ";
console.log(str);
var res = $.trim(str);
console.log(res);

/*
* isWindow();
* 作用：判断传入的对象是否window对象
* */

/*
* isArray();
* 作用：判断传入的对象是否为真数组
* */

/*
* isFunction();
* 作用：判断传入的是否为函数
* */

/*
* $.holdReady(true);
* 作用：暂停ready执行
* */
```


### JQuery-选择器
- 基本选择器
- 层级选择器
	- 后代`$("ul li")` `$("ul>li")`
	- 兄弟`$("p~div")``$("p+div")`
- 属性选择器
- 筛选选择器
	- `parent()`
	- `parents()`
	- `children()`
	- `siblings()`

### JQuery-尺寸操作|位置操作

- `width() ` 设置或返回元素的宽度（不包括内边距，边框 ， 外边距）
- `height()` 设置或返回元素的高度（不包括内边距，边框 ， 外边距）
- `innerWidth()`设置或返回元素的宽度（包括内边距）
- `innerHeight()`设置或返回元素的高度（包括内边距）
- `outerWidth()`设置或返回元素的宽度（包括内边距，边框）
- `outerHeight()`设置或返回元素的高度（包括内边距，边框）
- `outerWidth(true)`设置或返回元素的宽度（包括内边距，边框，外边距）
- `outerHeight(true)`设置或返回元素的高度（包括内边距，边框，外边距）

---

- `offset([coordinates])` 作用：设置|获取元素距离窗口的偏移位
```js
$('.box').offset({ //设置
        left:100,
        top:110
    });
    console.log($('.box').offset()); //获取
```
- `position()` 作用：获取元素距离定位元素的偏移位
```js
console.log($('.son').position('.box'));//获取
    //不能设置
```
- `scrollTop([val])` 作用：设置|获取元素的滚动值
- `scrollLeft([val])`
```js
console.log($("div").scrollTop());
    $("div").scrollLeft(300); //设置
```

### JQuery-动画

**自定义动画**

```js

/*
	//三个参数
	1. 接受一个对象可以在对象中修改属性
	2. 指定动画时长
	3. 动画节奏 默认swing linear
	4. 动画执行完毕之后的回调函数
*/
$("div").animate({
	//属性
}, 1000, function(){
	alert("动画执行完毕！");
})
```



### JQuery-标签处理
- 获取内容`text()`
	- 获取标签和内容`html()`
	- 获取输入框的内容`val()`
- 设置内容`text(内容)`
- 获取属性`attr(属性)`
	- **注意**无论找到多少元素，都只会返回第一个元素指定属性节点的值
- 设置属性`attr(属性,值)`
- 删除属性`removeAttr(属性);`
	- **注意**删除多个属性用空格隔开






### JQuery-文档处理

**添加节点**

- 内部插入
```js
append(content|fn) //元素添加到指定元素内部
appendTo(content)
prepend(content|fn) //将元素添加到指定元素的最前面
prependTo(content)
```

- 外部插入
```js
after(content|fn) //将元素添加到指定元素外部的后面
insertAfter(content)

before(content|fn)//将元素添加到指定元素外部的前面
insertBefore(content)
```

**删除节点**

```js
remove() //删除指定元素
empty() //删除指定元素的内容和子元素，指定元素自身不会被删除
detach([expr]) //删除所有匹配的元素
```

**替换**

```js
replaceWith(content|fn) //替换所有匹配的元素

replaceAll(selector) //替换顺序不一样
```

**复制节点**

```js
//传入false是浅复制 传入true是深复制
clone([Even[,deepEven]])
```

[中文文档](http://jquery.cuishifeng.cn/)


### Hash

- 设置

```js
window.location.hash = 3

>>> url = www.baidu.com/#3
```

- 获取

```
console.log(window.location.hash);
```
