Str具有不可改变性。所有的操作基于新的字符串并不会改变以前的字符串。

> toString()与valueOf()返回的都是自己

```javascript
let str = "Tashi";
console.log(str.toString());
console.log(str.valueOf());
```

> charAt(index) 返回在指定位置的字符

```javascript
let str = "0123456";
console.log(str.charAt(3));
> 3
```

> charCodeAt(index): 返回指定位置的字符的 Unicode 编码

```javascript
let a = 'a';
console.log(a.charCodeAt(0)); //97
```

> fromCharCode(n1, n2, ..., nX): 将 Unicode 编码转为一个字符 接受一个或多个 Unicode 值，即要创建的字符串中的字符的 Unicode 编码

```javascript
let BCDEF = String.fromCharCode(66,67,68,69,70);
console.log(BCDEF); //BCDEF
let B = String.fromCharCode(66);
console.log(B); //B
````

> string.indexOf(searchvalue,start): 返回某个指定的字符串值在字符串中首次出现的位置。如果没有找到匹配的字符串则返回 -1

```javascript
let key = "key";
let str =  "Do you find the key";
console.log(str.indexOf(key));//16
```

> string.lastIndexOf(searchvalue,start): 返回一个指定的字符串值最后出现的位置

> string.concat(string1, string2, ..., stringX): concat() 方法用于连接两个或多个字符串

该方法没有改变原有字符串，但是会返回连接两个或多个字符串新字符串


> string.match(regexp): 在字符串内检索指定的值，或找到一个或多个正则表达式的匹配。

```javascript
let str = "This is my Phone number 123456789";
console.log(str.match("....")); //This
console.log(str.match("[0-9]{8}"));//12345678
console.log(str.match("[a-z]{3}")); //his
```

> string.replace(searchvalue,newvalue): 在字符串中用一些字符替换另一些字符，或替换一个与正则表达式匹配的子串


> search(searchvalue): 检索字符串中指定的子字符串，或检索与正则表达式相匹配的子字符串。如果没有找到任何匹配的子串，则返回 -1

> string.slice(start,end): 提取字符串的某个部分，并以新的字符串返回被提取的部分


> string.split(separator,limit): 把一个字符串分割成字符串数组。如果把空字符串 ("") 用作 separator，那么 stringObject 中的每个字符之间都会被分割

> string.substr(start,length): 在字符串中抽取从 开始 下标开始的指定数目的字符

> string.substring(from, to): 用于提取字符串中介于两个指定下标之间的字符。返回的子串包括 开始 处的字符，但不包括 结束 处的字符


> toLowerCase(): 把字符串转换为小写

> toUpperCase(): 把字符串转换为大写


> trim(): 去除字符串两边的空白

```javascript
let str = "   abc   ";
console.log(str.trim());//abc
```

> includes(searchvalue, start): 用来判断一个字符串是否包含一个指定的值，如果是返回 true，否则false

```javascript
let str = "hello my friend";
console.log(str.includes("Tony")); //False
```

> startsWith(searchvalue, start): 表示是否字符(串)位于string的头部位置, 如果是返回 true，否则false

> endsWith(searchvalue, start): 表示是否字符(串)位于string的尾部位置, 如果是返回 true，否则false

> repeat(num): 参数num为重复字符串的次数

```javascript
var repeatStr = 'abc'
var repeatStrEle = repeatStr.repeat(2) //重复两次
console.log(repeatStrEle)
// -> abcabc
```

> ${}: 模板字符串

```javascript
let name = 'Tony';
let temp = `Hi ${name}`;
console.log(temp); //Hi Tony
```

注意下面的标点`

