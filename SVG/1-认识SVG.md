## 简介

使用XML描述的矢量文件

**位图**(BMP.PNG.JPG)

**矢量图**(SVG,AI)

**使用方式**

- 浏览器直接打开
- 嵌套中`<img src="Demo.svg">`
- 使用SVG标签
- CSS背景

## 初始化

```xml
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <circle cx="100" cy="50" r="40" fill="red">
</svg>
```

## 坐标系统

`<svg>` 可以指定width和height属性，说明它在HTML中所占的宽度和高度。如果不指定默认300x150。

`<svg>`中有一个viewbox属性这个属性的四个值分别代表`左上角的横坐标和纵坐标`，`视口的宽度与高度`

如果不指定width和height只指定viewbox，则相当于只给定 SVG 图像的长宽比。这时，SVG 图像的默认大小将等于所在的 HTML 元素的大小

- width, height控制视窗

> 四个坐标系

- 用户坐标系

  世界的坐标系                                                                                                                                                

- 自身坐标系

  每个图形元素或分组独立与生俱来

- 前驱坐标系

  父容器的坐标系

- 参考坐标系

  使用其他坐标系来参考自身的情况时使用

## 基本图形

- rect 矩形
- circle圆形
- elipse椭圆
- line线
- polyline折线
- polygon多边形
- 路径 path

**基本属性**

- fill 填充颜色
- stroke
- stroke-width
- transform
  - `rotate(30)`
  - `translate(x,y)`

---

> 矩形


```xml
//矩形
<svg   viewBox="0 0 600 400">
    <rect width="300" height="100"
          style="fill:red;stroke-width:1;stroke:rgb(0,0,0)"/>
</svg>
```

- width|height宽度与高度
- style CSS属性
- fill填充颜色
- stroke-width边框宽度
- stroke边框颜色
- x距离左边的位置
- y距离顶部的位置
- fill-opacity 颜色透明度
- stroke-opacity边框透明度
- rx|ry圆角属性

----

> 圆形


```html
<!-- 圆形 -->
<circle cx="0" cy="0" r="40" fill="red" />
```

- cx横坐标
- cy纵坐标
- r半径

> 椭圆

```html
<!-- 椭圆 -->
<ellipse cx="300" cy="80" rx="100" ry="50"
  style="fill:yellow;stroke:purple;stroke-width:2"/>
```

- cx横偏移
- cy纵偏移
- rx水平半径
- ry垂直半径

```html
<!-- 直线 -->
<line x1="0" y1="0" x2="200" y2="200" fill="red" stroke="red"/>
```

- x1
- y1 确定开始位置
- x2
- y2 结束位置

折线，指定每个端点的坐标，横坐标与纵坐标用逗号分开，点与点用空格分开

```html
<!-- 折线 -->
<polyline points="20,20 40,25 60,40 80,120 120,140 200,180" style="fill:none;stroke:black;stroke-width:3" />
```

- points路径点

```html
<!-- 多边形 -->
<polygon points="200,10 250,190 160,210"
  style="fill:lime;stroke:purple;stroke-width:1"/>
```

- pointsxy组成一个点 然后空格分隔 最后连线



## Path

区分大小写。大写绝对位置，小写为相对位置

`命令 参数`

- M移动画笔
- L绘制直线到指定位置
- H绘制水平线到指定的X位置
- V绘制竖直的的指定的Y位置
```
<!-- 直线Demo -->
<path d="M0 0 L 200 300 M 0 0 L 100 100 H 400" stroke="red" stroke-width="2" fill="none"/>
```

**曲线**

A六个参数
	
前两个参数绘制椭圆形 第三个参数确定旋转角度 第四个第五个参数确认需要的弧度值为[0,1] 第六七参数结束坐标

## 文本

**标签**

- `<text>`
- `<tspan>`

**属性**

- x|y
- dx|dy
- style

**路径文本**

- `<textPath>`
```
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink">
  <defs>
    <path id="path1" d="M75,20 a1,1 0 0,0 100,0" />
  </defs>
  <text x="10" y="100" style="fill:red;">
    <textPath xlink:href="#path1">I love SVG I love SVG</textPath>
  </text>
</svg>
```


**超链接**

- xlink:href 地址
- xlink:title 链接提示
- target 打开方式


## Color

- Rgb
- HSL
	- 颜色
	- 饱和度
	- 亮度
- 透明度
	- rgba
	- opacity属性[0,1]


## 渐变

- 线性渐变
- 径向渐变

```
<defs>
    <linearGradient id="grad1" gradientUnits="userSpaceOnUse"
        x1="0" y1="100" x2="200" y2="50"
    >
        <stop offset="0" stop-color="#fcd307"></stop>
        <stop offset="0" stop-color="#1c1259"></stop>
    </linearGradient>
</defs>
<rect width="200" height="200" fill="url(#grad1)"/>
```



## 其他

### use标签

标签用于复制一个形状

### g标签

用于将多个形状组成一个组（group），方便复用

### defs标签

用于自定义形状，它内部的代码不会显示，仅供引用

```
<svg width="300" height="100">
    <defs>
        <g id="myCircle">
            <text x="25" y="20">圆形</text>
            <circle cx="50" cy="50" r="20"/>
        </g>
    </defs>

    <use href="#myCircle" x="0" y="0" fill="red"/>
    <use href="#myCircle" x="100" y="0" fill="blue" />
    <use href="#myCircle" x="200" y="0" fill="white" stroke="blue" />
</svg>
```



## JavaScript操作



### 创建图形
```javascript
document.createElementNS(ns, tagName);
```

### 添加形状
```js
element.appendChild(childElement)
```

### 设置|获取属性
```js
element.setAttribute(name, value);

element.getAttribute(name);
```


