
### EL

> 从四大区域当中获取数据

`pageContext` `request` `session` `application`

```jsp

<%
    pageContext.setAttribute("myName", "Tashi");
%>
${pageScope.myName}

<br>

<%
    request.setAttribute("score", 125);
%>
${requestScope.score}

<br>

<%
    session.setAttribute("date", "2000");
%>
${sessionScope.date}
```

**简写**

```jsp
<!-- 范围从小到下 -->
<!-- 依次从pageContext request session application -->
${myName}
${score}
${date}
```

> pageContext

**为什么使用**



> 执行表达式

```
${5>3?">":"<"}
```

> # JSTL

1 引入标准库
