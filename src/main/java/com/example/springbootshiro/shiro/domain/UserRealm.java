package com.example.springbootshiro.shiro.domain;

import com.example.springbootshiro.shiro.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLOutput;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected   AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("authorization--------授权权限----");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        Subject subject= SecurityUtils.getSubject();
        User users=(User)subject.getPrincipal();
        User user=userDao.findUserByName(users.getName());
        System.out.println(user.getPerms());

//        if(user==null){
//            return null;
//        }
        info.addStringPermission(user.getPerms());
        return info;
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
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
