
> 抽取工具类

```java
//Util
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;
		/*只需加载一次所以static*/
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

---

**保存**

```java
@org.junit.Test
    public void saveDemo()
    {
        Session session = HibernateUtil.openSession();

        Students su = new Students();
        su.setName("Tahsi");
        su.setAge(6654);
        su.setId(5);

        //保存
        session.save(su);

        //释放
        session.close();
    }
```

**查询**

```java
@org.junit.Test
    public void queryOne(){
        Session session = HibernateUtil.openSession();

        // 查询一条
        Students students = session.get(Students.class, 11); //ID为1的同学消息
        System.out.println(students);

        session.close();
    }
```

![](image/1556028998966.png)

```java
@org.junit.Test
    public void Test7(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();

        //HQL
        Query query = session.createQuery("from  Demo1.Students" );

        //Query以list方法访问查询的全部实例
        List<Students> list = query.list();
        //遍历查询的全部结果
        for(Students s: list){
            System.out.println(s);
        }

        //提交事务
        transaction.commit();

        session.close();
    }
```

![](image/1556029702994.png)

```java
/*原生SQL*/

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


**修改**

```java
@org.junit.Test
    public void updateDemo(){
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

```java
@org.junit.Test
    public void Test4(){
        Session session = HibernateUtil.openSession();
        //开启实务
        Transaction transaction = session.beginTransaction();


        //更新操作
        Students s = session.get(Students.class, 1);
        s.setName("tao 666");
        session.update(s);


        //提交事务
        transaction.commit();

        session.close();
    }
```

**删除**

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

**保存或更新**

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
