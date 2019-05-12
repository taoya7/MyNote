## 基本使用


HTML

如果要指定加载页面时显示的“屏幕”，可以在对应的 section 加上 class=”active”

```
<div id="page">
    <!--Page1-->
    <section class="section">

    </section>
    <!--Page2-->
    <section class="section">

    </section>
    <section class="section">

    </section>
    <section class="section"></section>
</div>
```

JS激活

```
<script>
    $('body').ready(function () {
       $('#page').fullpage({
           'navigation':true, //显示导航
           'verticalCentered': false, //内容是否垂直居中
           'slidesColor': ['#254875', '#00FF00', '#254587', '#695684'], //设置背景颜色
       })
    })
</script>
```

## 常用

配置


|  选项 | 类型  |  默认值 | 说明 |
| ------------ | ------------ | ------------ | ------------ |
|verticalCentered|	字符串|	true	|内容是否垂直居中|
|   resize|   布尔值| false  |	字体是否随着窗口缩放而缩放   |
| slidesColor  |  数组 |  无 |  设置背景颜色 |
| anchors  | 数组  |  无 | 定义锚链接  |
|  scrollingSpeed |整数  | 700  |  滚动速度，单位为毫秒 |
|  easing |  字符串 |  easeInQuart | 滚动动画方式  |
|menu   |  布尔值 |  false | 绑定菜单，设定的相关属性与 anchors 的值对应后，菜单可以控制滚动  |
| navigation  |  布尔值 |   false| 是否显示项目导航  |
|  navigationPosition | 字符串  |right   | 项目导航的位置，可选 left 或 right  |
| navigationColor  | 字符串  |   #000|  项目导航的颜色 |
|  navigationTooltips | 数组  | NULL  |  项目导航的 tip |
| slidesNavigation  |  布尔值 |   false|  是否显示左右滑块的项目导航 |
| slidesNavPosition  | 字符串  | bottom  | 左右滑块的项目导航的位置，可选 top 或 bottom  |
| controlArrowColor  |  字符串 | #fff  |  左右滑块的箭头的背景颜色 |
|  loopBottom | 布尔值  |  false | 	滚动到最底部后是否滚回顶部 |
|loopTop   |  布尔值 | false  |  	滚动到最顶部后是否滚底部 |
|  loopHorizontal | 布尔值  |  true |左右滑块是否循环滑动   |
|  autoScrolling | 布尔值  |   true|  是否使用插件的滚动方式，如果选择 false，则会出现浏览器自带的滚动条 |
|  scrollOverflow |布尔值   |  false |  内容超过满屏后是否显示滚动条 |
| css3  | 布尔值  |  false |  	是否使用 CSS3 transforms 滚动 |
| paddingTop  |   字符串|  0 | 	与顶部的距离  |
|  paddingBottom |   |   |  	与底部距离 |
| continuousVertical  |  布尔值 |false   |  是否循环滚动，与 loopTop 及 loopBottom 不兼容 |


## 方法

- mouseSectionUp() 向上滚动

- moveSectionDown()向下滚动

- moveTo(section, slide)滚动到

- moveSlideRight()slide 向右滚动

- moveSlideLeft()slide 向左滚动

- setAutoScrolling()设置页面滚动方式，设置为 true 时自动滚动

- setAllowScrolling()添加或删除鼠标滚轮/触控板控制

- setKeyboardScrolling()添加或删除键盘方向键控制

- setScrollingSpeed()定义以毫秒为单位的滚动速度

## 回调函数

- afterLoad

滚动到某一屏后的回调函数，接收 anchorLink 和 index 两个参数，anchorLink 是锚链接的名称，index 是序号，从1开始计算

- onLeave

滚动前的回调函数，接收 index、nextIndex 和 direction 3个参数

index 是离开的“页面”的序号，从1开始计算

nextIndex 是滚动到的“页面”的序号，从1开始计算

direction 判断往上滚动还是往下滚动，值是 up 或 down

- afterRender 

页面结构生成后的回调函数，或者说页面初始化完成后的回调函数

- afterSlideLoad 

滚动到某一水平滑块后的回调函数  接收 anchorLink、index、slideIndex、direction 4个参数
 
- onSlideLeave  

 某一水平滑块滚动前的回调函数 接收 anchorLink、index、slideIndex、direction 4个参数




**方法使用**

```
<script>
    //下滚动
    $('.moveDown').click(function () {
        $('#page').fullpage.moveSectionDown();
    });
    //上滚动
    $('.moveUp').click(function () {
        $('#page').fullpage.moveSectionUp();
    });
    //左滚动
    $('.moveLeft').click(function () {
        $('#page').fullpage.moveSlideLeft();
    });
    //右滚动
    $('.moveRight').click(function () {
        $('#page').fullpage.moveSlideRight();
    });
</script>
```



## 参考资料

[jQuery-fullPage.js插件学习笔记](https://www.jianshu.com/p/43e39e711684?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation)


[关于fullpage的使用方法（笔记）](https://blog.csdn.net/u013299635/article/details/88060899)


