

importPackage(java.lang)
importPackage(java.io)

toast("文件处理")


// var path = "/sdcard/DCIM/";
// var dcims = files.listDir(path);

// var file = new java.io.File("/sdcard/DCIM/main.txt")
// log(file)


// for(let i=0; i<dcims.length; i++){
//    log(dcims[i])
//     // if(dcims[i]){
        
//     // }else{
//     //     log(files.isFile(path+dcims[i]));
//     // }
// }



var path = "/sdcard/DCIM/123.jpg";

var data = files.readBytes(path);

var sb = new java.lang.StringBuilder();
for(var i = 0; i < data.length; i++){
    sb.append(data[i].toString(16));
}
toast(sb.toString());