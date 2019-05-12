# <center>Pygal</center>

> ### 折线图

```python
import pygal

radar_chart = pygal.XY() #设置散点图
radar_chart.title = 'Tashi' #设置图标标题
"""
参数1
    - 添加的种类
参数2
    - 坐标(x,y)用列表包起来
"""

radar_chart.add('A', [(0, 0), (.1, .2), (.3, .1), (.5, 1), (.8, .6), (1, 1.08), (1.3, 1.1), (2, 3.23), (2.43, 2)])
radar_chart.add('B', [(.1, .15), (.12, .23), (.4, .3), (.6, .4), (.21, .21), (.5, .3), (.6, .8), (.7, .8)])
radar_chart.add('C', [(.05, .01), (.13, .02), (1.5, 1.7), (1.52, 1.6), (1.8, 1.63), (1.5, 1.82), (1.7, 1.23), (2.1, 2.23), (2.3, 1.98)])
radar_chart.add('D', [(.05, .01), (.13, .02), (1.5, 1.7), (1.52, 1.6), (1.8, 1.63), (1.5, 1.82), (1.7, 1.23), (2.1, 2.23), (2.3, 1.98)])

#保存
radar_chart.render_to_file('die_visual.svg')
```

![](imgs/4.png)
- pygal.XY(stroke=False)即可设置散点图，图线不连



> ### 散点图

```python
import pygal

radar_chart = pygal.XY(stroke=False) #设置散点图
radar_chart.title = 'Tashi' #设置图标标题

"""
参数1
    - 添加的种类
参数2
    - 坐标(x,y)用列表包起来
"""
radar_chart.add('A', [(0, 0), (.1, .2), (.3, .1), (.5, 1), (.8, .6), (1, 1.08), (1.3, 1.1), (2, 3.23), (2.43, 2)])
radar_chart.add('B', [(.1, .15), (.12, .23), (.4, .3), (.6, .4), (.21, .21), (.5, .3), (.6, .8), (.7, .8)])
radar_chart.add('C', [(.05, .01), (.13, .02), (1.5, 1.7), (1.52, 1.6), (1.8, 1.63), (1.5, 1.82), (1.7, 1.23), (2.1, 2.23), (2.3, 1.98)])
radar_chart.add('D', [(.05, .01), (.13, .02), (1.5, 1.7), (1.52, 1.6), (1.8, 1.63), (1.5, 1.82), (1.7, 1.23), (2.1, 2.23), (2.3, 1.98)])

#保存
radar_chart.render_to_file('die_visual.svg')
```
![](imgs/1.png)

> ### 条形图

```python
import pygal


# 条形图
hist = pygal.Bar()
hist.title = 'Tashi' #设置标题
hist.x_labels = [1, 2, 3, 4, 5, 6] #X坐标
hist.y_labels = [0, 30, 60, 90, 120, 150] #Y坐标
hist.x_title = 'Result' #X描述
hist.y_title = 'Frequency of Result' #Y描述

# 设置数据
data = [158, 163, 147, 189, 179, 164]
hist.add('D6', data) #添加

#保存数据
hist.render_to_file('bar_chart.svg')
```

![](imgs/2.png)

> ### 雷达图

```python
import pygal

hist = pygal.Radar()

hist.title = 'Tashi' #设置标题

#设置X,Y阈值
hist.x_labels = ['威力','灵敏度','子弹书','敏捷','咕噜']
hist.y_labels = [1,2,3,4,5,6,7,8,9,10]

#X、Y描述
hist.x_title = 'X描述'
hist.y_title = 'Y描述'

#添加数据
hist.add('啊哈',[1,2,3,4,5,6,7])
hist.add('咕噜',[5,8,3,4,5,6,7])

hist.render_to_file('bar_chart.svg')
```

![](imgs/3.png)


### 阅览

- render_to_file() 保存SVG文件

- render_in_browser() 浏览器在线阅览


### 声明对象

- hist = pygal.Bar(style=**,x_label_rotation=角度，show_legend=False/True)
	- style为样式
	- x_label_rotation旋转角度（顺时针旋转）
		- 显示的是X描述的旋转角度
	- show_legend是否显示图例

### 配置

- title_font_size=66 设置标题字体大小

- label_font_size=14 副标题字体大小

- major_label_font_size=18 主标签字体大小

- show_y_guides=False 是否展示图标中水平线

- width=1000  自定义图表宽度
