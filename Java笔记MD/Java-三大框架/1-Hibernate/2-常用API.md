### #提前做好的准备

**Utils工具类**

因为创建session工厂执行一次

```java
// Util.java
package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;
    static {
        //1. 加载Hibernate核心配置文件
        Configuration configure = new Configuration().configure(); //就去找配置文件

        //2. 创建sessionFactory
        sessionFactory = configure.buildSessionFactory();
    }

    public static Session openSession(){
        //3. 获取session
        Session session = sessionFactory.openSession();
        return session;
    }
}

```



### Session
---

> 保存

`save(Object obj)`

> 查询方法

`get(T.class, id)`

```java
@org.junit.Test
    public void Test2(){
        Session session = HibernateUtil.openSession();

        // 查询一条
        Students students = session.get(Students.class, 1); //ID为1的同学消息
        System.out.println(students);

        session.close();
    }
```


**查询所有**

1. HQL

```JAVA
@org.junit.Test
    public void Test7(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();

        //HQL
        Query query = session.createQuery("from  Demo1.Students" );

        List<Students> list = query.list();
        for(Students s: list){
            System.out.println(s);
        }

        //提交事务
        transaction.commit();

        session.close();
    }
```

2. 原生SQL

```java
@org.junit.Test
    public void Test8(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select * from students");
        List<Object[]> list = sqlQuery.list();
        for(Object[] objects: list){
            System.out.println(Arrays.toString(objects));
        }

        //提交事务
        transaction.commit();

        session.close();
    }
```

> 修改

- 直接创建对象修改
```java
@org.junit.Test
    public void Test3(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();


        //更新操作
        /*
        * 注意 如果没有指定其他的字段 会把其他的字段设置为null
        * */
        Students su = new Students();
        su.setId(1);
        su.setName("李四");
        su.setAge(15);
        session.update(su);

        //提交事务
        transaction.commit();

        session.close();
    }
```
- 修改某一隔字段，不会把其他的字段设置为null
```java

@org.junit.Test
    public void Test4(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();


        //更新操作
        Students s = session.get(Students.class, 1); //查询后修改
        s.setName("tao 666");
        session.update(s);


        //提交事务
        transaction.commit();

        session.close();
    }
```

> 删除

```java
@org.junit.Test
    public void Test5(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();


        //删除操作
        Students s = session.get(Students.class, 1);
        session.delete(s);


        //提交事务
        transaction.commit();

        session.close();
    }
```

> 保存或更新

没有设置id是保存的操作

设置了id是修改的操作， 如果设置的id没有会报错

```java
@org.junit.Test
    public void Test6(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();


       Students s = new Students();
       s.setName("mySQL");
       session.saveOrUpdate(s); 


        //提交事务
        transaction.commit();

        session.close();
    }
```

