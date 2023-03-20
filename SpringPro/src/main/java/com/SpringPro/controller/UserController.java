package com.SpringPro.controller;

import com.SpringPro.mapper.UserMapper;
import com.SpringPro.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//主要为用户管理页面的控制器
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //返回用户列表
    @RequestMapping("/userlist")
    public String userlist(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectList(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);

        model.addAttribute("user",users);
        model.addAttribute("pageInfo", pageInfo);

        return "user/userlist";
    }
    //改用户时先查出原先数据
    @GetMapping("/user/{id}")
    public String edit(@PathVariable("id")String id,Model model){
        //查出原先的数据
        User user = userMapper.selectById(id);
        model.addAttribute("user",user);
        return "user/edit";
    }
    //改密码
    @PostMapping("/edituser")
    public String edit(User user){
        userMapper.updateById(user);
        return "redirect:/userlist";
    }
    //删除用户
    @GetMapping("/userdel/{id}")
    public String del(@PathVariable("id")String id){
        userMapper.deleteById(id);
        return "redirect:/userlist";
    }
    //用户自己改密码
    @GetMapping("/useru/{id}")
    public String editu(@PathVariable("id")String id,Model model){
        //查出原先的数据
        User user = userMapper.selectById(id);
        model.addAttribute("user",user);
        return "user/editu";
    }
    //同上
    @PostMapping("/edituseru")
    public String editu(User user){
        userMapper.updateById(user);
        return "redirect:/Main.html";
    }
}
