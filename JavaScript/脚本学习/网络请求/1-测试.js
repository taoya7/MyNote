toast("测试完成");

let url = "www.itaolaity.com/ex/Test.js";
var res = http.post(url, {
  headers: {
    "User-Agent":
      "Mozilla/5.0(Macintosh;IntelMacOSX10_7_0)AppleWebKit/535.11(KHTML,likeGecko)Chrome/17.0.963.56Safari/535.11"
  }
});

if (res.statusCode == 200) {
  console.show();
  log(res.body.string());
  log(res);
  log(res.url, typeof res.url);
} else {
  toast("请求失败" + res.statusMessage);
}
