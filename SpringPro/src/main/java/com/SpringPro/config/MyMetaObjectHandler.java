package com.SpringPro.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    //自动填充，本项目为自动填充日期
    @Override
    public void insertFill(MetaObject metaObject) {

        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        //给createTime这个字段和updateTime这俩字段 来一个什么值呢 来一个自动插入时间 传一个数据 这个数据就是mataObject
        this.setFieldValByName("time",new Date(),metaObject);


    }

    //自动填充更新时间，我没写更新时间所以就没写
    @Override
    public void updateFill(MetaObject metaObject) {

    }


}
