package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zou17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {


    /**
     * 自动装配
     */
    private final AdminMapper adminMapper;

    public static final String DEFAULT_PASS = "123";
    public static final String PASS_SALT = "qingge";

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
        Admin existingAdmin = adminMapper.getByUsername(obj.getUsername());
        if (existingAdmin != null && existingAdmin.getUsername().equals(obj.getUsername())) {
            log.error("用户名已存在");
            throw new ServiceException("用户名已存在");
        }
        obj.setStatus(true);
        if (StrUtil.isBlank(obj.getPassword())) {
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword()));
        //设置md5加密，加盐
        adminMapper.save(obj);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Date());
        adminMapper.update(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        Admin admin = null;
        try {
            admin = adminMapper.getByUsername(request.getUsername());
        } catch (Exception e) {
            log.error("根据用户名{} 查询出错", request.getUsername());
            throw new ServiceException("用户名错误");
        }
        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        request.setPassword(securePass(request.getPassword()));
        if (!admin.getPassword().equals(request.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        if (!admin.isStatus()) {
            throw new ServiceException("用户被禁用");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);

        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword(), 2);
        loginDTO.setToken(token);

        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        // 注意 你要对新的密码进行加密
        request.setNewPass(securePass(request.getNewPass()));
        int count = adminMapper.updatePassword(request);
        if (count <= 0) {
            throw new ServiceException("修改密码失败");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }

}

