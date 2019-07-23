## 导入

```
<link rel="stylesheet" href="dist/aos.css" />
<script src="js/jquery.min.js"></script>
<script src="dist/aos.js"></script>
```

## HTML

```
<div aos="animation_name">
```

## 属性

- aos-offset	是立刻触发动画还是在指定时间之后触发动画	200	120
- aos-duration	动画持续时间	600	400
- aos-easing	动画的easing动画效果	ease-in-sine	ease
- aos-delay	动画的延迟时间	300	0
- aos-anchor	锚元素。使用它的偏移来取代实际元素的偏移来触发动画	#selector	null
- aos-anchor-placement	锚位置，触发动画时元素位于屏幕的位置	top-center	top-bottom
- aos-once	动画是否只会触发一次，或者每次上下滚动都会触发	true	false



## 全局配置

```
AOS.init({
  offset: 200,
  duration: 600,
  easing: 'ease-in-sine',
  delay: 100,
});
```

**禁用AOS**

```
# 在小屏幕设备中禁用AOS
AOS.init({
  disable: 'mobile' # mobile、phone或tablet
});
```


**动画**- 

淡入淡出动画：

- fade-up
- fade-down
- fade- -left
- fade-right
- fade-up-right
- fade-up-left
- fade-down-right
- fade-down-left

翻转动画：

- flip-up
- flip-down
- flip-left
- flip-right

滑动动画：

- slide-up
- slide-do- wn
- slide-left
- slide-right

缩放动画：

- zoom-in
- zoom-in-up
- zoom-in-down
- zoom-in-left
- zoom-in-right
- zoom-out
- zoom-out-up
- zoom-out-down
- zoom-out-left
- zoom-out-right

锚位置

- top-bottom
- top-center
- top-top
- center-bottom
- center-center
- center-top
- bottom-bottom
- bottom-center
- bottom-top

easing动画

你可以使用以下的一些easing动画效果：

- linear
- ease
- ease-in
- ease-out
- ease-in-out
- ease-in-back
- ease-out-back
- ease-in-out-back
- ease-in-sine
- ease-out-sine
- ease-in-out-sine
- ease-in-quad
- ease-out-quad
- ease-in-out-quad
- ease-in-cubic
- ease-out-cubic
- ease-in-out-cubic
- ease-in-quart
- ease-out-quart
- ease-in-out-quart


[Demo](http://demo.lanrenzhijia.com/demo/41/4139/demo/)