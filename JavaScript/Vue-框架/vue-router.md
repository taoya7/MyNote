# Vue-Router

## 第一个单页应用

> 导入

```
<script src="https://cdn.bootcss.com/vue-router/3.0.6/vue-router.common.js"></script>
```

> 使用

**HTML**

```html
<div id="app">
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <div class="columns box is-mobile">
        <div class="column"><router-link to="/home">Home</router-link></div>
        <div class="column"><router-link to="/more">More</router-link></div>
        <div class="column"><router-link to="/work">Work</router-link></div>
        <div class="column"><router-link to="/about">About</router-link></div>
    </div>


    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <!--<router-view></router-view>-->
    <router-view>

    </router-view>

</div>
```


**JavaScript**

1. 创建组件


```html
<template id="home">
    <div class="hero is-medium is-info">
        <div class="hero-body">
            <h1 class="title">Home</h1>
        </div>
    </div>
</template>
<template id="More">
    <div class="hero is-medium is-info">
        <div class="hero-body">
            <h1 class="title">More</h1>
        </div>
    </div>
</template>
<template id="Work">
    <div class="hero is-medium is-info">
        <div class="hero-body">
            <h1 class="title">Work</h1>
        </div>
    </div>
</template>
<template id="About">
    <div class="hero is-medium is-info">
        <div class="hero-body">
            <h1 class="title">About</h1>
        </div>
    </div>
</template>


//Step1:创建组件
const Home = Vue.extend({
		template:"#Home"
});
const More = Vue.extend({
		template:"#More"
});
const Work = Vue.extend({
		template:"#Work"
});
const About = Vue.extend({
		template:"#About"
});
```

2. 定义路由

每条路由以key-value 形式存在

```
//Step2:定义路由
const routes = [
		{path:'/home', component:Home,},
		{path:'/more', component:More,},
		{path:'/work', component:Work,},
		{path:'/about', component:About,},
];
```

3. 创建路由实例

```
//Step3:创建路由实例
const router = new VueRouter({
		routes // （缩写）相当于 routes: routes
});
```

4. 创建Vue实例 挂载

```
 //Step4:创建Vue实例并挂载
var app = new Vue({
		router
}).$mount('#app');
```


![](image/1557206449085.gif)

**执行过程**

当点击`v-link`指令元素，vue-router 首先会查找`v-link`指令的路由映射，然后根据路由映射找到匹配组件，最后将组件渲染`<router-view>`标签

## 嵌套路由

实现嵌套路由两个要点

1. 组件内部使用`<router-view>`标签
2. 路由器对象给组件定义子路由

```
const routes = [
		{    path:'/home', 
		    component:Home,
			  subRoutes:{
						path:'',//子组件
						component:"", //子组件
				}
		},
		{path:'/more', component:More,},
		{path:'/work', component:Work,},
		{path:'/about', component:About,},
];
```




