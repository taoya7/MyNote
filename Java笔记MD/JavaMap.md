---
title: Java-Map集合
date: 2018-03-21 17:32:26
tags: [Java]
categories: ["Java"]
---


# Map

>1. HashMap

最常用的Map,它根据键的HashCode 值存储数据,根据键可以直接获取它的值，具有很快的访问速度。HashMap最多只允许一条记录的键为Null(多条会覆盖);允许多条记录的值为 Null。非同步的

> 2. TreeMap

能够把它保存的记录根据键(key)排序,默认是按升序排序，也可以指定排序的比较器，当用Iterator 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的

> 3. Hashtable

ey和value的值均不允许为null;它支持线程的同步，即任一时刻只有一个线程能写Hashtable,因此也导致了Hashtale在写入时会比较慢

> 4. LinkedHashMap

保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.在遍历的时候会比HashMap慢。key和value均允许为空，非同步的。

**插入元素**

```java
public static void  main(String[] args){
    HashMap<Character, Integer> map = new HashMap<>();
    map.put('a',6);
    map.put('b', 87);
    System.out.println(map);
}

>>> {a=6, b=87}
```

**获取元素**

```java
Integer num = map.get('b');
```

**移除元素**

```java
map.remove('b');
```

**清空map**

```java
map.clear()
```

**遍历元素**

方式一

```
for(Character key:map.keySet()){
    System.out.println(key+"\t"+map.get(key));
}
```

本质是两个集合

map.keySet()

map.values()

方式二

```java
for(Map.Entry<Character,Integer>entry : map.entrySet()){
    System.out.println(entry.getKey() + "\t" + entry.getValue());
}
```

方式三：迭代器

```java
Iterator<Character> it = map.keySet().iterator();
while(it.hasNext()){
    Character key = it.next();
    System.out.println(key + "\t" + map.get(key));
}
```

方式四：entrySet()

entrySet() 返回此地图中包含的映射的Set视图

```java
Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator();


while(it.hasNext()){
    Map.Entry<Character, Integer> entry = it.next();
    System.out.println(entry.getKey() + "\t" + entry.getValue());
}
```

遍历速度测试

for循环是比较慢的，最快的是entrySet迭代器遍历

### Map排序

使用比较器

返回值-1逆序 返回值1顺序

```java
public static void  main(String[] args){
    TreeMap<Character, Integer> map = new TreeMap<>(new Comparator<Character>() {
        @Override
        public int compare(Character o1, Character o2) {
            return -1;
        }
    });


    map.put('a',6);
    map.put('b', 87);
    map.put('c', 666);

    Iterator<Map.Entry<Character, Integer>> it = map.entrySet().iterator();


    System.out.println(map);
}
```

按照Valule排序

```java
public static void  main(String[] args){
    TreeMap<Character, Integer> map = new TreeMap<>();


    map.put('a',700);
    map.put('b', 400);
    map.put('c', 800);

    //通过ArrayList构造函数把map.entrySet()转换成list
    ArrayList<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
    //通过比较器实现比较排序
    Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
        @Override
        public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    });


    System.out.println(map);
}
```

### 常用API

- clear()
- remove(Object key)
- put(Object key, Object value)
- putAll(Map t) Map复制
- entrySet() 返回 Map 中所包含映射的 Set 视图.可以使用 getKey() 和 getValue() 方法
- keySet()
- values()
- get(Object key)
- containsKey(Object key)
- containsValue(Object value)
- isEmpty()
- size()