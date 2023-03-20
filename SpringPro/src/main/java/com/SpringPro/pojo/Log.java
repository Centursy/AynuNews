package com.SpringPro.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//评论的实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    int id;
    int lzid;
    String loguser;
    String title;
    String log;

    @TableField(fill= FieldFill.INSERT)
    Date time;
}
