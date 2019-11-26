"ui";

ui.layout(
  <vertical>
    <appbar>
      <toolbar title="多线程示例" />
    </appbar>

    <button id="start" text="开始运行任务" />
  </vertical>
);

ui.start.on("click", function() {
  //Main
  main();
});

function main() {
  // 这里写脚本的主逻辑
  threads.start(function() {
    auto();
    setScreenMetrics(1080, 2340);
    toast("Main");
    exit();
  });
}
