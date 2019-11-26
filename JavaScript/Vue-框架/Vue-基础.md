### 属性

- 自定义属性`props`

- 原生属性`attrs`
- 特殊属性`class` `style`

## 事件

- 普通事件

- 修饰符事件

### 修饰符

- `.stop` 阻止冒泡，由内到外
- `.prevent`阻止默认事件
- `.capture` 添加事件侦听器时使用事件捕获模式，由外到内
- `.self ` 只当事件在该元素本身
- `.once` 事件只触发一次
- `.passive` 

```html
<input :value="name" @change="handleChange">


methods:{
	handleChange(e){
		this.$emit("change", e.target.value)
	}
}
```



### 键盘修饰

```js
<input type="text" placeholder="" @keydown="func1($event)">

func1:function (e) {
    console.log(e)
}
```

- `keydown`键盘按下
- `keyup`键盘抬起
- `keypress`

```JS
<input type="text" placeholder="" @keyup.65="function">
```

Vue中，已经为常用的按键设置了别名，这样我们就无需再去匹配`keyCode`，直接使用别名就能监听按键的事件

```js
<input @keyup.enter="function">
```

| 别名    | 实际键值                                   |
| ------- | ------------------------------------------ |
| .delete | delete（删除）/BackSpace（退格）           |
| .tab    | Tab                                        |
| .enter  | Enter（回车）                              |
| .esc    | Esc（退出）                                |
| .space  | Space（空格键）                            |
| .left   | Left（左箭头）                             |
| .up     | Up（上箭头）                               |
| .right  | Right（右箭头）                            |
| .down   | Down（下箭头）                             |
| .ctrl   | Ctrl                                       |
| .alt    | Alt                                        |
| .shift  | Shift                                      |
| .meta   | (window系统下是window键，mac下是command键) |

**组合按键**

```js
<input type="text" placeholder="" @keyup.ctrl.65="function"> //Ctrl+a
```

```html
//当点击+Ctrl的时候会触发函数
<div style="width:100px; height:100px; background: red;" @click.ctrl="f1"></div> 
```

