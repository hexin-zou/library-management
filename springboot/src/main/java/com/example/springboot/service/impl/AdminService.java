package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void save(Admin admin) {
        Date date = new Date();
        admin.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        adminMapper.save(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        Date date = new Date();
        admin.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        admin.setUpdatetime(new Date());
        adminMapper.update(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

}

