package com.SpringPro.mapper;

import com.SpringPro.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
//MybatisPlus中只要集成了BaseMapper就可以实现sql操作，不用写xml！太爽了！（本mapper为用户的mapper）
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
