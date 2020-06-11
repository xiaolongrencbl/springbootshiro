package com.example.springbootshiro.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Security;


@Controller
public class LoginController {


    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }


    @RequestMapping("/index")
    //@ResponseBody
    public String index() {
        System.out.println("index-------------");
        return "index";
    }
    @RequestMapping("/login")
    public String login(String uname,String pwd) {
        System.out.println("login");
        return "login";
    }

    @RequestMapping("/dologin")
    public String dologin(String uname, String pwd, Model model) {
        System.out.println("uname:"+uname+"       pwd:"+pwd);
        //获取subject
         Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(uname,pwd);
        try{
            subject.login(token);

            return "redirect:/index";
        }catch(UnknownAccountException e){
            //登录失败，用户名不存在
            e.printStackTrace();
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/noauthc")
    public String noauthc() {
        return "noauthc";
    }
}
