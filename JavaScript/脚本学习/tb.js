auto();

function main() {
  console.show();
  showBanana();
  //获取设备信息
  console.log(
    ":屏幕宽度为" + device.width + "   " + "屏幕高度为" + device.height
  );

  launchApp("手机淘宝");
  toast("打开手机淘宝中,请稍等");
  waitForActivity("com.taobao.tao.TBMainActivity");
  sleep(600);
  className("android.view.View")
    .desc("搜索")
    .clickable(true)
    .findOne()
    .click();

  console.log(":打开淘宝成功准备进入活动页...");
  sleep(1500);
  id("searchEdit").waitFor();
  id("searchEdit")
    .findOne()
    .setText("双十一合伙人");
  id("searchbtn").waitFor();
  id("searchbtn")
    .findOne()
    .click();

  waitForActivity("com.taobao.browser.BrowserActivity");

  text("待兑换红包").waitFor();
  console.log(":已进入活动");

  sleep(1500);
  if (text("开心收下").exists()) {
    text("开心收下")
      .findOne()
      .click();
  }
  if (text("收下去盖楼").exists()) {
    text("收下去盖楼")
      .findOne()
      .click();
  }
  sleep(600);

  className("android.widget.Button")
    .text("升级领红包")
    .waitFor();
  var activeBtn = className("android.widget.Button")
    .text("升级领红包")
    .findOne()
    .bounds();
  click(device.width - 150, activeBtn.centerY());
  console.log(":查看任务");
  sleep(1500);

  // 签到
  console.log("---- 检查是否签到 ----");
  if (text("签到").exists()) {
    text("签到")
      .findOne()
      .click();
    sleep(1500);
    console.log("> 签到成功");
  } else {
    console.log("> 已签到过了");
  }
  sleep(1500);
  /* 取消 分享功能 */
  // console.log("---- 检查是否可以分享 ----");
  // if (text("去分享").exists()) {
  //   text("去分享")
  //     .findOne()
  //     .click();
  //   sleep(2000);
  //   if (text("复制链接").exists()) {
  //     getClip();
  //     console.log("> 已将分享链接复制至剪切板");
  //     back();
  //     back();
  //     sleep(800);
  //   }
  // }

  sleep(1000);

  if (text("去浏览").exists()) {
    while (text("去浏览").exists()) {
      text("去浏览")
        .findOne()
        .click();
      sleep(2000);
      console.log("--------------");
      console.log("| 进入会场并浏览   |");

      swipe(
        device.width / 2,
        (4 * device.height) / 5,
        device.width / 2,
        device.height / 5,
        1000
      );
      sleep(2600);
      swipe(
        device.width / 2,
        (4 * device.height) / 5,
        device.width / 2,
        device.height / 5,
        1000
      );
      sleep(13000);
      if (
        className("android.widget.LinearLayout")
          .desc("会员码")
          .exists()
      ) {
        console.log("| 判断当前的页面为首页|");
        sleep(600);
        className("android.view.View")
          .desc("搜索")
          .clickable(true)
          .findOne()
          .click();

        console.log(":重新准备进入活动页...");
        sleep(1500);
        id("searchEdit").waitFor();
        id("searchEdit")
          .findOne()
          .setText("双十一合伙人");
        id("searchbtn").waitFor();
        id("searchbtn")
          .findOne()
          .click();

        waitForActivity("com.taobao.browser.BrowserActivity");

        text("待兑换红包").waitFor();
        console.log(":已进入活动");
        className("android.widget.Button")
          .text("升级领红包")
          .waitFor();
        var activeBtn = className("android.widget.Button")
          .text("升级领红包")
          .findOne()
          .bounds();
        click(device.width - 150, activeBtn.centerY());
        console.log(":查看任务");
        sleep(1500);
      } else {
        back();
      }
      console.log("| 退出会场并关闭   |");
      console.log("--------------");

      sleep(2000);
    }
    console.log("---- 所有浏览任务已经完成 ----");
    sleep(600);
  }
  if (text("去签到").exists() && textContains("天猫农场")) {
    console.log(": 可以进入农场签到");
  }
}

function showBanana() {
  console.info("---------------------------");
  console.info(" _/  _/ ");
  console.info("_/      _/_/_/ ");
  console.info("_/  _/  _/    _/");
  console.info("_/  _/  _/    _/");
  console.info("_/  _/  _/    _/   v1.0.0");
  console.info();
  console.info("温馨提示");
  console.info("· 自动操作过程中 请勿手动返回");
  console.info("---------------------------");
}

main();
