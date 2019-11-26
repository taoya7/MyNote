**起步**

```c
"ui";

//Your UI Code
```

## 布局

- 垂直布局`vertical`
- 水平布局`horizontal`
- 帧布局`frame`

通过`ui.layout()`函数指定界面的布局xml

> 垂直布局

垂直布局是一种简单的布局

```c
    "ui";
ui.layout(
  <vertical>
    <button text="第一个按钮" />
    <button text="第二个按钮" />
  </vertical>
);
```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-29-22-53-47-image.png)

- `layout_weight` 属性来控制子控件高度占垂直布局高度的比例
  
  

> 水平布局`horizontal`

```c
"ui";
ui.layout(
    <horizontal>
        <button text="第一个按钮"/>
        <button text="第二个按钮"/>
    </horizontal>
);
```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-29-22-55-50-image.png)

- `layout_weight` 水平布局中也可以使用layout_weight属性来控制子控件的**宽度**占父布局的比例



> gravity

用于决定View的内容相对于View的位置

- `left` 靠左
- `right` 靠右
- `top` 靠顶部
- `bottom` 靠底部
- `center` 居中
- `center_vertical` 垂直居中
- `center_horizontal` 水平居中

这些属性是可以组合的，例如`gravity="right|bottom"`的View他的内容会在右下角

```c
"ui";
ui.layout(
    <frame>
        <button gravity="right" w="*" h="auto" text="靠右的文字"/>
    </frame>
);
```

> 帧布局`frame`



### 外边距`marigin`

margin为View和其他View的间距，即外边距。margin属性包括四个值:

- `marginLeft` 左外边距
- `marginRight` 右外边距
- `marginTop` 上外边距
- `marginBottom` 下外边距

**格式**

1. `margin="marginAll"`  所有边距

2. `margin="left, top, right, bottom"` 定制四周边距

3. `margin="水平, 垂直"`

### 内边距`padding`

同理

### 属性

- `bg="#d597ce"`
  
  - 指定了垂直布局的背景色
  
  也可以给定图片 ，设置背景为点击时出现的波纹效果(可能需要同时设置`clickable="true"`才生效)。

- `click()`
  
  - 设置视图(View)被点击时执行的动作

- 宽度`w`
  
  - `*`
  
  - `auto`
  
  ```c
  "ui";
  ui.layout(
   <horizontal>
     <button text="父容器宽度" w="auto" />
     <button text="自适应宽度" w="*" />
   </horizontal>
  );
  ```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-29-23-02-41-image.png)

        如果不设置，不同的控件有不同的默认值宽度。宽度的单位可以是`dp` `px` `mm` `cm`

- 高度`h`
  
  与宽度同理。

- ID `id`
  
  - View的id，用来区分一个界面下的不同控件和布局，一个界面的id在同一个界面下通常是唯一的
  
  - id属性也是连接xml布局和JavaScript代码的桥梁，在代码中可以通过一个View的id来获取到这个View，并对他进行操作(设置点击动作、设置属性、获取属性
  
  ```c
  "ui";
  ui.layout(
    <horizontal>
      <button text="我是一个Button" w="auto" id="btn1" />
    </horizontal>
  );
  
  toast(ui.btn1.getText()); // 我是一个Button
  ```

- `alpha`
  
  透明度,其值是一个0~1之间的小数，0表示完全透明，1表示完全不透明

- `foreground`
  
  - View的前景。前景即在一个View的内容上显示的内容，可能会覆盖掉View本身的内容。其值和属性bg的值类似

- `minHeight | minWidth`
  
  - View的最小高度|宽度。该值不总是生效的，取决于其父布局是否有足够的空间容纳

- `visbility`
  
  View的可见性，该属性可以决定View是否显示出来。其值可以为：
  
  - `gone` 不可见。
  - `visible` 可见。默认情况下View都是可见的。
  - `invisible` 不可见，但仍然占用位置

- `rotation`
  
  View的旋转角度。通过该属性可以让这个View顺时针旋转一定的角度。例如`rotation="90"`

- `transformPivotX | transformPivotY`

## 控件

### 文本

- `text ` 字体标签

- `textColor` 字体颜色

- `textSize` 字体大小

- `textStyle` 设置字体样式
  
  - bold
  - italic
  - normal

- `lines` 设置文本控件的行数， 如果超出则不显示

- `maxLines` 设置文本控件的最大行数

- `typeface`
  
  - normal 正常字体
  - sans 衬线字体
  - serif 非衬线字体
  - monospace 等宽字体

- `ellipsize` 设置文本的省略号的位置
  
  - `end` 在文本末尾显示省略号
  - `marquee` 跑马灯效果，文本将滚动显示
  - `middle` 在文本中间显示省略号
  - `none` 不显示省略号
  - `start` 在文本开头显示省略号

- `autoLink`
  
  - `all` 匹配所有连接、邮件、地址、电话
  - `email` 匹配电子邮件地址
  - `map` 匹配地图地址
  - `none` 不匹配 (默认)
  - `phone` 匹配电话号码
  - `web` 匹配URL地址
  
  控制是否自动找到url和电子邮件地址等链接，并转换为可点击的链接
  
  ```c
  <text autoLink="web" text="http://www.itaolaity.com" />
  <text autoLink="phone" text="18735673470" />
  ```

```js
"ui";

ui.layout(
  <vertical bg="#f6c89f">
    <vertical w="250" h="250" bg="#9fdfcd">
      <text text="Hello Jack" />
      <text text="WIth Color Text" textColor="blue" />
      <text
        text="With Size Text Text Text"
        textSize="36"
        lines="1"
        ellipsize="end"
      />
      <text autoLink="web" text="http://www.itaolaity.com" />
      <text autoLink="phone" text="18735673470" />
    </vertical>
  </vertical>
);
```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-30-12-55-04-image.png)



### 

## APIS

ui.layout(xml)
ui.inflate(xml[, parent])
ui.findView(id)
ui.finish()
ui.setContentView(view)
ui.run(callback)
ui.post(callback[, daley])
ui.statusBarColor(color)
ui.showPopupMenu(view, menu)ui.layout(xml)
ui.inflate(xml[, parent])
ui.findView(id)
ui.finish()
ui.setContentView(view)
ui.run(callback)
ui.post(callback[, daley])
ui.statusBarColor(color)
ui.showPopupMenu(view, menu)


