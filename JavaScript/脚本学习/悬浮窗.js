var w = floaty.rawWindow(
    <frame gravity="center">
        <text id="text">悬浮文字</text>
    </frame>
);

w.setPosition(500, 500);


setTimeout(()=>{
    w.close();
}, 5000);