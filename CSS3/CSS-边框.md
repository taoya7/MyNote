# Border

CSS3增加了三种边框属性
- border-radius 圆角边框
- border-shadow 边框阴影
- border-image 图片边框

#### 圆角边框
`border-radius`

```
.box{
            width:300px;
            height:150px;
            background:#2b542c;
            border-radius: 10px;
        }
```

![](image/1556771167793.png)

当然也可以设置不同的弧度的圆角

```
.box{
            width:300px;
            height:150px;
            background:#2b542c;
            border-top-right-radius:50%;
            border-bottom-left-radius: 50%;
        }
```

![](image/1556771286896.png)

#### 边框阴影

`box-shadow`

参数值：

- h-shadow 水平位置
- v-shadow 垂直位置
- blur 阴影模糊距离
- spread 阴影尺寸
- color 颜色
- inset 外部阴影还是内部阴影

```
box-shadow: 0px 3px 8px 0 rgba(0,0,0,.2);
```

![](image/1556771699629.png)

#### 图片边框

- border-image-source 图片路径
- border-image-slice 内偏移
- border-image-width 边框的宽度
- border-image-outset 超出边框的量
- border-image-repeat 是否平铺


