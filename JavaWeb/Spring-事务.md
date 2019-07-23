

# 事务

如果一组处理的步骤或者全部发生或者一步也不执行，则称该组处理步骤为一个事务。

- 原子性
- 一致性
- 隔离性
- 持久性

回顾一下|JDBC版本的事务处理

```
try{
	//更改JDBC提交方式
	conn.setAutoCommit(false);

	//一连串的SQL操作

	//提交JDBC事务
	conn.commit();

	//恢复JDBC事务
	conn.setAutoCommit(true);
}catch(Exception e){
		//如果发生错误回滚
		conn.rollback();
}
```


## Spring框架的事务处理

Spring提供了两种事务管理方式

1. 编程式的事务管理(Programmatic Transaction Management)
2. 声明式的事务管理(Declarative Transaction Management)

