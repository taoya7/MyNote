#### 接受参数

三种方式

- 完全解耦
- 原生ServletAPI
- 接口注入方式

> 完全解耦

**Demo-获取表单的提交数据**

```
<index.jsp>
<form action="hobby.action">
	用户名：<input type="text" name="username"><br>
	密码：<input type="password" name="password"><br>

	<input type="radio" name="fruit" value="apple" >苹果
	<input type="radio" name="fruit" value="banana" >香蕉
	<input type="radio" name="fruit" value="pear" >梨

	<input type="submit">
</form>

<!--struts.xml-->
<struts>
	<package name="unit1" extends="struts-default">
			<action name="hobby" class="Unit1.HobbyAction" ></action>
	</package>
</struts>
```

```
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import java.util.Map;
import java.util.Set;

public class HobbyAction extends ActionSupport {
    public String execute(){
        System.out.println("接受表单");

        /*接受表单的数据|参数*/
        //获取参数
        ActionContext context = ActionContext.getContext();
        //获取参数
        HttpParameters parameters = context.getParameters();



        //获取单个指定参数
        String username = parameters.get("username").getValue();
        System.out.println(username);

        //获取全部参数
        Set<Map.Entry<String, Parameter>> entries = parameters.entrySet();

        for(Map.Entry<String,Parameter>entry: entries){
            System.out.print(entry.getKey() + "\t\t");
            System.out.print(entry.getValue() + "\n");
        }


        return null;
    }
}
```

##### 往域中提交数据

```
ActionContext context = ActionContext.getContext();

//req
context.put("属性","值");

//session域
context.getSession().put("属性",值");

//application域
context.getAoolication().put("属性","值")
```

```
//提取数据

${属性}
```


> 原生ServletAPI

```
public class HobbyAction extends ActionSupport {
    public String execute(){
        System.out.println("接受表单");

        //获取原生API
        HttpServletRequest request = ServletActionContext.getRequest();

        //获取单个参数
        String username = request.getParameter("username");
        System.out.println(username);
    
        //获取多个参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String,String[]>entry : parameterMap.entrySet()){
            System.out.println(entry.getKey() + "\t\t"+ Arrays.toString(entry.getValue()));
        }

        //域中存数据
        request.setAttribute("", "");
        request.getSession().setAttribute("", "");
        ServletActionContext.getServletContext().setAttribute("", "");

        return null;
    }
}
```


> 接口注入方式


```
public class HobbyAction implements ServletRequestAware, ServletContextAware {
    private HttpServletRequest httpServletRequest;
    private ServletContext servletContext; //获取context
    public String execute(){
        System.out.println("接受表单");

			//Main_Code

        return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
```

##### 配置全局结果页

先去自己的action找结果页，如果没有再到全局结果页

```xml
<global-results>
	<result name=" " >/login.jsp</result>
</global-results>
```

#### Struts接受参数

三种方式

**提供属性set方法方式.**

例如：一个学生信息表有姓名、年龄、工作、学校字段 。提供set方法会自动接受参数并且转换类型

```
public class HobbyAction extends ActionSupport {

    private String name;
    private Integer age;
    private String job;
    private String school;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSchool(String school) {
        this.school = school;
    }



    public String execute(){
        System.out.println("接受表单");

        System.out.println(name);
        System.out.println(age);
        System.out.println(job);
        System.out.println(school);

        return null;
    }
}
```


**domain属性接受参数**

1. 创建一个接受参数的模型
2. Action当中提供一个对象属性，提供对象属性的get|set方法
3. JSP页面需要带上Action当中对象属性的名称

```
/*建立模型*/

public class Student {
    private String name;
    private Integer age;
    private String job;
    private String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}

```

```
/*Action*/
public class HobbyAction extends ActionSupport {
    private Student stu;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public String execute(){
        System.out.println("接受表单");

        System.out.println(stu);


        return null;
    }
}
```

```
<!--index.jsp-->
<form action="hobby.action">
	姓名：<input type="text" name="stu.name"><br>
	年龄：<input type="text" name="stu.age"><br>
	职业：<input type="text" name="stu.job"><br>
	学校：<input type="text" name="stu.school"><br>

	<input type="submit">
</form>
```


**模型驱动**

1. 创建模型
2. Action实现接口ModelDriver<T>
3. 实现接口方法getModel()
4. 创建模型对象在接口方法中返回

	
```
public class HobbyAction extends ActionSupport implements ModelDriven<Student> {
    private Student stu = new Student();

    @Override
    public Student getModel() {
        return stu;
    }

    /*模型驱动*/
    public String execute(){
        System.out.println("接受表单");

        System.out.println(stu);

        return null;
    }
}
```

```jsp
	<form action="hobby.action">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    职业：<input type="text" name="job"><br>
    学校：<input type="text" name="school"><br>

    <input type="submit">
  </form>
```

##### 接收参数错误处理

接收参数如果输入值错误则会引发错误。所以我们需要对错误进行处理。
	
```
全局结果页
	<result name="input">/error.jsp</result>
```

只要有任何一个拦截器在执行过程当中出现错误都会向错误信息当中添加错误信息。
	
如果没有直接到目标action,如果有就跳转到input逻辑视图