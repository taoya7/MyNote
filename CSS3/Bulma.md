---
title: CSS框架-Bulma
date: 2019-04-20 14:00:27
tags: [CSS3]
published: true
hideInList: false
feature: 
categories: ["CSS"]
---
# Bulma

[官网](https://bulma.io/documentation/overview/)

## 宽度断点

- mobile 小于等于768px
- tablet 大于等于769px
- desktop 大于等于1024px
- widescreen 大于等于1216px
- fullhd 大于等于1408px

## 基本用法

```
<link rel="stylesheet" href="css/bulma.min.css"/>
```


**大小**

- is-small
- is-primary
- is-medium
- is-large

![](css-kuang-jia-bulma/1555740968055.png)

**颜色**

- is-primary
- is-link
- is-info
- is-success
- is-warning
- is-danger

`is-white`  `is-light`  `is-dark`  ` is-black`  `is-text`

![](css-kuang-jia-bulma/1555741359662.png)

**按钮状态修饰**

- is-hovered
- is-focused
- is-active
- is-loading

![](css-kuang-jia-bulma/1555741837256.png)

## 网格体系

Bulma 的网格体系基于 Flex 布局

```
<div class="columns box">
            <div class="column">1</div>
            <div class="column">2</div>
            <div class="column">3</div>
            <div class="column">4</div>
</div>
```

![](css-kuang-jia-bulma/1555742474856.png)


当屏幕宽度大于768px所有项目平铺，平分容器宽度，如果宽度小于768所有盒子变成垂直

- is-narrow 网格的宽度由内容的宽度决定
- is-centered 网格的内容居中对齐
- is-gapless 网格之间没有间距

**12网格体系**

- is-2
- is-3
- is-4
- ...

**网格偏移**

- is-offset-one-quarter
- is-offset-one-fifth
- is-offset-8
- is-offset-1

**指定项目的宽度**

- 四分之三：is-three-quarters
- 四分之一： is-one-quarter
- 三分之二：is-two-thirds
- 三分之一：is-one-third
- 二分之一：is-half
- 五分之四：is-four-fifths
- 五分之三：is-three-fifths
- 五分之二：is-two-fifths
- 五分之一：is-one-fifth

## 容器

#### Container

`.container` 

容器的宽度取决于每个断点，在电脑端最大宽度960px 在大屏电脑上最大宽度是1344px

**流体容器**

`.is-fluid`
满屏宽度，并且左右32px的外边距

#### Level

**结构**

- level 主容器
	- level-left左侧
	- level-right 右侧
		- level-item 每个独立元素

左侧与右侧相当于flex-start与flex-end

#### Hero

横幅

整体分为三个垂直部分

- hero
	- hero-head 顶部
	- hero-body 垂直居中
	- hero-foot  底部

![](css-kuang-jia-bulma/1555747654211.png)

```html
<div class="hero is-large is-info">
    <div class="hero-head">
        <div class="container">
            <h1 class="title">Logo</h1>
        </div>
    </div>
    <!--主体-->
    <div class="hero-body">
        <div class="container">
            <h1 class="title">桃阿</h1>
            <h2 class="subtitle">从心</h2>
        </div>
    </div>
    <!--脚注-->
    <div class="hero-foot">
        <div class="container">
            QQ569781231
        </div>
    </div>
</div>
```

#### Tiles

卡片布局

`.tile`

- **三种上下文修饰符**
	- is-ancestor 始祖
	- is-parent 父
	- is-child 孩子
- **方向修饰符**
	- is-vertical


![](css-kuang-jia-bulma/1555749071437.png)

**分析**

![](css-kuang-jia-bulma/1555749066845.png)


--- 




## 响应式布局

布局默认是在手机上垂直堆叠，其他宽度都是平铺。如果希望手机也保持平铺，可以加上`is-mobile`修饰类

- is-mobile
- is-desktop

为不同的设备指定不同的布局

```
<!--手机是 flex 布局，平板是 inline 布局，其他宽度是 block 布局-->
<div class="
	column
	is-flex-mobile
	is-inline-tablet
	is-block-desktop
">
```

**隐藏与显示修饰**

- is-hidden-mobile：只在手机隐藏
- is-hidden-tablet-only：只在平板隐藏
- is-hidden-desktop-only ：只在桌面隐藏
- is-hidden-touch：手机和平板隐藏，其他宽度显示
- is-hidden-desktop 只在手机显示其他隐藏

- is-block
- is-flex
- is-inline
- is-inline-block
- is-inline-flex


## 文字

**文字大小**

- is-size-1： 3rem
- is-size-2： 2.5rem
- is-size-3： 2rem
- is-size-4： 1.5rem
- is-size-5： 1.25rem
- is-size-6： 1rem
- is-size-7： 0.75rem

为不同的设备指定不同的大小文字

- is-size-1-mobile：手机是 size-1
- is-size-1-tablet：平板是 size-1
- is-size-1-touch：手机和平板是 size-1
- is-size-1-desktop：桌面、宽屏和高清是 size-1
- is-size-1-widescreen：宽屏和高清是 size-1
- is-size-1-fullhd：高清是 size-1


**对齐方式**

- has-text-centered
- has-text-justified
- has-text-left
- has-text-right

---

# 其他元素

## Box

带有阴影、边框、圆角和一些边距的容器

- .box

## Titles

用来增加页面深度的简单 标题

- .title
	- .subtitle


