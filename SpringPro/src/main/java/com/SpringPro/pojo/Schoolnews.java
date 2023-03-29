package com.SpringPro.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schoolnews {
    int id;
    String writer;
    String title;
    String word;

    String type;
    int emo;
    int wlevel;

    @TableField(fill= FieldFill.INSERT)
    Date time;
}
