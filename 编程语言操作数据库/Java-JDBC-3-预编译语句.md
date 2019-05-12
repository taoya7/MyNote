# Statement接口

- **Statement**
    - CallableStatment
    - PreparedStatment

Statment用于对数据库进行通用访问，使用的是静态SQL

PreparedStatement用于预编译模板SQL语句，在运行时接受SQL参数参数

CallableStatement要访问**数据库存储过程**时使用也可以接受运行时输入参数


# 预编译语句

- PreparedStatement 对象使用 ? 作为占位符，即参数标记

- 使用 setXXX( index，value) 方法将值绑定到参数中
    - 每个参数标记是其顺序位置引用，注意 index 从 1 开始


**executeUpdate()**
```java
PreparedStatement ps = null;

/*编写SQL语句*/
String sql = "insert into students values(?,?,?)";
ps = conn.prepareStatement(sql);
ps.setInt(1,stu.getId());
ps.setString(2,stu.getName());
ps.setInt(3,stu.getAge());
ps.executeUpdate();
```

**executeQuery**
```java
/*编写SQL语句*/
String sql = "select * from stu where id=?";

PreparedStatement ps = conn.prepareStatement(sql);
ps.setInt(1,id);
ResultSet res = ps.executeQuery();
if(res.next()){
    Students stu = new Students();
    stu.setId(res.getInt("id"));
    stu.setName(res.getString("name"));
    stu.setAge(res.getInt("age"));

    return stu;
}
```

**Demo**

SQL注入
```java
public static void main(String[] args) throws Exception{
        String use = "'or 1=1 or'";
        String pwd = "1234";
        Connection conn = JDBCUtil.getConn();/*加载*/
        String sql = "select * from user where user='"+use+"' and pwd='"+pwd+"'";
        System.out.println(sql);

        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(sql);
        if(res.next()){
            System.out.println("登陆成功");
        }else{
            System.out.println("登陆失败");
        }
    }

>>> select * from user where user=''or 1=1 or'' and pwd='1234'
```

# 调用存储过程

1. 在数据库当中定义一个存储过程
2. JDBC调用一个参数的存储过程
3. 编写输入参数和输出参数的存储过程
4. JDBC调用两个参数的存储过程

#### 一

给进一个姓名 查询此人信息
```sql
delimiter $$
create procedure getMessage(in name varchar(50))
begin
    select * from emp where ename = name;
end$$
delimiter ;
```

### 二

```java
public class Test{
    public static void main(String[] args) throws Exception{
        Connection conn = JDBCUtil.getConn(); /*加载驱动*/
        CallableStatement cs = conn.prepareCall("{call getMessage(?)}");

        /*设置占位符*/
        cs.setString(1,"Aha");
        /*执行存储过程*/
        ResultSet res = cs.executeQuery();
        if(res.next()){
            System.out.println(res.getString("ename") + res.getInt("emoney"));
        }
    }
}
```

### 三

```sql
delimiter $$
create procedure getMoney(in name varchar(50), out res double)
begin
    select emoney into res from emp where ename = name;
end$$
delimiter ;
```

### 四

```java
public class Test{
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtil.getConn();

        //调用存储过程
        CallableStatement cs = conn.prepareCall("{call getMoney(?,?)}");
        //设置参数
        cs.setString(1,"Aha");
        //执行那个存储过程
        cs.registerOutParameter(2,Types.DOUBLE);
        cs.execute();

        //获取输出参数
        Double money = cs.getDouble(2);
        System.out.prinln(money);
    }
}
```
