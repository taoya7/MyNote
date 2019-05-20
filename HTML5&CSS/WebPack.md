# WebPack

是一个资源加载打包工具


**使用**

```
npm -g 全局安装（C:\Users\Administrator\AppData\Roaming\npm）

npm install <Model Name> 本地安装
```

```
//安装
npm install webpack -g

npm install webpack-cli -g

//使用
npx webpack main.js 
```

**本地安装-不同文件不同版本号**

```
npm init -y

npm install webpack@4.13.5 -D

npx webpack 
```

##### 模块打包工具

分析项目结构，找到JavaScript模块以及其它的一些浏览器不能直接运行的拓展语言（Scss，TypeScript等），并将其转换和打包为合适的格式供浏览器使用


##### 模块配置文件

**创建**

`touch webpack.config.js`

**进行配置**

```js
const path = require('path'); //引入核心模块

module.exports = {
	mode:'production', //development | production
	entry:{
		main:'./src/index.js'
	}, //入口文件
	output:{
		filename:"bundle.js",
		path:path.resolve(__dirname,'dist')
	},

}
```

最后只要在html中引入`./bundle.js`就可以了


## Loader




