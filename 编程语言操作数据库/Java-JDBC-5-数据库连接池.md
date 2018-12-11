# JDBC-连接池

**没有连接池的情况**

每次CRUD操作都要使用数据库的时候，都要创建一个数据库连接对象。普通的JDBC数据库连接使用DriverManager来获取。每次向数据库建立连接的时候都要将 Connection 加载到内存中。然后再验证用户名和密码花费时间0.05s～1s左右。

关键是 每次CRUD操作就向数据库要要一个连接执行完成后再断开连接。这样的方式将会消耗大量的资源和时间。

### 数据库连接池

**池**

保存对象的容器

**连接池**

保存数据库连接对象的容器

**作用**

- 初始化时创建一定数量的对象。需要时直接从池中取出一个空闲对象，

- 用完后并不直接释放掉对象，而是再放到对象池中以方便下一次对象请求可以直接复用。

- 池技术的优势是，可以消除对象创建所带来的延迟，从而提高系统的性能。

**数据库连接池**

- 连接池用于创建和管理数据库连接的缓冲池技术

- 预先在缓冲池中放入一定数量的连接，当需要建立数据库连接时，

- 只需从“缓冲池”中取出一个，使用完毕之后再放回去。

- 我们可以通过设定连接池最大连接数来防止系统无尽的与数据库连接

- 可以通过连接池的管理机制监视数据库的连接的数量﹑使用情况，为系统开发﹑测试及性能调整提供依据。

**优点**

- 减少连接创建时间

- 简化编程模式

- 控制资源的使用

**连接池中的属性**

- 1.连接数据库时需要的4个要素
	```
	驱动名称，数据库地址，用户名，密码
	```

- 2.初始化连接数
	```
	初始化时，连接池当中创建多少个Connection对象
	```
- 3.最大连接数

- 4.最小连接数


- 5.最大的空闲时间
	```
	如果一个获取了连接对象，在指定时间内没有任何动作，就会自动释放链接
	```

- 6.最大等待时间
	```
	在指定时间内，尝试获取连接，如果超出了指定时间，就会提示获取失败
	```


### 连接池的使用

连接池是使用javax.sql.DataSource接口来表示连接池

DataSource和jdbc一样，也是只提供一个接口，由第三方组织来提供

**常见的连接池**

- DBCP
	```
	Spring推荐，Tomcat的数据源使用的就是DBCP
	```

- C3P0

- Druid
	```
	阿里巴巴提供的连接池-德鲁伊-号称最好的连接池，它里面除了这些， 还有更多的功能。
	```


**区别**

- 1.获取方式不同
	- 传统
		```
		Connection conn = DriverManager.getConnection(url.userName,pwd);
		```
	- 连接池
		```
		Conneciton conn = DataSource对象.getConnection();
		```

- 2.释放资源
	- 传统
		```
		直接close conn.close();
		```
	- 连接池
		```
		把数据库连接对象还给连接池，还可以给下一个人来使用
		```
### 使用DBCP

1. 导入相关JAR包

2. 在项目中使用连接池获取连接

```java
import java.sql.Connection;
import org.apache.commons.dbcp.BasicDataSource;
public class Main {
    public static void main(String[] args) throws Exception{

        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java_test";
        String user = "root";
        String pwd = "";

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverName);
        ds.setUsername(user);
        ds.setPassword(pwd);
        ds.setUrl(url);

        Connection conn = ds.getConnection();

        System.out.println(conn);

    }
}
```

> ### 使用配置文件

**什么是配置文件**

资源文件，是以.properties作为扩展名的文件

**配置文件的书写**

命名`db.properties`

内部是以key-value的形式存放

```
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/java_test
username=root
password=
```

**Java读取配置文件**

```java
import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {
    public static void main(String[] args) throws Exception{
        //读取配置文件
        Properties pro = new Properties();

        FileInputStream in = new FileInputStream("src/JDBC/db.properties"); //选择路径

        pro.load(in); //加载
        //读取文件
        System.out.println(pro.getProperty("url")); //打印文档里的url
    }
}
```
### 使用Druid

开源地址

```
https://github.com/alibaba/druid
```

Druid是一个JDBC组件库，包括数据库连接池、SQL Parser等组件。DruidDataSource是最好的数据库连接池。

**使用**

1. 导包

2. Code

```java
public class Main {
    public static void main(String[] args) throws Exception{
         public static DataSource ds = null;

         static {
	        try{
	            /*加载配置文件*/
	            Properties p = new Properties();
	            FileInputStream in = new FileInputStream("src/JDBC/db.properties");
	            p.load(in);
	            /*加载驱动*/
	            ds = DruidDataSourceFactory.createDataSource(p);
       		 }catch (Exception e){
           		 e.printStackTrace();
       		 }
   		 }
   	}

   	 /*获取连接对象*/
    public static Connection getConn(){
        try{
            /*连接数据库*/
            return ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
```