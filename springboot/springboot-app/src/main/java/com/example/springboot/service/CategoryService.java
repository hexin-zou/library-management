package com.example.springboot.service;

import com.example.springboot.request.BaseRequest;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.model.Category;
import com.example.springboot.api.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class CategoryService implements ICategoryService {

    @Resource
    CategoryMapper categoryMapper;


    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public PageInfo<Category> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        // 自关联查询
        List<Category> categories = categoryMapper.listByCondition(baseRequest);
        return new PageInfo<>(categories);
    }

    @Override
    public void save(Category obj) {
        Category existingCategory = categoryMapper.getByCategoryname(obj.getName());
        if (existingCategory != null && existingCategory.getName().equals(obj.getName())) {
            log.error("类名已存在");
            throw new ServiceException("类名已存在");
        }
        categoryMapper.save(obj);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category obj) {
        obj.setUpdatetime(new Date());
        categoryMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

}
