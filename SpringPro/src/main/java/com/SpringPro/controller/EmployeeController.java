package com.SpringPro.controller;

import com.SpringPro.mapper.DepartmentMapper;
import com.SpringPro.mapper.EmployeeMapper;
import com.SpringPro.pojo.Department;
import com.SpringPro.pojo.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
//本类为练手测试时写的，直接删掉也可以
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;



    //测试分页的！
    @RequestMapping("/emps")
    public String logList(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectList(null);
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        model.addAttribute("emps", list);
        model.addAttribute("pageInfo", pageInfo);


        return "emp/list.html";
    }



    // //查询
    // @RequestMapping("/emps")
    // public String list(Model model){
    //     List<Employee> employees = employeeMapper.selectList(null);
    //     model.addAttribute("emps",employees);
    //     return "emp/list.html";
    // }

    //返回添加页面，返回部门信息
    @GetMapping("/emp")
    public String add (Model model){
        //查出所有部门的信息
        List<Department> departments = departmentMapper.selectList(null);
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //接收添加员工请求
    @PostMapping("/emp")
    public String save (Employee employee){
        //添加的操作
        System.out.println("save-->"+employee);
        employeeMapper.insert(employee); //调用底层业务保存员工信息
        return "redirect:/emps";
    }

    //修改时填的默认值
    @GetMapping("/emp/{id}")
    public String edit(@PathVariable("id")Integer id, Model model){
        //先查出原来的数据
        Employee employee = employeeMapper.selectById(id);
        model.addAttribute("emp",employee);
        //查出所有部门的信息
        List<Department> departments = departmentMapper.selectList(null);
        model.addAttribute("departments",departments);
        return "emp/edit";
    }
    //保存编辑
    @PostMapping("/editemp")
    public String editemp(Employee employee){
        employeeMapper.updateById(employee);
        return "redirect:/emps";
    }

    //删除
    @GetMapping("/empdel/{id}")
    public String del(@PathVariable("id")Integer id){
        employeeMapper.deleteById(id);
        return "redirect:/emps";
    }


}
