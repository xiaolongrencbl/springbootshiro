package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends JpaRepository<User,String> {
    public User findUserByName(String name);
}
