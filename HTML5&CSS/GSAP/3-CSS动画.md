 TweenMax中对DOM元素的CSS相关属性进行动画 

比如宽度、高度、移动、旋转等动画。



**注意**

需要将CSS属性写成驼峰式



**例子**

```shell
# 容器
width // 宽度
height // 高度
opacity // 透明度
backgroud // 背景颜色
borderRadius// 圆角

color // 字体颜色
fontSize // 字体大小

boxShadow  // 阴影

scaleX // 放大缩小
rotation // 旋转 rotation：deg| rad 
skewX // 扭曲


perspective： 500 // 视觉距离
```

**2D**

| 属性     | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| x        | 水平方向位移，相当于CSS3的translateX，如`x:200`              |
| y        | 垂直方向位移，相当于CSS3的translateY，如`y:30`               |
| xPercent | 水平方向位移，以百分比为单位，如`xPercent:100%`              |
| yPercent | 垂直方向位移，以百分比为单位，如`yPercent:100%`              |
| rotation | 旋转，相当于CSS3的rotate，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度 `rotation:45` `rotation:"1.5rad"` |
| scale    | 缩放，`scale:0.8`  ，可单独设置scaleX、scaleY                |
| skewX    | 水平斜切，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度`skewX:30` |
| skewY    | 垂直斜切，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度`skewY:30` |

**3D**

| 属性      | 说明                                                         |
| :-------- | :----------------------------------------------------------- |
| z         | 纵深方向位移，相当于CSS3的translateZ，如`z:200`              |
| rotationX | X轴旋转，相当于CSS3的rotateX，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度。如`rotationX:60` |
| rotationY | y轴旋转，相当于CSS3的rotateY，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度。如`rotationY:60` |
| rotationZ | z轴旋转，相当于CSS3的rotateZ，默认是角度deg，也可以设为弧度rad，3.14弧度等于180度。如`rotationZ:60` |

```shell
//为父级元素设置视觉距离(推荐方式)
TweenLite.set(container, {perspective:500});
 
//全局设置视觉距离
CSSPlugin.defaultTransformPerspective = 500;
 
//单个元素设置视觉距离 "transformPerspective"
TweenLite.set(element, {transformPerspective:500});
```

> 举例

```js
//你的CSS:
.myClass {
    transform: translate3d(10px, 0px, -200px) rotateY(45deg) scale(1.5, 1.5);
}
 
//转化为TweenMax数值
TweenLite.to(element, 2, {scale:1.5, rotationY:45, x:10, y:0, z:-200});
 
//你的CSS:
.myClass {
    transform: perspective(500px) translateY(50px) rotate(120deg)
}
 
//转化为TweenMax数值
TweenLite.set(element, {transformPerspective:500, rotation:120, y:50});
```

