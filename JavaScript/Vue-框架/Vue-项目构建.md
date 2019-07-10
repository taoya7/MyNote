---
title: Vue项目构建
id: 1559716001412
author: linis
date: 2019-06-07 16:27:06
tags: ["Vue"]
categories: JavaScript
---

# Vue项目构建


## 1.单文件组成

一个vue组件 就是项目中.vue结尾的文件。

由三部分组成

- template
- script
- style

```js
<template>
    //这里写布局
</template>
<script>
    export default { 
        name: "App"
    }
</script>
<style scoped>
    //这里写局部样式
</style>
```

**注释**

这里scoped的意思是vue的样式只在本组件生效


## 2.路由

路由三概念

- route 一个路由
- routes 一组路由
- router 管理路由

以及页面展示的

- router-view
- router-link

```js
<router-link to='/home'></router-link>


<router-view></router-view>
```

### 定义route

一条路由俩个部分组成path、component

path指路径，component指组件

```js
//两条路由
const routes = [
  { path: '/home', component: Home },
  { path: '/about', component: About }
]
```

### 路由管理

创建router对路由进行管理，由构造函数 new vueRouter() 创建，接受routes 参数

```js
const router = new VueRouter({
    routes // routes: routes 的简写
})
```

### 组件跟入

```js
new Vue({
    render: h => h(App),
    router,
}).$mount('#app');
```

**Code**

```js
import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'

import goods from './components/goods/goods.vue'

Vue.config.productionTip = false;

Vue.use(Router); //Vue全局使用Router


//配置路由数组
const routes = [
    {
      path:'/goods',
      component:goods,//对应组件模板

    },
];

const router = new Router({
    routes // routes: routes 的简写
});



new Vue({
    render: h => h(App),
    router,
}).$mount('#app');

```


