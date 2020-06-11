package com.example.springbootshiro.shiro.domain;

import com.example.springbootshiro.shiro.dao.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLOutput;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected   AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("authorization--------授权权限----");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("authentication--------------认证");
//        String name="jake";
//        String pwd="123";
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        System.out.println("token.getUsername().:"+token.getUsername());

        User user=userDao.findUserByName(token.getUsername());
        System.out.println(user);

        if(user==null){
            return null;
        }

        //判断密码
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
