package com.SpringPro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//用户的实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String password;
}
