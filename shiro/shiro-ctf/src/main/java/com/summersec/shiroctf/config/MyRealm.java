package com.summersec.shiroctf.config;


import java.util.UUID;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName: MyRealm
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:22
 * @Version: v1.0.0
 * @Description:
 **/


public class MyRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        if (!"admin".equals(username)) {
            throw new UnknownAccountException("unkown user");
        } else {
            return new SimpleAuthenticationInfo(username, UUID.randomUUID().toString().replaceAll("-", ""), getName());
        }
    }
}

