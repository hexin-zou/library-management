package com.example.springboot.service;

import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;

import java.util.List;

public interface IUserService {

    List<User> list();

    //该接口定义了一个listUsers()方法，该方法的目的是返回一个包含多个User对象的列表
// 该方法返回一个List<User>对象，表示一个包含多个User对象的列表
// 在接口中，我们定义了该方法的签名，即方法名、参数和返回值类型
// 在实现类中，我们通过调用UserMapper的listUsers()方法来获取用户列表
//返回的是一个List<User>对象，表示一个包含多个User对象的列表，返回到控制层
    Object page(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}






































































































