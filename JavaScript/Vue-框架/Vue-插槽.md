

# 插槽

- 普通插槽

  ```html
  <templatte slot="xxx"> <template>
      
  <templatte v-slot="xxx"> <template>
  ```

- 作用域插槽

  ```html
  <templatte slot="xxx" slot-scope="xxx"> <template>
      
  <templatte v-slot:xxx="props"> <template>
  ```

## slot-实现内容分发

- 实名插槽
- 匿名插槽

> 匿名插槽


`<slot> 标签`

```
<div id="app">
    <show>
        <p>I'm new here</p>
    </show>
</div>

<template id="try">
    <div class="main">
        <h1>Start</h1>
        <!--预留插槽-->
        <slot>替换任何标签， 没有则显示提示的内容</slot>
        <h3>End</h3>
    </div>
</template>

Vue.component("show",{
		template:"#try"
});
```

![](image/1557203141511.png)


> 实名插槽

插槽标签给定属性名字，外面调用的时候给定`slot='name'` 。与匿名插槽不同的是只会显示插槽上给定的内容，其他的内容并不会显示。

```
<div id="app">
    <person>
        path <!--并不会显示-->
        <p slot="ming">小明的座位</p>
        <h1 slot="gang">是小刚啊</h1>
        path
    </person>
</div>

<template id="try">
    <section>
        <slot name="hong">小红</slot><br>
        <slot name="ming">小明</slot><br>
        <slot name="gang">小刚</slot><br>
        <slot name="hui">小惠</slot><br>
    </section>
</template>

//注册全局组件
Vue.component("person",{
		template:"#try"
});
```


![](image/1557203545673.png)