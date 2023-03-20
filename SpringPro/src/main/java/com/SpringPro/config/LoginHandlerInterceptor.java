 package com.SpringPro.config;


 import org.springframework.web.servlet.HandlerInterceptor;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 //拦截器,mvc配置里把注释去了就能用了
 public class LoginHandlerInterceptor implements HandlerInterceptor {
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //登录成功之后，应有用户的session
         Object loginUser = request.getSession().getAttribute("loginUser");

         if (loginUser==null){        //未登录
             request.setAttribute("msg","没有权限，请先登录");
             request.getRequestDispatcher("/index.html").forward(request,response);
             return false;
         }else {
             return true;
         }

     }

 }
