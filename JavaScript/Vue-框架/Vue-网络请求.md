# Axios

基于http客户端的promise，面向浏览器和nodejs

> 安装

```bash
npm install axios --save
npm install qs --save
npm install vue-axios --save
```

Qs包装data数据

> 使用

- 方式一

```js
import axios from "axios";
import QS from "qs";


//axios 改写为 Vue 的原型属性
Vue.prototype.$ajax = axios
Vue.prototype.$qs = QS;
```

- 方式二-通过vue-axios来全局使用axios,组件中也是通过this.axios来拿到axios

```javascript
import axios from "axios";
import Vueaxios from "vue-axios";

Vue.use(Vueaxios, axios);
```

### 请求

```javascript
this.axios.get("url",{"params"}).then(function (res) {

                console.log( (res.data.name));
                this.obj = res.data.name;
            }).catch(function () {
                //Do error things
            })

// 组件中使用post方法
this.axios.post(url, {a: 1, b: 2})
  .then(res => {
    // 成功回调
  }, res => {
    // 错误回调
  })
```

### 拦截器

- 请求拦截器（interceptors.requst）是指可以拦截每次或指定HTTP请求，并可修改配置项

- 响应拦截器（interceptors.response）可以在每次HTTP请求后拦截住每次或指定HTTP请求，并可修改返回结果项

```javascript
//添加请求拦截器
axios.interceptors.request.use(function (config) {
    //发送成功之前做什么
    return config;
}, function (error) {
    //对请求错误做些什么
    return Promise.reject(error);
});
```

```javascript
//添加响应拦截器
axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});
```

![](C:\Users\Administrator\AppData\Roaming\marktext\images\2019-09-10-18-35-30-image.png)

- 移除拦截器
  
  ```javascript
  var myInterceptor = axios.interceptors.request.use(function () {
  
  });
  
  axios.interceptors.request.eject(myInterceptor);
  ```

## 请求配置

```javascript
// 请求的服务器URL
url

//请求方法
method

//自定义请求头
headers

//请求参数
params

//请求超时时间
timeout

//跨域请求
withCredentials：false|true
```

## 响应结构

```javascript
{
  // `data` 由服务器提供的响应
  data: {},

  // `status` 来自服务器响应的 HTTP 状态码
  status: 200,

  // `statusText` 来自服务器响应的 HTTP 状态信息
  statusText: 'OK',

  // `headers` 服务器响应的头
  headers: {},

  // `config` 是为请求提供的配置信息
  config: {}
}
```

## 全局配置

```javascript
axios.defaults.baseURL = 'https://api.example.com';
axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
```

[中文文档](https://www.kancloud.cn/yunye/axios/234845)

## Demo

请求占座电影

```javascript
this.axios({
                    url: "https://m.maizuo.com/gateway?cityId=360100&pageNum=1&pageSize=10&type=1&k=6067035",
                    method: "get",
                    headers:{
                        'X-Client-Info': '{"a":"3000","ch":"1002","v":"5.0.4","e":"156809993910475425235173"}',
                        'X-Host': 'mall.film-ticket.film.list'
                    }
                }).then(res=>{
                    console.log(res.data);
                    this.movie = res.data.data.films;
                }).catch(res=>{
                    alert(res);
                });
```


