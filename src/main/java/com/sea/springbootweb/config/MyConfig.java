package com.sea.springbootweb.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.springbootweb.component.LoginInterceptor;
import com.sea.springbootweb.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

/**
 * date  2019/1/22-20:51
 * Description:实现对springmvc的扩展，要需要什么功能就重写什么方法就可以了
 *  1：需要有@Configuration
 *  2: 需要继承WebMvcConfigurerAdapter
 *  3：不能标注@EnableWebMvc
 *  注意springboot2.1.1 WebMvcConfigurerAdapter被抛弃了，我听的课程是1.5.9的，它那时的可以，现在的版本可以使用
 *  WebMvcConfigurer来代替WebMvcConfigurerAdapter
 *  我这里还是采用讲者的
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**")表示它可以拦截任何请求
        //excludePathPatterns("/","index.html","/user/login") 可以排除哪些请求不被拦截
        //springboot已经给我们做好的静态资源的拦截，静态资源不会拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/login.html","/","/user/login");
    }

    //添加自定义的Formatters
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }


    //第一种方式实现跳转到首页
    //视图的映射器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /kongbei请求就来到success页面
        registry.addViewController("/kongbei").setViewName("success");
    }

    //第二种方式实现跳转到首页，springboot会扫描WebMvcConfigurer 并加入到容器当中
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //浏览器发送 localhost:8080/  或者localhost:8080/index.html 都将跳转到首页
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");//dashboard是登陆首页
            }
        };
    }

    //自己的国际化解析器
    @Bean
    public LocaleResolver locale(){
        return new MyLocaleResolver();
    }
}
