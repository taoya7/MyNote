```shell
vue add vuex //安装
```

查看store.js

```js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    
  },
  mutations: {

  },
  actions: {

  }
})
```

**Demo**

store.js

```js
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      count:0, //添加数据
  },
  mutations: {
    //添加与减少操作
      Increment(state){
        state.count++;
      },
      Decrease(state){
        state.count--;
      }

  },
  actions: {

  }
})
```

Counter.vue

```vue
<template>
    <div>
        <h3>{{count}}</h3>
        <button @click="increment">添加</button>
        <button @click="decrease">减少</button>
    </div>
</template>

<script>
    import {mapMutations} from "vuex";
    export default {
        name: "Counter",
        computed:{
            count(){
                return this.$store.state.count;
            }
        },
        methods:{
            increment(){
                this.$store.commit("Increment");
            },
            decrease(){
                this.$store.commit("Decrease");
            }

        },

    }
</script>

<style scoped>

</style>
```



