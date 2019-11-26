### 第一个程序

```c
main(List<String> args) {
  print('Hello World');
}
```

- 入口是`main` 函数
- 没有返回值

### 变量

**声明方式**

```shell
变量类型 名称 = 值;
```

**检查变量类型**

```shell
变量.runtimeType
```

#### 类型推导

- `var`
- `dynamic`
- `const`
- `final`

var

```c
main(){
  var name = "Linis";
  print(name);
  name = "Jack";
  print(name);
  //name = 123; // Error 
}
```

dynamic

```c
main(){
  Map<String, dynamic> data = new Map();
  data["Name"] = "Tashi";
  data["age"] = 15;

  print(data); // {Name: Tashi, age: 15}
}
```

final 

```c
final name = "Jack";
name = "Amy"; // Error  
```

final和const都是定义常量的。定义之后不可以修改，而且声明的时候就要赋值

- const赋值，赋值的内容必须在编译期间就要确定下来
- final在赋值时，可以动态获取。

```c
String getName() {
  return 'coderwhy';
}

main(List<String> args) {
  const name = getName(); // 错误的做法, 因为要执行函数才能获取到值
  final name = getName(); // 正确的做法
}
```

#### 集合

- List
- Set
- Map



##### List

**定义**

```shell
// 方式一
var list = [1,2,3,4,5]

// 方式二
List<int> nums = [1,2,3,4,5];
```

##### Set

元素时无序的且不可重复

**定义**

```c
// 方式一
void main(){
  var sets = {1,2,3,1};
  print(sets); // 1,2,3
}


```

##### Map

**定义**

```c
// 方式一 - 直接赋值
var data = {
    "name":"Tashi",
    "age":18
};

print(data); // {name: Tashi, age: 18}


// 方式二 
main (){
  Map<String,dynamic> data =new Map();
  data["name"] = "Tashi";
  data["age"] = 18;
  print(data);
}
```

**属性**

```c
main (){
  Map<String,dynamic> data =new Map();
  data["name"] = "Tashi";
  data["age"] = 18;
  print(data);

  print(data.runtimeType); //_InternalLinkedHashMap<String, dynamic>
  print(data.length); // 2
  print(data.isEmpty); // 是否为空
  print(data.isNotEmpty); // 是否不为空
  print(data.keys); // 所有的key
  print(data.values); // 所有的value
  print(data.containsKey("name")); // 是否包含某个key
    
    print(data.values.elementAt(0)); // 获取指定
}
```

**方法**

- `clear()` 清空map
- `addAll()` 整合到另外一个map
- `addEntries()` 合并两个map如果key重复 ，被合并的map的value覆盖前者

**增删改查**

```c
main (){
  Map<String,dynamic> data =new Map();

  // 添加操作
  data["name"] = "Tashi";
  data["age"] = 18;
  
  // 修改操作
  data["age"] = 22;

  int res = data.update("age", (value)=>(
    value*2
  ));

  print(data);// {name: Tashi, age: 44}

  // 删除操作
  data.remove("age");

  data.removeWhere((key,value)=>(value=="Tashi"));

  print(data); // {}

  // 是否包含key
  print(data.containsKey("name"));

  // 是否包含value
  print(data.containsValue("Tashi"));

}
```

**遍历**

注意：遍历的时候添加或删除key都会报错

简单遍历

```c
main(){
  Map<String,dynamic> data =new Map();

  // 添加操作
  data["name"] = "Tashi";
  data["age"] = 18;


  // 遍历
  data.forEach((key, value){
    print("${key} - ${value}");
  });
//name - Tashi
//age - 18 
}
```

#### 整形

- `int`
- `double`

```dart
void main(){
  int num1 = 18;
  int num2 = 0x15;
  print(num1);
  print(num1.runtimeType); // 18 int

  double num3 = 12.66;
  print(num3.runtimeType); // double
}
```

#### 布尔类型

- `bool`

#### 转换

`int.parse(str)`

`double.parse(str)`

```c
var num4 = int.parse("111");
print(num4.runtimeType); // int
```





### 函数

**定义方式**

```shell
void fun(){
	// Main Code
	return 返回值;
}
```

> 箭头函数

```c
main(){
   int add(num1, num2) => num1+num2;

   print(add(5,3)); // 8
}
```

#### 参数

**可选参数**

`{param1, param2}`

**位置可选参数**

`[param1, param2]`



#### 匿名函数

```shell
main (){
  List<String> data = ["红楼梦", "三国演义", "水浒传", "西游记"];


  data.forEach((item){
    print(item);
  }); // 也可以简化 data.forEach((item)=>print(item));
}

```

