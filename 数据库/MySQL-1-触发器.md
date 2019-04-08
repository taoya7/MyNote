# 触发器

触发器(trigger)


> 创建触发器

```mysql
create trigger trigger_name
	before|after trigger_event
		on table_name for each row trigger_stmt
```

1. 需要标识创建触发器的名字`trigger_name`
2. before|after指定了触发器执行的时间 
3. `trigger_event`表示了触发事件有delete、insert、update
4. `trigger_stmt`表示激活触发器执行的语句

```mysql
create trigger tr_check
	after insert
		on students for each row
		begin
			update students set age = 55;
		end

# 添加一个触发器 当在students表进行插入的时候就会执行 `update students set age=55;`的操作
```



> 查看触发器


```
show triggers \G;
```

> 删除触发器

```mysql
drop triggers tri_name;
```