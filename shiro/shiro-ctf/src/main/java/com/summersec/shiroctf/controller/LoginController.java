package com.summersec.shiroctf.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 15:03
 * @Version: v1.0.0
 * @Description:
 **/

@Controller
public class LoginController {
    public LoginController() {
    }

    @PostMapping({"/doLogin"})
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:index/" + username;
        } catch (AuthenticationException var5) {
            var5.printStackTrace();
            return "redirect:login?failed=1";
        }
    }

    @GetMapping({"/login"})
    public String login(HttpServletRequest request, @RequestParam(value = "failed",defaultValue = "0") String failed) {
        if (!failed.equals("0")) {
            request.setAttribute("failed", 1);
        }

        return "login";
    }
}