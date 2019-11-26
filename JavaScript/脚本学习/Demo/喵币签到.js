auto();
console.show();
var store = new Array();
store[1] = "￥OtoeYrYrZZn￥";
store[2] = "￥tTR8YrYPBDN￥";
store[3] = "￥eUprYrY7NMg￥";
store[4] = "￥LnZeYrYiHM6￥";
store[5] = "￥hxC0YrYiJfT￥";
store[6] = "￥7OUlYrYifm7￥";
store[7] = "￥4XjAYrYixNe￥";
store[8] = "￥ZzlPYrYRd2n￥";
store[9] = "￥yGAbYrYRNcQ￥";
store[10] = "￥ctJMYrY8pEo￥";

store[11] = "￥7ZvIYrYQbGY￥";
store[12] = "￥OxjyYrYjn0a￥";
store[13] = "￥5TVTYrYjsjB￥";
store[14] = "￥KqhqYrYQrCF￥";
store[15] = "￥d2K9YrYQ3Pn￥";
store[16] = "￥LkqsYrYQ9yz￥";
store[17] = "￥omdnYrY9eSK￥";
store[18] = "￥qNSIYrY91x3￥";
store[19] = "￥9StPYrY9nus￥";
store[20] = "￥vB6eYrYkxSj￥";

store[21] = "￥pDJmYrYPeGc￥";
store[22] = "￥2ZJUYrYPTgC￥";
store[23] = "￥n4MtYrYPqCG￥";
store[24] = "￥uTsDYrYP949￥";
store[25] = "￥wcygYrYl4Qs￥";
store[26] = "￥cpn4YrYK6ai￥";
store[27] = "￥x8j8YrYl6AW￥";
store[28] = "￥DgZtYrYlodK￥";
store[29] = "￥yv4pYrYOSok￥";
store[30] = "￥liynYrYpCNP￥";

log("打开淘宝，请稍等...");

for (i = 1; i < store.length; i++) {
  sleep(2000);
  log("店铺 " + i + "号");
  setClip(store[i]); // 剪切板
  log("已复制 " + store[i] + "至剪切板");
  sleep(3000);
  launchApp("手机淘宝");
  log("打开淘宝并准备进入..." + i + "号");
  sleep(1500);

  // 等待弹窗出现
  id("tpd_shop_action").waitFor();
  sleep(1000);
  id("tpd_shop_action")
    .findOne()
    .click();

  //特殊店铺处理
  if ((i == 13) | (i == 14) | (i == 22) | (i == 30)) {
    className("android.view.View")
      .desc("推荐")
      .waitFor();
    var panzixiang = className("android.view.View")
      .desc("推荐")
      .findOne()
      .bounds();
    sleep(6000);
    click(device.width / 2, panzixiang.centerY());
  }

  sleep(1000);

  var LS = descContains("签到领喵币").findOne(3000); // 是否拥有签到领取标记
  if (LS == null) {
    log("这家已签到");
  } else {
    descContains("签到领喵币").waitFor();
    var DP = descContains("签到领喵币")
      .findOne()
      .bounds();
    sleep(1000);
    click(DP.centerX(), DP.centerY());
    // 点击收下中心
    descContains("开心收下").waitFor();
    var GI = desc("开心收下")
      .findOne()
      .bounds();
    sleep(1000);
    click(GI.centerX(), GI.centerY());
    log(i + " 号店铺签到成功");
    log("************");
  }

  sleep(600);
  toast("回到手机主页");
  home();
}
