"ui";

ui.layout(
  <frame bg="#f6c89f" w="*" h="*">
    <vertical w="250" h="250" layout_gravity="center">
      <button
        style="Widget.AppCompat.Button.Colored"
        text="获取剪切板内容"
        id="clip"
      />
    </vertical>
  </frame>
);

ui.clip.click(function() {
  toast(getClip());
});
