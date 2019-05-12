#Anime.js

## 起步|选择元素

**刚开始**

```
var bar = anime({
    //Code
    targets:" ",  //这里是选择器 可以是CSS选择器|DOM节点|列表|对象|数组
    translateX:450 //向X轴方向移动450px
});
```

一个对象的变化

```html
<div class="box">
    <p></p>
</div>

<script>
	// 声明一个对象 存有数值0
    var obj = {
        charged:"0"
    };


    var bar = anime({
        //Code
        targets:obj, //选取目标
        charged:'100', //改变值
        round:1,
        easing:'linear', //速度
        update:function () {
            var date = JSON.stringify(parseInt(obj.charged))
            $('.box p').text(date) //赋值
        }
    });
</script>
```

**小结**

1. `targets` 属性里面可以是CSS选择器、DOM节点、对象、数组

```
targets:"#app div",

var ele = document.querySelectorAll('.box li');
targets:"ele",

var obj = {}
targets:obj,

targets:[obj,ele, "#app div"],
```




## 动画属性

这里设置动画变化的趋势，还可以设置关键帧动画，如果一个值代表是逐帧动画

> 基本

- width
- height


- opacity

> 边框

- borderRadius
- border

> 位置

- top
- left
- right
- bottom

> 颜色

- backgroundColor 

> 变换

- scale 
- translateX
- rotate

```
//Input动态变化

<div class="box">
    <input type="text" value="0">
</div>

<script>
    var bar = anime({
        //Code
        targets:".box input", //选择元素

        value:100, //改变的值
        round:1, //绑定value round：1 表示整数形式变化

        scale:1.5, //放大
        rotate:'90deg',//旋转
        translateX:300,//平移

        easing:'easeInOutExpo', //速度

    });
</script>
```

## 动画参数

动画参数决定了动画是否循环呢？动画最后停留的方向？ 都是可以的

- direction 动画方向
	- normal 正方向
	- reverse 反方向
	- alternate 往返动画

- 循环loop
	- 数值 循环次数
	- true|false 无限循环

- autoplay 自动播放
	- true 开启自动播放
	- false 关闭自动播放

## 动画属性值

### 没有单位

没有单位代表默认以px为单位。比如 translateX: 250。形状就会以X轴移动250px

### 具体单位

`em`  `rem`  `px`  `deg` `turn`

### 相对单位

最具酷炫的还是这个 可以根据当前值做出变化

```
var bar = anime({
    //Code
    targets:".box .rect",

    translateX:{
        value: '+=400',
        duration:1000
    },

    width:{
        value:"-=100",
        duration:5000
    },
    height:{
        value:'*=2',
        duration:3000
    },

    duration: function(el, i, l) {
        return 2000 + (i * 1000);
    },
});
```
### 颜色

```
backgroundColor:[
	{value: '#FFF'}, 
    {value: 'rgb(255, 0, 0)'},
    {value: 'hsl(100, 60%, 60%)'}
]
```


### 函数

位移|旋转|缩放 后面跟上函数可以动态设置

- 函数返回值（三个）
	- target 元素本身
	- index 元素角标
	- targetsLength 总数

```
<script>
    var bar = anime({
        //Code
        targets:".box li",

        translateX:function(el){ //获取DOM元素的属性值作为移动距离
            return el.getAttribute('data-x');
        },

        /**
         *  传递了两个参数
         *  参数1：
         *      元素DOM
         *  参数2：
         *      元素的index
         * */
        translateY:function(el,i){
            console.log(i);
            return 300+(-50*i);
        },

        //放大与缩小
        scale:function(el,i){
            return (1-i)+.25; //1.25 .25 -0.75
        },
        //旋转
        rotate: function() { return anime.random(-360, 360); },


        duration: function(el, i, l) {
            return 2000 + (i * 1000);
        },
    });
</script>
```
### 关键帧

如果属性值为一个数组那么代表这是一个关键帧动画

```html
translateX:[0,100,1000,500,0],
```

后面可以跟上持续时间`duration` 效果更佳


## 性能参数

- duration 动画持续时间
- delay 延迟执行动画
- easing  缓冲
	- easeInOutQuart
	- easeInOutSine

```
<script>
    var bar = anime({
        //Code
        targets:".box .rect",

        translateX:{ //平移 持续1S
          value:[50,1000,500],
          duration:1000
        },

        rotate:{ //旋转 持续3S
            value:360,
            duration:3000,
        },

        scale:{
            value:[3,1],
            duration:2000,
            delay:300,
            easing:'easeInOutQuart'
        },

        easing:'linear',

    });
</script>
```

## 功能参数

- 持续时间direction
	- alternate 轮流执行
- 是否循环 loop
	- true
	- false
```js
var bar = anime({
    //Code
    targets:".box .rect",

    translateX:400,


    direction:'alternate',
    loop:true,


    duration: function(el, i, l) {//持续2S
        return 2000 + (i * 1000);
    },
});
```
- 延迟delay()
```js
delay: function(el, i, l) {
	return i * 100;
}
```
- 弹性
```js
elasticity: function(el, i, l) {
    return (200 + i * 200);
  }
```



## SVG


动态更改SVG图形的参数值

```
<div id="svgAttributes"> 
	<svg width="128" height="128" viewBox="0 0 128 128">
 		 <polygon points="64 68.64 8.574 100 63.446 67.68 64 4 64.554 67.68 119.426 100 " fill="currentColor"></polygon>
	</svg> 
</div>

var svgAttributes = anime({
  targets: '#svgAttributes polygon',
  points: '64 128 8.574 96 8.574 32 64 0 119.426 32 119.426 96',
  easing: 'easeInOutExpo'
});
```