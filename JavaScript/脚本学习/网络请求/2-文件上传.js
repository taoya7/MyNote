upurl = "http://192.168.1.102:8088/upload";

var res = http.postMultipart(upurl, {
  file: open("/sdcard/123.jpeg")
});

toast(res.body.string());
