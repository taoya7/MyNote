# CSS滚动视差


视差滚动（Parallax Scrolling）是指让多层背景以不同的速度移动，形成立体的运动效果，带来非常出色的视觉体验。 作为网页设计的热点趋势，越来越多的网站应用了这项技术。


![58.gif](https://upload-images.jianshu.io/upload_images/11006938-25bfc1f0f8812d08.gif?imageMogr2/auto-orient/strip)


### #方案一`background-attachment: fixed`

创建一个DIV，然后设立图片背景。这里有一个非常重要的属性`background-attachment: fixed;`背景是在视口中固定的还是随着包含它的区块滚动的

**属性**

- fixed 视口并不随着滚动而滚动
- scroll 背景相对于元素本身固定
- local 如果一个元素拥有滚动机制，背景将会随着元素的内容滚动

```css
.g-img{
    width:100vw;
    height:200px;
    background:url("image/th.jpg");
    background-size: 100% 100%;
    background-attachment: fixed;
}
```


### 方案二 `transform: translate3d`


![66.gif](https://upload-images.jianshu.io/upload_images/11006938-2257cc0d3eb2b7a6.gif?imageMogr2/auto-orient/strip)


**关键**

- 父容器设置transform-style: preserve-3d 和 perspective: xpx
	
- 子元素设置不同的 transform: translateZ()

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        html {
            overflow: hidden;
        }

        body {
            perspective: 1px;
            transform-style: preserve-3d;
        }
        #app{
            width: 100vw;
            height:400px;
            background:skyblue;
            margin:auto;
            overflow: auto;
        }
        .box{
            width:80%;
            height:5000px;
            margin:auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            /*Import*/
            transform-style: preserve-3d;
            perspective: 10px;
        }
        .box>div:nth-child(1){
            width:500px;
            height:200px;
            background:red;

            transform: translateZ(0px);
        }
        .box>div:nth-child(2){
            width:500px;
            height:200px;
            background:red;
            transform: translateZ(-1px);
        }
        .box>div:nth-child(3){
             width:500px;
             height:200px;
             background:red;
            transform: translateZ(-2px);
         }
    </style>
</head>
<body>
   <div id="app">
        <div class="box">
            <div></div>
            <div></div>
            <div></div>
        </div>
   </div>
</body>
</html>
```