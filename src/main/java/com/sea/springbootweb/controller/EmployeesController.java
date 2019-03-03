package com.sea.springbootweb.controller;

import com.sea.springbootweb.dao.DepartmentDao;
import com.sea.springbootweb.dao.EmployeeDao;
import com.sea.springbootweb.entities.Department;
import com.sea.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * date  2019/1/26-21:00
 * Description:
 * 运行的结果：
 */
@Controller
public class EmployeesController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    //去员工的list页面
    @GetMapping(value="/emps")
    public String emps(Map map){
        Collection<Employee> list = employeeDao.getAll();
        map.put("emps",list);
        return "emp/list";
    }

    //去员工的添加页面
    @GetMapping(value="/emp")
    public String emp(Map map){
        Collection<Department> list = departmentDao.getDepartments();
        map.put("departments",list);
        return "emp/add";
    }

    //员工添加，注意分析这几个类，ThymeleafViewResolver当中的createView方法，RedirectView的sendRedirect，InternalResourceView的renderMergedOutputModel
    @PostMapping(value="/emp")
    public String addEmp(Employee employee){

        System.out.println(employee);
        employeeDao.save(employee);
        //redirect: 重定向
        //forward:进行转发
        return "redirect:/emps";
    }

    //进行员工的修改，注意分析这个类HiddenHttpMethodFilter的doFilterInternal方法，WebMvcAutoConfiguration 的hiddenHttpMethodFilter这个方法为我们添加了HiddenHttpMethodFilter组件
    @PutMapping("/emp")
    public String updateEmp(Employee emp){
        employeeDao.save(emp);
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping(value="/emp/{id}")
    public String goEditPage(@PathVariable Integer id,Map map){
        //查询出这个员工
        Employee employee = employeeDao.get(id);
        //查询出所有的部门
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("emp", employee);
        map.put("departments", depts);
        //添加与修改页面用的是同一个页面
        return "/emp/add";
    }

    //删除用户
    @DeleteMapping(value="/emp/{id}")
    public String goEditPage(@PathVariable Integer id){
        employeeDao.delete(id);
        //添加与修改页面用的是同一个页面
        return "redirect:/emps";
    }



}

