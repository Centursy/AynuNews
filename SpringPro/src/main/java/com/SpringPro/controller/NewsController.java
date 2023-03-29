package com.SpringPro.controller;

import com.SpringPro.mapper.LogMapper;
import com.SpringPro.mapper.NewsMapper;
import com.SpringPro.pojo.Log;
import com.SpringPro.pojo.News;
import com.SpringPro.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private LogMapper logMapper;




    //跳转到板块
    @RequestMapping("/zonghe")
    public String zonghe(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.ne("type","匿名");


        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/zonghe";
    }

    //从id获取新闻具体
    @GetMapping("/news/{id}")
    public String newsshouw(@PathVariable("id")String id,Model model){
        News newz = newsMapper.selectById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("lzid",id);

        List<Log> logs = logMapper.selectByMap(map);

        model.addAttribute("logs",logs);
        model.addAttribute("news",newz);
        return "News/zshow";
    }

    //接收提交的新闻
    @RequestMapping("/newssubmit")
    public String newssub(News news,Model model){
        System.out.println(news);
        if (news.getTitle().isEmpty()){
            model.addAttribute("msg","标题写点东西再发布！");
            return "fail";
        }else {

            newsMapper.insert(news);
            model.addAttribute("msg","发帖成功");
            return "succ";
        }
    }

    //接收提交的评论，并返回当前帖子
    @RequestMapping("/newslog")
    public String logsub(Log log){
        if(log.getTitle().isEmpty() || log.getLog().isEmpty()){
            return "redirect:/news/"+log.getLzid();
        }else {
            logMapper.insert(log);
            return "redirect:/news/"+log.getLzid();
        }
    }
    //跳转到板块
    @RequestMapping("/richang")
    public String richang(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.eq("type","日常");



        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/richang";
    }
    //跳转到板块
    @RequestMapping("/xuexi")
    public String xuexi(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.eq("type","学习");



        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/xuexi";
    }
    //跳转到板块
    @RequestMapping("/weilu")
    public String weilu(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.eq("type","围炉夜话");



        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/weilu";
    }
    //跳转到板块
    @RequestMapping("/xunwen")
    public String xunwen(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.eq("type","询问");



        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/xunwen";
    }

    //跳转到板块
    @RequestMapping("/niming")
    public String niming(Model model
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        //实现倒序输出
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("time");
        newsQueryWrapper.eq("type","匿名");



        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(newsQueryWrapper);
        PageInfo<News> newsPageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", newsPageInfo);

        return "News/niming";
    }
    //匿名模块的请求
    @GetMapping("/newsn/{id}")
    public String newsn(@PathVariable("id")String id,Model model){
        News newz = newsMapper.selectById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("lzid",id);

        List<Log> logs = logMapper.selectByMap(map);

        model.addAttribute("logs",logs);
        model.addAttribute("news",newz);
        return "News/nshow";
    }
    //接收匿名区添加评论的请求
    @RequestMapping("/newslogn")
    public String logsubn(Log log){
        if(log.getTitle().isEmpty() || log.getLog().isEmpty()){
            return "redirect:/newsn/"+log.getLzid();
        }else {
            logMapper.insert(log);
            return "redirect:/newsn/"+log.getLzid();
        }
    }

    //管理页面的请求
    @RequestMapping("/newsa")
    public String newsa(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(null);
        PageInfo<News> pageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("pageInfo", pageInfo);

        return "user/newsa";
    }

    //管理页面的请求
    @GetMapping("/newsa/{id}")
    public String newsashow(@PathVariable("id")String id,Model model){
        News newz = newsMapper.selectById(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("lzid",id);

        List<Log> logs = logMapper.selectByMap(map);

        model.addAttribute("logs",logs);
        model.addAttribute("news",newz);
        return "user/newsashow";
    }
    //删除新闻
    @GetMapping("/newsadel/{id}")
    public String del(@PathVariable("id")String id){
        newsMapper.deleteById(id);

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("lzid",id);

        logMapper.deleteByMap(objectObjectHashMap);
        return "redirect:/newsa";
    }

    @RequestMapping("/quarynew")
    public String qula(String name,Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize){


        QueryWrapper<News> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("title",name);

        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectList(objectQueryWrapper);
        PageInfo<News> pageInfo = new PageInfo<>(news);

        model.addAttribute("news",news);
        model.addAttribute("name",name);
        model.addAttribute("pageInfo", pageInfo);

        return "user/newsb";
    }

}
