package com.example.springboot.api;

import com.example.springboot.request.BaseRequest;
import com.example.springboot.model.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBookService {
    
    List<Book> list();

    PageInfo<Book> page(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void update(Book obj);

    void deleteById(Integer id);

}
