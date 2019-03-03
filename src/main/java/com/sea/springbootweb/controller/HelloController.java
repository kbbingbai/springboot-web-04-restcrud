package com.sea.springbootweb.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.SpringContextUtils;


import java.util.Arrays;
import java.util.Map;
/**
 * date  2019/1/20-17:13
 * Description:
 * 运行的结果：
 */

@Controller
@RequestMapping
public class HelloController {

//    @RequestMapping({"/hello","/"})
//    public String index(){
//        return "index";
//    }

    @RequestMapping("/hello_1")
    public  String hello(Map<String,Object> map) throws BeansException{
        map.put("hello", "<h1>hellovalue</h1>");
        map.put("person", Arrays.asList("zs","ww","dd"));
        return "success";
    }
}
