# ORM模型  （Object Relational Mapping）

Object Relational Mapping.对象关系映射

通过ORM我们可以通过类的方式去操作数据库，而不用原生的SQL语句。通过把**表映射成类**，把**行当作实例**，把**字段当作属性**。

ORM在执行对象操作的时候最终还是会把对应的操作转换为数据库原生语句。

**优点**

- 简单易用、直观、清晰

- 性能损耗小

- 设计灵活

- 移植性强


### 模型映射到数据库当中

- 配置数据库。`settings.py`配置

- app中的`models.py`定义好模型 然后继承`django.db.models`

- app添加到`settings.py`中的`installed_app`中

- 使用`makemigrations`生成迁移脚本文件
```
python manage.py makemigrations
```

- 使用`migrate`将生成的迁移脚本文件映射到数据库
```
python manage.py migrate
```

### 基本操作

创建一个对象，然后调用这个ORM模型的方法

`modles.py`

```python
from django.db import models

# Create your models here.



# 如果将一个普通的类 编程一个可以映射到数据库中的ORM模型
# 继承models.Model
class Book(models.Model):
    '''
    1. id int 自动增
    2. name varchar
    3. author varchar
    '''
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255, null=False)
    author = models.CharField(max_length=255, null=False)


    def __str__(self):
        #<Book:(name,author)>
        return "<Book:({name},{author})>".format(name=self.name,author=self.author)
```


`views.py`

```python
from django.shortcuts import render
from .models import Book #从模块中导入类
from django.http import HttpResponse

def index(request):
    #1. 使用ORM添加一条数据到数据库中
    # book = Book(name="三国演义",author="罗贯中")
    # book.save() #save()方法
    # return HttpResponse("添加成功")


    #2. 查询 根据主键(PK)
    # book = Book.objects.get(pk=1)
    # print(book)
    # return HttpResponse(book)
        #根据其他条件查询
    # books = Book.objects.filter(name="西游记")
    # books = Book.objects.filter(author="吴承恩").first()
    # print(books)
    # return HttpResponse(books)

    #3. 删除数据
    # book = Book.objects.get(pk=1)
    # book.delete()
    # return HttpResponse("删除成功")

    #4. 修改数据
    book = Book.objects.get(pk=2)
    book.name = "未知"
    book.save()
    return HttpResponse("修改成功")
```


### 常用字段

- AutoField(auto_increment)

- BigAutoField

- BooleanField(TinyInt)

- CharField(Varcgar())

- DataField(data)

**Demo**

```python
from django.db import models

class Article(models.Model):
	#若使用自己定义的Field作为主键 primary_key=True
	id = models.BigAutoField(primary_key=True)

```
```
python manage.py makemigrations

python manage.py migrate
```


