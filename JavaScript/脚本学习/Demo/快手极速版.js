// 开启控制台
console.show();

function main() {
  var width = device.width;
  var height = device.height;
  var count = 1;
  log(width, " * ", height);
  launchApp("快手极速版");
  sleep(4000);

  // 如果在主页
  while (id("like_icon").exists()) {
    if (text("我知道了").exists()) {
      var mesKnow = text("我知道了")
        .findOne()
        .click();
    }
    if (text("立即邀请").exists()) {
      back();
    }
    sleep(4500);
    swipe(width / 2, (height * 4) / 5, width / 2, 0, 300);

    console.log("第 " + count + "Video.");
    count++;
    sleep(600);
  }
}

main();
