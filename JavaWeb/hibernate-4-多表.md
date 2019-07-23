
**建立模型**

学生表与成绩表

![](hibernate-duo-biao-wen-ti/1556529801826.png)

> 1. 创建ORM模型

```java
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter@Setter
public class Students {
    private Long stu_id; //主键
    private String stu_name; //姓名
    private Integer stu_age;//年龄
    private Integer stu_gra;//外键

    //一个学生多个成绩
    private Set<Grade> gras = new HashSet();
}

```

```java
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Grade {
    private Integer gra_id;
    private String gra_name;
    private Integer gra_num;
    private Integer stu_gra;

    //某个成绩只对一个学生有效
    private Students stu;

}

```


> 2. 配置映射文件
`grade.hbm.xml`  `students.hbm.xml`

```xml
<!--student.hbm.xml-->
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
    <class name="多表查询.Students" table="students" >
        <!--建立类属性哪一个是主键  还要跟数据库当中主键进行对象-->
        <id name="stu_gra" column="stu_gra" >
            <generator class="native"/>
        </id>
        <!--建立类中的普通属性与数据库当中字段进行关联-->
        <property name="stu_name" column="stu_name" />
        <property name="stu_age" column="stu_age"/>


        <!--一对多的配置-->
        <set name="gras" > <!--属性名称-->
            <key column="stu_link_gra"> <!--外键名称--></key>
            <one-to-many class="多表查询.Grade"></one-to-many>
        </set>


    </class>
</hibernate-mapping>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
    <class name="多表查询.Grade" table="students" >
        <!--建立类属性哪一个是主键  还要跟数据库当中主键进行对象-->
        <id name="gra_id" column="gra_id" >
            <generator class="native"/>
        </id>
        <!--建立类中的普通属性与数据库当中字段进行关联-->
        <property name="gra_name" column="gra_name" />
        <property name="gra_num" column="gra_num"/>

        <many-to-one name="stu" class="多表查询.Students" column="stu_link_gra">

        </many-to-one>
    </class>
</hibernate-mapping>
```

注意：映射不需要添加外键。`<set></set>` 中name属性指的是ORM模型中最后的变量，colmn是数据库中外键的名称，class属性是ORM类的路径。

加载映射文件

```xml
<!--hibernate.cfg.xml-->

<!--映射文件-->
<mapping resource="多表查询/grade.hbm.xml"/>
<mapping resource="多表查询/student.hbm.xml"/>
```

> 3. 操作

**添加**

```java
@Test
    public void t1(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();//开启事务

        /*配置*/
        Students stu1 = new Students();
        Students stu2 = new Students();
        Students stu3 = new Students();
        stu1.setStu_name("小红");
        stu2.setStu_name("小明");
        stu3.setStu_name("小刚");

        Grade gra1 = new Grade();
        Grade gra2 = new Grade();
        Grade gra3 = new Grade();
        gra1.setGra_name("数学"); gra1.setGra_num(90);
        gra2.setGra_name("语文"); gra1.setGra_num(75);
        gra3.setGra_name("英语"); gra1.setGra_num(80);

        stu1.getGras().add(gra1); /*添加*/
        stu2.getGras().add(gra2);
        stu3.getGras().add(gra3);

        session.save(stu1); /*保存*/
        session.save(stu2);
        session.save(stu3);
        session.save(gra1);
        session.save(gra2);
        session.save(gra3);


        transaction.commit();
    }
```
