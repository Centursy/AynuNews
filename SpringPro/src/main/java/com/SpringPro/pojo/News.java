package com.SpringPro.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//新闻的实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    int id;
    String user;
    String title;
    String news;
    String type;

    @TableField(fill= FieldFill.INSERT)
    Date time;
}
