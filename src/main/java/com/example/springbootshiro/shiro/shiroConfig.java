package com.example.springbootshiro.shiro;

import com.example.springbootshiro.shiro.domain.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    /*
     创建shiroFilterFactoryBean
     */
    @Bean()
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro内置的过滤器可以进行权限拦截
        /**
         * anon:无需认证就可以登录
         * authc:必须认证才能访问
         * user:只有使用remember me才可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:资源必须得到角色权限才可以访问
         */
        Map<String,String> map=new LinkedHashMap<String,String>();
        map.put("/login","anon");
        map.put("/dologin","anon");
        map.put("/index","anon");
        map.put("/add","perms[user=add]");
        map.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean("userRealm")
    public UserRealm getReal(){
        return new UserRealm();
    }
}
