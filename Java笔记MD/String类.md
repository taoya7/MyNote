### Scanner

1. 常用方法

public int nextInt();     获取一个int类型的值

public String nextLine();  获取一个String类型的值

### String

1. 字符串看成是一个字符串对象

2. 字符串是常量，一旦被赋值，就不能被改变

#### 构造方法

public String();    空构造

public String(bytes[] bytes);把字节数组转成字符串
```
public static void main(String[] args) {
  byte[] arr = {97,98,99};
  String str = new String(arr);
  System.out.println(str);
}

>>> abc
```
public String(byte[] bytes, int index, int length);把字节数组的一部分转换成字符串

public String(char[] value);把字符数组转成字符串
```
public static void main(String[] args) {
    char[] arr = {'1','a','b'};
    String str = new String(arr);
    System.out.println(str);
  }

>>> 1ab
```
publuc String(char[] value, int index, int length);把字符数组的一部分转换成字符串

public String(String original);把字符串常量转成字符串

#### Other
```
String s1 = "abc";
String s2 = "abc";

System.out.println(s1==s2);     //True
System.out.println(s1.equals(s2));  //True
```
