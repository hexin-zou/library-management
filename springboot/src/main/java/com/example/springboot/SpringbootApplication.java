package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 表示当前类为控制器
@SpringBootApplication
//启动类注解
public class SpringbootApplication {
    //启动类

    public static void main(String[] args) {         //args, 命令行参数，不用管
        SpringApplication.run(SpringbootApplication.class, args);
    }
    //(提供方法用于启动程序) (启动项目，从这个类启动) 并在默认端口上监听请求

    //这样应用程序可以处理 HTTP 请求并提供服务了
    @GetMapping("/")
    // HTTP GET 请求  如果是 post 请求需要使用PostMapping请求
    public String health() {
        return "SUCCESS";
        //返回字符串
    }

}
// http://localhost:9090  服务器在接收请求后返回字符串，构建响应体，发送响应体到客户端
//是SpringBoot应用的入口类
