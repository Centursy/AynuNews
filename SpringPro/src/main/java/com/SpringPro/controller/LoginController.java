package com.SpringPro.controller;

import com.SpringPro.mapper.NewsMapper;
import com.SpringPro.mapper.UserMapper;
import com.SpringPro.pojo.Employee;
import com.SpringPro.pojo.News;
import com.SpringPro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;

//登录控制器
@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;



    //接收登录请求
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model
                        ,HttpSession session
    ) {


        //登录实现
        User user = userMapper.selectById(username);
        if(username.equals("admin")&&password.equals("123")){
            session.setAttribute("loginUser", username);
            return "redirect:/userlist";
        }else {
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    session.setAttribute("loginUser", username);
                    //登录成功

                    return "redirect:/Main.html";

                } else {
                    model.addAttribute("msg", "密码错误");
                    return "loginl";
                }

            } else {
                model.addAttribute("msg", "用户不存在");
                return "loginl";
            }
        }


        // //具体业务
        // if (!StringUtils.isEmpty(username) && "123".equals(password)) {
        //     session.setAttribute("loginUser",username);
        //     //登录成功
        //     return "redirect:/Main.html";
        // }else {
        //     //登录失败
        //     model.addAttribute("msg","用户名或密码错误");
        //     return "index";
        // }
    }


    //接收注册请求
    @RequestMapping("/rege")
    public String save (User user,Model model){
        //添加的操作
        User user233 = userMapper.selectById(user.getId());
        if (user.getId().equals("admin")) {
            model.addAttribute("msg", "不准注册管理员帐号");
            return "register";
        } else {
            if (user233 == null) {
                userMapper.insert(user); //调用底层业务保存用户
                model.addAttribute("msg", "注册成功，请返回登录");
                return "loginl";
            } else {

                model.addAttribute("msg", "用户已存在");
                return "register";

            }
        }

    }



    //退出登录
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
