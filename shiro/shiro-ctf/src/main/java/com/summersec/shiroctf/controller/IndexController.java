package com.summersec.shiroctf.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.summersec.shiroctf.Tools.LogHandler;
import com.summersec.shiroctf.Tools.Tools;
import com.summersec.shiroctf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:25
 * @Version: v1.0.0
 * @Description:
 **/
@Controller
public class IndexController {
    public IndexController() {
    }

    @GetMapping({"/"})
    public String main() {
        return "redirect:login";
    }

    @GetMapping({"/index/{name}"})
    public String index(HttpServletRequest request, HttpServletResponse response, @PathVariable String name) throws Exception {
        Cookie[] cookies = request.getCookies();
        boolean exist = false;
        Cookie cookie = null;
        User user = null;
        if (cookies != null) {
            Cookie[] var8 = cookies;
            int var9 = cookies.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Cookie c = var8[var10];
                if (c.getName().equals("hacker")) {
                    exist = true;
                    cookie = c;
                    break;
                }
            }
        }

        if (exist) {

            byte[] bytes = Tools.base64Decode(cookie.getValue());
            user = (User)Tools.deserialize(bytes);


        } else {
            user = new User();
            user.setID(1);
            user.setUserName(name);
            cookie = new Cookie("hacker", Tools.base64Encode(Tools.serialize(user)));
            response.addCookie(cookie);
        }

        request.setAttribute("hacker", user);
        request.setAttribute("logs", new LogHandler());
        return "index";
    }




}
