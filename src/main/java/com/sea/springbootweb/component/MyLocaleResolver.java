package com.sea.springbootweb.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * date  2019/1/24-21:35
 * Description: 设置国际化的信息
 * 运行的结果：
 */
public class MyLocaleResolver implements LocaleResolver{

    public MyLocaleResolver(){
        System.out.println("sssssssssssssssssssss");
    }
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String link = request.getParameter("l");
        Locale locale = Locale.getDefault();//如果自己没有配置，则用系统默认的，如果自己写了，则采用自己的
        if(!StringUtils.isEmpty(link)){
            String[] localArr = link.split("_");
            locale = new Locale(localArr[0],localArr[1]);//第一个参数是语言代码，第二个参数是国家代码
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
