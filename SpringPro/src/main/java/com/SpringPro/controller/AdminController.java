package com.SpringPro.controller;

import com.SpringPro.mapper.AdminMapper;
import com.SpringPro.mapper.LogMapper;
import com.SpringPro.mapper.NewsMapper;
import com.SpringPro.mapper.UserMapper;
import com.SpringPro.pojo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private LogMapper logMapper;

    //跳转登录
    @RequestMapping("/admlogin")
    public String logg(){
        return "admin/loginl";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

    //接收登录请求
    @RequestMapping("/adlogin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model
            , HttpSession session
    ) {


        //登录实现

        Admin admin = adminMapper.selectById(username);

        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                session.setAttribute("loginUser", username);
                int level = admin.getLevel();
                session.setAttribute("this",level);
                //登录成功

                return "admin/admin";

            } else {
                model.addAttribute("msg", "密码错误");
                return "admin/loginl";
            }

        } else {
            model.addAttribute("msg", "用户不存在");
            return "admin/loginl";
        }
    }

    //返回用户列表
    @RequestMapping("/aduserlist")
    public String aduserlist(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectList(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);

        model.addAttribute("user",users);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/aduserlist";
    }

    @RequestMapping("/newslist")
    public String newsa(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(null);
        PageInfo<News> pageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/newslist";
    }

    @GetMapping("/newslist/{id}")
    public String newsashow(@PathVariable("id")String id, Model model){
        News newz = newsMapper.selectById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("lzid",id);

        List<Log> logs = logMapper.selectByMap(map);

        model.addAttribute("logs",logs);
        model.addAttribute("news",newz);
        return "admin/adnewsshow";
    }

    //返回管理员列表
    @RequestMapping("/adlist")
    public String adlist(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        PageHelper.startPage(pageNum, pageSize);
        List<Admin> users = adminMapper.selectList(null);
        PageInfo<Admin> pageInfo = new PageInfo<>(users);

        model.addAttribute("user",users);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/adlist";
    }

    @RequestMapping("/addad")
    public String savead(){
        return "admin/addadmin";
    }

    //接收添加管理员请求
    @PostMapping("/addad")
    public String save (Admin admin,Model model){
        //添加的操作
        Admin ad233 = adminMapper.selectById(admin.getId());
        if (ad233 != null){
            model.addAttribute("msg","该昵称已存在");
            return "admin/addadmin";
        }else {
            if (admin.getId().isEmpty() || admin.getPassword().isEmpty()){
                model.addAttribute("msg","昵称和密码不为空");
                return "admin/addadmin";
            }else {

                System.out.println("save-->"+admin);
                adminMapper.insert(admin); //调用底层业务保存员工信息
                return "redirect:/adlist";
            }
        }

    }

    //修改时填的默认值
    @GetMapping("/aded/{id}")
    public String edit(@PathVariable("id") String id, Model model){
        //先查出原来的数据

        Admin admin = adminMapper.selectById(id);
        model.addAttribute("user",admin);
        //查出所有部门的信息

        return "admin/editad";
    }

    //删除
    @GetMapping("/delad/{id}")
    public String del(@PathVariable("id")String id){
        adminMapper.deleteById(id);
        return "redirect:/adlist";
    }

    //删除新闻
    @GetMapping("/newsqdel/{id}")
    public String delnews(@PathVariable("id")String id){
        newsMapper.deleteById(id);

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("lzid",id);

        logMapper.deleteByMap(objectObjectHashMap);
        return "redirect:/newslist";
    }
    //改用户时先查出原先数据
    @GetMapping("/adusered/{id}")
    public String aduseredit(@PathVariable("id")String id,Model model){
        //查出原先的数据
        User user = userMapper.selectById(id);
        model.addAttribute("user",user);
        return "admin/adusered";
    }
    //退出登录
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
