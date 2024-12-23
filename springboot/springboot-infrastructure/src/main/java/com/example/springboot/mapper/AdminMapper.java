package com.example.springboot.mapper;

import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.request.PasswordRequest;
import com.example.springboot.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);


    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin admin);

    void deleteById(Integer id);

//    Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest request);

    Admin getByUsernameAndPassword(LoginRequest request);

    Admin getByUsername(String username);
}
