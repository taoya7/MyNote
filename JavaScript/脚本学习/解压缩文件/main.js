toast("Start");

console.show();

let dir = "/sdcard/DCIM";
let zipDir = "/sdcard/DCIM/zip.zip";

$files.remove(zipDir);
$zip.zipDir(dir, zipDir);
