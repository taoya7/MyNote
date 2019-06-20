# Appium

Appium是一个跨平台移动端自动化的测试工具。可以模拟App内部的各种操作，点击、滑动、文本等等



## 准备工作

1. 安装Android开发环境
2. Python版本



## 启动



```bash
adb devices -l

37e7d8e9               device product:R9sk model:OPPO_R9sk device:R9sk transport_id:1
```



**注释**

1. model 是设备的名称

打开Appium打开启动会话



![](E:\Tashi\Desktop\Learning\工具\image\im1.png)

添加名称与值

1. platformName 平台名称 Android|IOS
2. deviceName 设备名称 手机的具体类型
3. appPackage App程序包名
4. appActivity 入口Activity名以`.`开头

```json
{
  "platformName": "Android",
  "deviceName": "R9sk",
  "appPackage": "com.tencent.mm",
  "appActivity": ".ui.LanuncherUI"
}
```



