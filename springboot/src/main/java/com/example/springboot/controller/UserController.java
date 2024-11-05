package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zou17
 */
@CrossOrigin  // 允许跨域请求
@RestController  // 返回 JSON 数据
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;  // 在 userService字段自动注入 IUserService 接口的实现类


//    @GetMapping("/list")
//    public List<User> list() {
//        return userService.list();  // 调用 IUserService 接口实现类的 listUsers 方法，返回所有用户到前端
//    }                  //这里返回的list列表未进行封装
    @GetMapping("/list")
    public Result list() { //Result表示返回的数据类型
        List<User> users = userService.list();
        return Result.success(users);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) { //Result表示返回的数据类型
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest) {
        return Result.success(userService.page(userPageRequest));
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

}
// 返回所有用户到前端
