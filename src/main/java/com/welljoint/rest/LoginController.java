package com.welljoint.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.welljoint.entity.User;
import com.welljoint.service.LoginService;
@Scope("prototype")
@Controller  //註解為控制器
@RequestMapping(value="/login")    //截獲帶有/login的請求
public class LoginController {

    @Autowired
    LoginService loginService;  //注入service層

    @RequestMapping(method = RequestMethod.GET)
    public String get(){  //用來返回一個頁面
        return "login";  //返回指向login.jsp頁面
    }

    @RequestMapping(method=RequestMethod.POST)
    public String post(User user){  //用來處理使用者的登陸請求
        if (loginService.login(user.getUserName(), user.getPassword())==1) {
            return "login_success";  //登陸成功，跳轉到login_success.jsp頁面
        }
        return "login";
    }
}