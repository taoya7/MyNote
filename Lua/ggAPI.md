[官网文档](<https://gameguardian.net/help/classgg.html#a26f376a1d243ec199b1ae48578d2a303>)

### Alert

显示一个弹出框

**原型**

```lua

int alert	(	string 	text,
    string 	positive = 'ok',
    string 	negative = nil,
    string 	neutral = nil 
)		
```

- 参数

| text     | Text message.                                        |
| -------- | ---------------------------------------------------- |
| positive | Text for positive button. This button return code 1. |
| negative | Text for negative button. This button return code 2. |
| neutral  | Text for neutral button. This button return code 3.  |

- 返回值

**举例**

```lua
res = gg.alert("这是提示信息", "确认","取消","没有选择")

print(res)

if (res == 1) then
    print("你选择了确认按钮")
elseif res == 2 then 
    print("你选择了取消按钮")
else 
    print("没有选择")
end
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701142539.png)

### toast

toast是Android系统中一种消息框类型

**原型**

```lua
nil toast	(	string 	text,
	bool fast = false 
)
```

**举例**

```lua
gg.toast('This is toast')
-- 长时间显示
gg.toast('This is toast', true)
-- 短时间显示
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701143319.png)

### choice

列表选择框

**原型**

```lua
mixed choice	(	
    table 	items,
	string 	selected = nil,
	string 	message = nil 
)	
```

**举例**

```lua

code = gg.choice(
    {
        "苹果",
        "香蕉",
        "梨",
        "桃"
    },2,
    "选择水果"
)
print(code) -- 返回选择的元素的下标
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701153816.png)





### multiChoice

显示“选择多项”对话框

**原型**

```lua
mixed multiChoice	(	table 	items,
	table 	selection = {},
	string 	message = nil 
)		

items:可供选择的项目
selection:指定项中的每个项的选择状态。如果没有找到键，则将不选中该元素
message: 对话框的可选标题
```

**举例**

```lua
res = gg.multiChoice(
    {
        "苹果",
        "香蕉",
        "梨"
    },
    nil,
    "请选择你最喜欢的水果"
)

print(res)
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701143816.png)

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701143836.png)

### prompt

**原型**

```lua
mixed prompt	(	
    table 	prompts,
	table 	defaults = {},
	table 	types = {} 
)	

prompts: 指定每个字段的描述
defaults: 指定每个键的默认值
types: 指定键值的类型
	- number
	- text
	- path
	- file
	- new_file
	- setting
	- speed
	- checkbox
```

**举例**

```lua
code = gg.prompt(
    {"请输入姓名", "请输入年龄","是否使用过"},
    {[3]="true"},
    {[1]='text', [2]='number',[3]='checkbox'}
)
print(code)
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701154848.png)

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701154920.png)

## UI判断

**函数原型**

检查GG的UI是否打开

```lua
boolean isVisible()
```

**举例**

```lua
function Main()
    gg.multiChoice(
        {
            "湘江",
            "撒旦"
        },nil,
        "添加信息"
    )
    flag = -1
end



while true do
  if gg.isVisible(true) then
    flag = 1
    gg.setVisible(false)
  end
  gg.clearResults() -- 清除搜索结果列表
  if flag == 1 then
    Main()
  end
end
```

![](E:\Tashi\Desktop\Learning\Lua\image\QQ截图20190701150438.png)

## 搜索

### searchNumber

根据指定参数，搜索一个数字，在结果列表中如果没有搜索到结果，那么将执行一个新的搜索，否则重新定义搜索。

**函数原型**

```lua
mixed searchNumber	(	
    string 	text,
    int 	type = gg.TYPE_AUTO,
    bool 	encrypted = false,
    int 	sign = gg.SIGN_EQUAL,
    long 	memoryFrom = 0,
    long 	memoryTo = -1 
)		

text: 用于搜索的文本字符串
type: 类型gg.TYPE_AUTO
sign: 标志符号
memoryForm: 搜索内存单元的起始地址
memoryTo: 搜索内存单元的结束地址
```

**函数返回值**

- 成功`true`
- 失败`error`

**举例**

```lua
-- 搜索数值类型
gg.searchNumber("10", gg.TYPE_DWORD) -- 搜索数值类型10

-- 范围搜索
gg.searchNumber('6~100', gg.TYPE_DWORD)

-- 范围联合搜索
gg.searchNumber('6~10; 20-30', gg.TYPE_DWORD) -- [6-10]n[20-30]


```

## 修改

### editAll

修改查询的结果集

**原型**

```lua
mixed editAll	(	
    string 	value,
	int type 
)		

- value 
- type 
```

**示例**

```lua
gg.clearResults(); -- 清除搜索结果
gg.searchNumber('6', gg.TYPE_DWORD) -- 新搜索
gg.getResults(5) -- 获取前5结果集
gg.editAll("8",gg.TYPE_DWORD) -- 将选择的全部修改为8
```

### 修改单独的数值保存

```lua
-- 1. 清除搜索结果列表
gg.clearResults();
-- 2. 新搜索Double类型的1
gg.searchNumber("1", gg.TYPE_DOUBLE);
-- 3. 获取前10结果集
res = gg.getResults(10);

print(res)
-- 4. 设置单个结果的参数
res[1].value = '666';
res[1].freeze = true;

-- 5. 将项目添加到保存的列表中
gg.addListItems(res);
```

`addListItems()`

## 常量类型

- TYPE_AUTO
- TYPE_BYTE
- TYPE_DOUBLE
- TYPE_DWORD
- TYPE_FLOAT
- TYPE_QWORD
- TYPE_WORD
- TYPE_XOR

## 其他

### sleep

使当前执行的脚本休眠(暂时停止执行)指定的毫秒数，这取决于系统计时器和调度程序的精度

**示例**

```lua
gg.sleep(200) -- 200ms
```





