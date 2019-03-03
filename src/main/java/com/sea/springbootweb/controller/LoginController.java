package com.sea.springbootweb.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * date  2019/1/25-19:26
 * Description:登陆用的Controller
 * 注意：
 *    1：@RequestMapping(value="/login",method = RequestMethod.POST)现在有一种更好的方式来代替
 *      就是采用@DeleteMapping    @PutMapping  @PostMapping   @GetMapping
 */
@Controller
public class LoginController {
    @PostMapping(value="/user/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        Map<String,String> map, HttpServletRequest request){
        if(!StringUtils.isEmpty(userName)&&"123".equals(password)){
            request.getSession().setAttribute("loginMsg", "true");//放在把成功登陆的信息放在session中，供拦截器判断
            //需要重定向，否则登陆成功后，重新刷新页面会出现表单重复提交的情况
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误" );
            return "login";
        }
    }
}
