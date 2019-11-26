

> # 注册中心

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-12-43-25-image.png)

配置YML文件

```yml
server:
  port: 8761

eureka:
  instance:
    hostname: eureka-server
  client:
    register-with-eureka: false # 不将自己注册到eureka上
    fetch-registry: false # 不从eureka上获取服务的注册信息
    service-url:
      defaultZone: http://localhost:8761/eureka/

```

配置启动类加入注解

```java
/**
 *  1. 配置yml信息
 *  2. @EnableEurekaServer
 * */
@EnableEurekaServer
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

}
```

打开浏览器`127.0.0.1:8761`

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-13-25-07-image.png)

> # 服务提供者

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-12-45-43-image.png)



配置文件

```yml
server:
  port: 8001

spring:
  application:
    name: eureka-drovider

eureka:
  instance:
    prefer-ip-address: true # 注册服务使用IP地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

同理

新建一个接口

新建一个类继承接口并且添加注解`@Service`

控制层`@Autowired`接口

```java
@RestController
public class IndexController {

    @Autowired
    TestDemo testDemo;

    @GetMapping("")
    public String index(){
        String res = testDemo.sayHello();
        return res;
    }

}
```

当启动的时候在注册中心可以看到

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-14-04-45-image.png)



> # 服务发现

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-12-48-54-image.png)



```yml
server:
  port: 8002

spring:
  application:
    name: customer

eureka:
  instance:
    prefer-ip-address: true # 注册服务使用IP地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/


```

```java
@EnableDiscoveryClient//开启发现服务功能
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

```java
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/get")
    public String home(){
        String res = restTemplate.getForObject("http://EUREKA-DROVIDER/", String.class);
        return "Get: "+res;
    }
}

```

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-14-19-52-image.png)

![](E:\Tashi\Desktop\Learning\A1Static\imgs\2019-10-26-14-20-05-image.png)




