package com.example.springboot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.springboot.request.UserPageRequest;
import com.example.springboot.model.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.api.IUserService;
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
public class UserService implements IUserService {

    /**
     * 自动装配
     */
    private final UserMapper userMapper;

    /**
     * 查询所有用户
     *
     * @return List
     */
    @Override
    public List<User> list() {
        //返回一个包含所有用户信息的列表
        return userMapper.list();
    }

    @Override
    public Object page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(), userPageRequest.getPageSize());
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        user.setAccount(60);
        user.setStatus(true);
        user.setUpdatetime(new Date());
        User existingUser = userMapper.getByUsername(user.getUsername());
        if (existingUser != null && existingUser.getUsername().equals(user.getUsername())) {
            throw new ServiceException("用户名已存在");
        }
        user.setStatus(true);
        Date date = new Date();
        user.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        userMapper.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        Date date = new Date();
        user.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

}

