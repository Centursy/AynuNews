package com.SpringPro.mapper;

import com.SpringPro.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
//MybatisPlus中只要集成了BaseMapper就可以实现sql操作，不用写xml！太爽了！（另外本mapper是测试用，直接删除即可）
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
