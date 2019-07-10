---
title: ES6知识点
author: linis
date: 2019-06-08 16:27:06
tags:
    - Vue
    - JavaScript
categories: JavaScript
---

# ES6

1. let与const
2. 变量的解构赋值
3. 箭头函数
4. Map数据结构
5. Moudle语法

![](http://itaolaity.com/20190608230124.png)


**简介**

ECMAScript6(ES6)是javascript语言的下一代标准

## let

1. let命令作用域局限于当前代码块

```js
<script>
    {
        let name = 'Tony';
        console.log(name);//Yes
    }
    console.log(name);//Error
</script>
```
2. 使用let声明的变量作用域不会被提升

以前使用的var声明会发生变量提升，所以输出undefined。let则不会

```js
<script>
    console.log(num); //undefined
    var num = 2;

    console.log(ar);//Error
    let ar = 3;
</script>
```

3. 相同的作用域下不能声明相同的变量

以下命名都会Error

```js
<script>
    //Error
    function f1() {
        let a = 3;
        var a = 4;
    }
    //Error
    function f2(){
        let num = 3;
        let num = 5;
    }
    //Error
    function f3(res){
        let res = 3;
    }
    //Error
    function  f4(res) {
        {
            let res;
        }
    }
</script>
```

4. for循环体中let的父子作用域

## const声明

const声明一个**只读**的常量。一旦声明常量的值就不能改变。

const必须赋初值，因为不能更改

const的作用域与let相同，只在声明的代码块内有效

const保证的基本类型不可更改但是引用类型(数组，对象)指针指向的内容可以更改

```js
<script>
    const Person = {
        name:"Tashi",
        age:"18"
    };
    Person.age = 20;
    console.log(Person); //{name: "Tashi", age: 20}
</script>
```


## 变量的解构赋值

Es6允许按照一定模式从数组和对象中提取值，再对变量赋值，这种操作成为解构

### 数组的解构赋值

```js
<script>
    //Before
    let a = 1;
    let b = 2;
    let c = 3;
    console.log(a,b,c);

    //Now
    let [a,b,c] = [1,2,3];
    console.log(a,b,c);
</script>

如果解构不成功 变量的值为undefined

<script>
    let[x,y] = [1];
    console.log(x,y); //1 undefined
</script>
```

### 对象的解构赋值

**与数组解构的不同**

数组解构数组的元素是按照次序排序的，而对象的属性没有次序,变量必须与属性同名才能解构

```js
<script>
    //Yes
    let{per,dog} = {
        per:{
            name:"Person"
        },
        dog:{
            name:"小白"
        }
    }

    console.log(per,dog);//{name: "Person"} {name: "小白"}


    //Error
    let {t1} = {num1:"1", num2:"2"}
    console.log(t1);//undefined
</script>
```

## 箭头函数

```js
<script>
    const Person = {
        name:"Tony",
        age:18,
        say:function () {
            setInterval(function () {
                console.log(this.name, this.age);
            });
        }
    };
    Person.say();//undefined undefined
</script>
```

**解析**

setInterval() 方法可按照指定的周期（以毫秒计）来调用函数或计算表达式

因为setInterval在全局作用域下执行的，所以this指的是全局windows，windows没有name与age

常规解决办法

```js
<script>
    const Person = {
        name:"Tony",
        age:18,
        say:function () {
            let that = this;
            setInterval(function () {
                console.log(that.name, that.age);
            });
        }
    };
    Person.say();//Tony 18
</script>
```

使用箭头函数解决

```js
<script>
    function Person() {
        let name = "Tony";
        let age = 18;

        setInterval(()=>{
            console.log(name, age);
        },1000)

    };
    let p = new Person();
</script>
```




### 箭头函数定义

```js
//箭头函数
()=> console.log("Hello");

//普通函数
function(){
    console.log("Hello");
}
```

箭头函数相当于匿名函数，并且简化了函数定义。

```js
(参数1,参数2,参数3...) =>{
    函数声明
}

//当只有一个参数的时候圆括号是可选的
x =>{
    x*x;
}
```

### 与普通函数的区别

1. 不绑定this
2. 更简化的代码语法

### 箭头函数不适用的场景

1. 对象的方法
2. 不能作为构造函数
3. 定义原型方法

箭头函数在非方法函数中使用最合适，在方法函数使用需要注意this绑定问题。


## Set数据结构

类似于数组没有重复的元素(唯一)

```js
let set = new Set();
```


> size 获取集合长度


> add() 添加


支持链式调用（本质返回对象）

> delete(value) 删除

> has(key) 是否包含

> clear() 清空集合

## Map数据解构

**创建**

```js
<script>
    const student = new Map(
        [
            ["name","Tony"],
            ["age","18"]
        ],

    );
    console.log(student) //Map(2) {"name" => "Tony", "age" => "18"}
</script>
```

### 常用方法

> 1. size(返回map结构成员总数)


> 2. set与get方法

set方法设置键key对应的键值value，返回整个map结构。如果key存在值会更新，否则生成新的键与值

set方法返回值是当前的map对象

```js
<script>
    const student = new Map(
        [
        ["name","Tony"],
            ["age","18"]
        ],

    );
    student.set("Pets",["Dog","Cat"]).set("name","Aha");

    console.log(student) // Map(3) {"name" => "Aha", "age" => "18", "Pets" => Array(2)}
</script>
```

> 3.has方法

has方法返回一个布尔值，判断某个键是否在当前map对象中

> 4.delete方法

delete方法删除某个键，如果删除成功返回true，否则false

> 5.遍历方法

1. keys() 返回键遍历器
2. value() 返回值遍历器
3. entries() 所有成员遍历
4. forEach() 遍历map成员

```js
<script>
    const student = new Map(
        [
        ["name","Tony"],
            ["age","18"]
        ],

    );
    student.set("Pets",["Dog","Cat"]).set("name","Aha");

    console.log(student.keys()) //MapIterator {"name", "age", "Pets"}

    console.log(student.values())//MapIterator {"Aha", "18", Array(2)}

    student.forEach(function (value, index) {
        console.log(value, index)
    });

    for(let[key,value] of student.entries()){
        console.log(key,value)
    }
</script>
```

## 模块语法

### export,import

**export**

一个模块就是一个独立的文件，文件内部的所有变量外部无法获取，如果希望外部能读取内部的某个变量需要使用export关键字

**import**

import来加载某个模块。

import需要指定的模块变量名字(或者函数名)

**export default**

export default为模块指定默认输出

import语句不需要使用大括号，不适用export default对应的import需要使用大括号