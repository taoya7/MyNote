<style type="text/css">
    h1 {

        font-weight: 400;
    }
    img {
        border-radius: 10px;
        box-shadow: 0 2px 8px rgba(0,0,0,.3);
    }
    body {
        background-color: #FDF6E3;
/*      margin:0;
        outline:none;
        border:none;
        max-width: 100%;*/

    }
    import::before{
        display: inline-block;
        content: "";
        width: 10px;
        height: 10px;
        background-color: red;
        margin-right: 10px;
        border-radius: 50%;
    }
    import {
        font-size:14px;
        font-weight: bold;
        padding:0.55rem;
        border-radius:5px; 
        color:#eb5055;
    }
    .markdown-body blockquote{
        border-left: 4px solid tomato;
    }
</style>


# 事务与批处理

- 转账问题

- 处理事务

- 批处理

- 保存与取出图片到数据库

- 获取主键

## 转账问题


问题版本

```java
import JDBC.DAO_2.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception{
        /*连接*/
        Connection conn = JDBCUtil.getConn();

        //1. 检查张三的账户余额
        String sql = "select * from emp where name = ? and money > ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"zs");
        ps.setInt(2,3000);
        ResultSet res = ps.executeQuery();
        if(! res.next()){
            throw new RuntimeException("余额不足");
        }


        //2. 减少张三的账户
        sql = "update emp set money = money - ? where name= ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1,1000);
        ps.setString(2, "zs");
        ps.executeUpdate();

        //3. 增加李四的账户
        sql = "update emp set money = money+? where name=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 1000);
        ps.setString(2,"ls");
        ps.execute();

        /*关闭连接*/
        JDBCUtil.close(conn, ps, null);
    }
}

```

如果在减少张三的金额的时候出现异常那么李四的账户就不会增加。所以需要处理事务

默认情况下，事务是自动提交的，要手动设置提交

- 关闭自动提交 conn.setAutoCommit(false);

- 提交事务 conn.commit();

- 出现异常时，进行回滚操作 conn.rollback()

- 设置事务的隔离级别 conn.setTransactionIsolation(Connection.TRANSACTION_REPATABLE_READ)

**只有增、册、改才需要事务，查询不需要事务**

```java
import JDBC.DAO_2.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception{
        /*连接*/
        final Connection conn = JDBCUtil.getConn();
        PreparedStatement ps = null;
        try{
            //1. 检查张三的账户余额
            String sql = "select * from emp where name=? and money>?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"zs");
            ps.setInt(2,1000);
            ResultSet res = ps.executeQuery();
            if(! res.next()){
                throw new RuntimeException("余额不足");
            }

            /*设置事务*/
            conn.setAutoCommit(false);

            //2. 减少张三的账户
            sql = "update emp set money = money - ? where name= ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,1000);
            ps.setString(2, "zs");
            ps.executeUpdate();

            //3. 增加李四的账户
            sql = "update emp set money = money+? where name=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2,"ls");
            ps.execute();

            /*提交处理*/
            conn.commit();
        }catch(Exception e){
            e.printStackTrace(); //打印异常信息
            try{
                conn.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        finally{
            /*关闭连接*/
            JDBCUtil.close(conn, ps, null);
        }
    }
}
```


## 处理事务

> 什么是批处理

一次性执行多条Sql语句，允许多条语句一次性提交给数据库批量处理。比单独提交处理要效率高

> 方法

- addBatch(String)  添加需要批处理的sql语句

- executeBatch()  执批处理

> 支持情况

MySQL 需要添加一个rewriteBatchedStatement参数

```java
import JDBC.DAO_2.util.JDBCUtil;
import com.mysql.jdbc.JDBC4PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static  void main(String[] args) throws Exception{
        Connection conn = JDBCUtil.getConn();

        String sql = "insert into emp(name,money) values(?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        for(int i=1; i<500; i++){
            ps.setString(1,"Aha");
            ps.setInt(2,16);
            /*添加批处理*/
            ps.addBatch();
            System.out.println(((JDBC4PreparedStatement)ps).asSql());
        }
        /*执行*/
        ps.executeBatch();
        ps.clearBatch();/*清除缓存*/
        ps.clearParameters();/*清除参数*/

        //关闭
        JDBCUtil.close(conn, ps, null);
    }
}
```

添加参数
```
public static String url = "jdbc:mysql://localhost:3306/java_test?rewriteBatchedStatement";
```


## 存储图片

数据库当中BLOB类型

- 存储图片 音频 视频等多媒体信息

- 二进制流的形式

> 数据类型

- TINYBLOB 255个字节

- BLOB 65535字节

- MEDIUMBLOB 16M

- LONGBLOB 4G


插入图片到数据库当中
```java
Connection conn = JDBCUtil.getConn();
String sql = "insert into student(img) values(?)";

PreparedStatement ps = conn.prepareStatement(sql);


ps.setBlob(1,new FileInputStream("路径"));
ps.executeUpdate();

//关闭
JDBCUtil.close(conn, ps, null);
```


到数据库当中取出图片
```java
Connection conn = JDBCUtil.getConn();
String sql = "select * from student where id=?";

ps.setInt(1,2);
ResultSet res = ps.executeQuery();
if(res.next()){
    //获取BLOB对象
    Blob blob = res.getBlob("img");

    //获取二进制流
    InputStream in = blob.getBinaryStream();

    // 保存
    Files.copy(in, Paths.get("路径"));
}

//关闭
JDBCUtil.close(conn, ps, null);
```


## 获取自动生成的主键

有时候我们插入数据时，要想知道我们生成的主键是多少？

**两种方法**

- Statement语句
    - 创建语句时，设置可以获取主键`st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS)`
    - 通过语句对象的getGeneratedKeys获取主键
    ```java
    //设置可以获取主键
    st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

    //获取自动生成主键
    ResultSet rs = st.getGeneratedKeys();
    if(rs.next()){
        int id = re.getInt();
        System.out.println(id);
    }
    ```
- Preparement语句
    - 在创建语句时，传入参数`Statement.RETURN_GENERATED_KEYS`
    - 通过语句对象的getGeneratedKeys获取主键
    ```java
    //设置可以获取主键
    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    ResultSet rs = st.getGeneratedKeys();
    if(rs.next()){
        int id = re.getInt();
        System.out.println(id);
    }
    ```