package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.dao.UserDao;
import com.example.springbootshiro.shiro.domain.User;

import java.util.List;


public interface UserSerivce {

    List<User> findAll();

    User findByName(String name);
}
