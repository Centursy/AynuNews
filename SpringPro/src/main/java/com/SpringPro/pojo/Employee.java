package com.SpringPro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//测试时用的实体类，直接删除即可
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String lastname;
    private String email;
    private int gender; //0:女   1:男

    private String department;
    private Date birth;
}
