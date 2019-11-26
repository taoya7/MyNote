importPackage(java.lang)
importPackage(java.io)


var width = device.width;
var height = device.height;
var model = device.model;
var product = device.product;
var hardware = device.hardware;
var high = device.getBrightness(); // 屏幕亮度

//toast("分辨率："+width+" * "+ height+"\n"+"设备型号："+model)

toast(high)





