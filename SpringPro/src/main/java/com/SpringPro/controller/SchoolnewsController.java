package com.SpringPro.controller;

import com.SpringPro.mapper.AdminMapper;
import com.SpringPro.mapper.SchoolnewsMapper;
import com.SpringPro.pojo.Log;
import com.SpringPro.pojo.News;
import com.SpringPro.pojo.Schoolnews;
import com.SpringPro.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class SchoolnewsController {



    @Autowired
    private SchoolnewsMapper schoolnewsMapper;

    @RequestMapping("/postnew")
    public String admin(){
        return "admin/postnew";
    }

    @RequestMapping("/snewspost")
    public String snewpost(Schoolnews schoolnews,Model model){
        if(schoolnews.getTitle().isEmpty() || schoolnews.getWord().isEmpty()){
            model.addAttribute("msg","请保证标题或内容不为空。");
            return "admin/postnew";
        }else {
            schoolnewsMapper.insert(schoolnews);
            model.addAttribute("msg","发布成功，您的投稿已进入审核队列。");
            return "admin/postnew";
        }
    }

    //初级审核队列
    @RequestMapping("/newjud")
    public String newjud(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){

        //实现倒序输出
        QueryWrapper<Schoolnews> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("id");
        newsQueryWrapper.eq("emo",1);


        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnews = schoolnewsMapper.selectList(newsQueryWrapper);
        PageInfo<Schoolnews> pageInfo = new PageInfo<>(schoolnews);

        model.addAttribute("news",schoolnews);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/juda";
    }

    //从id获取新闻具体
    @GetMapping("/pageshow/{id}")
    public String pageshouw(@PathVariable("id")String id, Model model){

        Schoolnews schoolnews = schoolnewsMapper.selectById(id);


        model.addAttribute("news",schoolnews);
        return "admin/pageshow";
    }

    @GetMapping("/delsnews/{id}")
    public String nessdel(@PathVariable("id")String id){

        schoolnewsMapper.deleteById(id);

        return "redirect:/admin";
    }
    //初级审核队列
    @RequestMapping("/newjudb")
    public String newjudb(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){

        //实现倒序输出
        QueryWrapper<Schoolnews> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("id");
        newsQueryWrapper.eq("emo",2);


        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnews = schoolnewsMapper.selectList(newsQueryWrapper);
        PageInfo<Schoolnews> pageInfo = new PageInfo<>(schoolnews);

        model.addAttribute("news",schoolnews);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/judb";
    }

    @PostMapping("/snewsupd")
    public String newsupd(Schoolnews schoolnews){
        schoolnewsMapper.updateById(schoolnews);
        return "redirect:/admin";
    }

    //二级审核队列
    @RequestMapping("/listed")
    public String judlists(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){

        //实现倒序输出
        QueryWrapper<Schoolnews> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("id");
        newsQueryWrapper.eq("emo",3);


        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnews = schoolnewsMapper.selectList(newsQueryWrapper);
        PageInfo<Schoolnews> pageInfo = new PageInfo<>(schoolnews);

        model.addAttribute("news",schoolnews);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/judc";
    }

    //从id获取新闻具体
    @GetMapping("/pageshowc/{id}")
    public String pageshouwc(@PathVariable("id")String id, Model model){

        Schoolnews schoolnews = schoolnewsMapper.selectById(id);


        model.addAttribute("news",schoolnews);
        return "admin/pageshowjudc";
    }
}
