package com.example.springboot.service.impl;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zou17
 */
@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    /**
     * 自动装配
     */
    private final AdminMapper adminMapper;
    private final UserMapper userMapper;

    /**
     * 查询所有用户
     *
     * @return List
     */
    @Override
    public List<Admin> list() {
        //返回一个包含所有用户信息的列表
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(), adminPageRequest.getPageSize());
        List<Admin> admin = adminMapper.listByCondition(adminPageRequest);
        return new PageInfo<>(admin);
    }

    @Override
    public void save(Admin obj) {
        adminMapper.save(obj);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        Admin admin = adminMapper.login(request);
        if(admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);
        return loginDTO;
    }

}

