### 路径运动动画

1. 定义一个`path` 路径
2. 定义一个运动小球
3. Anime

```html
<style>
    .el{
        width:10px;
        height:10px;
        background:blue;
        position: absolute;
        top:5px;
        left:5px;
    }
</style>

<div class="motion-path-demo">
    <div class="small square el follow-path" ></div>
    <svg width="256" height="112" viewBox="0 0 256 112">
        <path fill="red" stroke="currentColor" stroke-width="1" d="M8,56 C8,33.90861 25.90861,16 48,16 C70.09139,16 88,33.90861 88,56 C88,78.09139 105.90861,92 128,92 C150.09139,92 160,72 160,56 C160,40 148,24 128,24 C108,24 96,40 96,56 C96,72 105.90861,92 128,92 C154,93 168,78 168,56 C168,33.90861 185.90861,16 208,16 C230.09139,16 248,33.90861 248,56 C248,78.09139 230.09139,96 208,96 L48,96 C25.90861,96 8,78.09139 8,56 Z"></path>
    </svg>
</div>


<script>
    var path = anime.path('.motion-path-demo path');

    anime({
        targets: '.motion-path-demo .el',
        translateX: path('x'),	
        translateY: path('y'), // 返回SVG路径的当前y值
        rotate: path('angle'), // 返回SVG路径的当前角度
        easing: 'linear',
        duration: 8000,
        loop: true
    });
</script>
```

### 变形动画

创建两个svg形状之间的过渡

- 通过不断改变svg形状的`points` 属性

```html
<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
    <polyline  class="lines" points="20,20 40,25 60,40 80,120 120,140 200,180" style="fill:none;stroke:black;stroke-width:3" />
</svg>
</body>


<script src="https://cdn.bootcss.com/animejs/2.2.0/anime.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    anime({
        targets: '.lines',
        points: [
            { value: '20,80 40,25 60,40 80,120 120,140 200,180' },
            { value: '20,120 40,25 60,40 80,120 120,140 200,180' },

        ],
        easing: 'easeOutQuad',
        duration: 2000,
        loop: true
    });
</script>
```

