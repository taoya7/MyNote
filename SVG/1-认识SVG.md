### #简介

XML描述的矢量文件

**位图**(BMP.PNG.JPG)

**矢量图**(SVG,AI)

- **使用方式**

	- 浏览器直接打开
	- 嵌套中`<img src="Demo.svg">`
	- 使用SVG标签
	- CSS背景

- **初始化**

```
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <circle cx="100" cy="50" r="40" fill="red">
</svg>
```

- **基本图形**
    - rect 矩形
    - circle圆形
    - elipse椭圆
    - line线
    - polyline折线
    - polygon多边形
    - 路径 path
- **基本属性**
    - fill
    - stroke
    - stroke-width
    - transform
### #Path

区分大小写。大写绝对位置，小写为相对位置

- `命令 参数`

- M移动画笔
- L绘制直线到指定位置
- H绘制水平线到指定的X位置
- V绘制竖直的的指定的Y位置
```
<!-- 直线Demo -->
<path d="M0 0 L 200 300 M 0 0 L 100 100 H 400" stroke="red" stroke-width="2" fill="none"/>
```

**曲线**

- A六个参数
	
	前两个参数绘制椭圆形 第三个参数确定旋转角度 第四个第五个参数确认需要的弧度值为[0,1] 第六七参数结束坐标

### #文本

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

# #Demo
```html
<!-- 矩形 -->
<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
  <rect width="300" height="100"
  style="fill:rgb(0,0,255);stroke-width:1;stroke:rgb(0,0,0)"/>
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

```html
<!-- 圆形 -->
<circle cx="0" cy="0" r="40" fill="red" />
```

- cx横坐标
- cy纵坐标
- r半径

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