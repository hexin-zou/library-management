package com.example.springboot.mapper;

import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //    @Select ("select * from user")  // 查询所有用户；user 为数据库中的 user表
    List<User> list();

    //返回一个 User 对象的列表
    List<User> listByCondition(UserPageRequest userPageRequest);


    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}
//这个接口用于定义数据库操作，即查询所有用户的方法。
