

# 动画

方法一

- `<animate xlink:href="url(#ID)">`

方法二

- 
```
<rect>    
	<animate></animate>
</rect>
```

**初始**

```html
<rect id="test1" width="100" height="100" fill="red">
    <animate
        attributeType="XML"
        attributeName="width"
        from="0"
        to="400"
        dur="0.8s"
        fill="freeze"
        repeatCount="100"
></animate>
    <animate
            attributeType="XML"
            attributeName="fill"
            from="black"
            to="gold"
            dur="0.8s"
            fill="freeze"
    ></animate>
</rect>
```

- 轨迹移动`<animateMotion>`
	- path
	- dur