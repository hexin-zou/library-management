package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zou17
 */
@CrossOrigin  // 允许跨域请求
@RestController  // 返回 JSON 数据
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;  // 在 userService字段自动注入 IUserService 接口的实现类


//    @GetMapping("/list")
//    public List<admin> list() {
//        return adminService.list();  // 调用 IUserService 接口实现类的 listUsers 方法，返回所有用户到前端
//    }                  //这里返回的list列表未进行封装
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO loginDTO = adminService.login(request);
        return Result.success(loginDTO);
    }
    @GetMapping("/list")
    public Result list() { //Result表示返回的数据类型
        List<Admin> obj = adminService.list();
        return Result.success(obj);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) { //Result表示返回的数据类型
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest PageRequest) {
        return Result.success(adminService.page(PageRequest));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

}
// 返回所有用户到前端
