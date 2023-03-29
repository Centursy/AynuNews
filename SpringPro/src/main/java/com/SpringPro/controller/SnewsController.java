package com.SpringPro.controller;

import com.SpringPro.mapper.SchoolnewsMapper;
import com.SpringPro.pojo.Schoolnews;
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

import java.util.List;

@Controller
public class SnewsController {

    @Autowired
    private SchoolnewsMapper schoolnewsMapper;

    // @RequestMapping("/snews")
    // public String snews(){
    //     return "/snews/index";
    // }
    //

    @RequestMapping("/snews")
    public String newjud(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "7", value = "pageSize") Integer pageSize){

        //实现倒序输出（安师新闻）
        QueryWrapper<Schoolnews> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.orderByDesc("id");
        newsQueryWrapper.eq("emo",3);
        newsQueryWrapper.eq("type","安师新闻");

        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnews = schoolnewsMapper.selectList(newsQueryWrapper);
        model.addAttribute("aynunews",schoolnews);


        //实现倒序输出（院系速递）
        QueryWrapper<Schoolnews> wrapperb = new QueryWrapper<>();
        wrapperb.orderByDesc("id");
        wrapperb.eq("emo",3);
        wrapperb.eq("type","院系速递");

        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnewsb = schoolnewsMapper.selectList(wrapperb);
        model.addAttribute("yuanxinews",schoolnewsb);


        //实现倒序输出（通知公告）
        QueryWrapper<Schoolnews> wrapperc = new QueryWrapper<>();
        wrapperc.orderByDesc("id");
        wrapperc.eq("emo",3);
        wrapperc.eq("type","通知公告");

        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnewsc = schoolnewsMapper.selectList(wrapperc);
        model.addAttribute("tongzhinews",schoolnewsc);


        //实现倒序输出（电子校报）
        QueryWrapper<Schoolnews> wrapperd = new QueryWrapper<>();
        wrapperd.orderByDesc("id");
        wrapperd.eq("emo",3);
        wrapperd.eq("type","电子校报");

        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnewsd = schoolnewsMapper.selectList(wrapperd);
        model.addAttribute("dianzinews",schoolnewsd);


        //实现倒序输出（媒体安师）
        QueryWrapper<Schoolnews> wrappere = new QueryWrapper<>();
        wrappere.orderByDesc("id");
        wrappere.eq("emo",3);
        wrappere.eq("type","媒体安师");

        PageHelper.startPage(pageNum, pageSize);
        List<Schoolnews> schoolnewse = schoolnewsMapper.selectList(wrappere);
        model.addAttribute("meitinews",schoolnewse);


        return "/snews/index";
    }

    //从id获取新闻具体
    @GetMapping("/indnewss/{id}")
    public String indpageshouw(@PathVariable("id")String id, Model model){

        Schoolnews schoolnews = schoolnewsMapper.selectById(id);


        model.addAttribute("news",schoolnews);
        return "snews/newshows";
    }

    //新闻列表
    @GetMapping("/newlis/{type}")
    public String newliss(@PathVariable("type")String type, Model model){

        QueryWrapper<Schoolnews> new233 = new QueryWrapper<>();
        new233.eq("type",type);

        List<Schoolnews> schoolnews = schoolnewsMapper.selectList(new233);
        model.addAttribute("type",type);
        model.addAttribute("news",schoolnews);
        return "snews/newslists";
    }
}
