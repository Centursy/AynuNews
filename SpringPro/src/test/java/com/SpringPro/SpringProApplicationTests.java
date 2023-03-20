package com.SpringPro;

import com.SpringPro.mapper.EmployeeMapper;
import com.SpringPro.mapper.NewsMapper;
import com.SpringPro.mapper.UserMapper;
import com.SpringPro.pojo.Employee;

import com.SpringPro.pojo.News;
import com.SpringPro.pojo.User;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringProApplicationTests {


	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NewsMapper newsMapper;

	@Test
	void contextLoads() {
		System.out.println("cao");
		PageHelper.startPage(1,2);
		List<Employee> userList = employeeMapper.selectList(null);
		System.out.println(userList);

	}

	@Test
	void test(){

		User users = userMapper.selectById("鸡");
		System.out.println(users.getPassword());

	}

	@Test
	void news(){
		News news = new News();
		news.setUser("da迪克");
		news.setTitle("你说什么？");
		news.setNews("你算个金箔？你这个土王八有老子帅？");
		news.setType("日常");
		int insert = newsMapper.insert(news);
		System.out.println(insert);
		System.out.println(news);
	}

}
