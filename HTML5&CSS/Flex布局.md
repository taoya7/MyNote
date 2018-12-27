# Flex

### 1. Flex布局

- display：flex 将对象作为弹性伸缩盒展示，用于块级元素

- display：inline-flex 将对象作为弹性伸缩盒展示，用于行内元素

### 2. 常用的属性

**flex-direction**用于指定Flex主轴的方向，既然决定Flex子项在Flex容器的位置

取值：row|row-reverse|column|column-reverse

![](image/1.png)

**justify-content**主轴上子项的对齐方式

- justify-content: flex-start; 左对齐
- justify-content: flex-end; 右对齐
- justify-content: center; 居中对齐
- justify-content: space-between; 两端对齐
- justify-content: space-around;



**align-items**用来指定侧轴子项的对齐方式

- align-items:stretch; 拉伸对齐
- align-items:flex-start; 顶部对齐
- align-items:flex-end; 底部对齐
- align-items:center; 垂直居中对齐
- align-items:baseline; 基线对齐


**flex-wrap**用来指定Flex子项是否换行

- flex-wrap: nowrap; 默认不换行
- flex-wrap: wrap; 换行
- flex-wrap: wrap-reverse; 反方向换行


**align-content**多行对齐方式

- flex-start
- flex-end
- center
- space-between
- space-around


**align-self**单独的某个子项的对齐方式

- auto
- flex-start
- flex-end
- center
- baseline
- stretch






