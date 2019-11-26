var filePath = "www.itaolaity.com/ex/Test.js";

var res = http.get(filePath);

toast(res.body.string());
