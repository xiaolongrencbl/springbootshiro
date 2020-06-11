package com.example.springbootshiro.shiro.service.impl;

import com.example.springbootshiro.shiro.dao.UserDao;
import com.example.springbootshiro.shiro.domain.User;
import com.example.springbootshiro.shiro.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDao.findUserByName(name);
    }

}
