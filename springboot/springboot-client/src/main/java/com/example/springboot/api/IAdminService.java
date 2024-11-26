package com.example.springboot.api;

import com.example.springboot.dto.LoginDTO;
import com.example.springboot.request.AdminPageRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.request.PasswordRequest;
import com.example.springboot.model.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    //该接口定义了一个listUsers()方法，该方法的目的是返回一个包含多个User对象的列表
// 该方法返回一个List<User>对象，表示一个包含多个User对象的列表
// 在接口中，我们定义了该方法的签名，即方法名、参数和返回值类型
// 在实现类中，我们通过调用UserMapper的listUsers()方法来获取用户列表
//返回的是一个List<User>对象，表示一个包含多个User对象的列表，返回到控制层
    PageInfo<Admin> page(AdminPageRequest adminPageRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);
}






































































































