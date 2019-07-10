---
title: Vue-过滤器
date: 2019-05-06 15:17:39
tags: [Vue]
published: true
hideInList: false
feature: 
categories: ["JavaScript"]

---
# 过滤器

可以用作一些常见的文本格式化，比如日期，钱等。

**分类**

- 全局过滤器
- 私有过滤器


`Vue.filter`

```html
<div id="app">
    <h1>{{price|currency}}</h1>
</div>
```

```js
/*过滤器*/
Vue.filter("currency",function (val) {
		val=val||0;
		return "￥"+val;
});

var app = new Vue({
		el: '#app',
		data: {
				price:10
		}

})
```


**过滤器传递参数**

```js
<div id="app">
    <h1>{{price|currency('元')}}</h1>
</div>

Vue.filter("currency",function (val,end) {
		val=val||0;
		return "￥"+val+end;
});
```

![](vue-guo-lu-qi/1557127359113.png)