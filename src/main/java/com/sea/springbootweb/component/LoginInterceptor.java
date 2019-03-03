package com.sea.springbootweb.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * date  2019/1/25-19:40
 */
public class LoginInterceptor implements HandlerInterceptor {
   // preHandle: 在执行Handler之前进行，即Controller方法调用之前执行，主要进行初始化操作。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginMsg = (String)request.getSession().getAttribute("loginMsg");
        if("true".equals(loginMsg)){//已经登陆，请求放行
            return true;
        }else{//用户没有登陆，拦截他的非法请求
            request.setAttribute("msg", "非法请求");
            request.getRequestDispatcher("/login.html").forward(request,response );
            return false;
        }
    }
    //postHandle: 在执行Handler之后进行，即Controller 方法调用之后执行，主要对ModelAndView对象进行操作。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }
    //afterCompletion: 在整个请求结束之后，即渲染对应的视图之后执行, 主要进行资源清理工作。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
