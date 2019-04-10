package com.springmvc.controller;


import com.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/hello")
public class HelloController{

    @RequestMapping("/first")
    @ResponseBody
    public User demo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello world");
//        request.setAttribute("msg","hello wa wa wa...");
//        request.getRequestDispatcher("/WEB-INF/hello.jsp").forward(request,response);
//        request.getRequestDispatcher("/hello.html").forward(request,response);
//        ModelAndView view = new ModelAndView();
//        view.addObject("123");
//        view.addObject("and you");
//        view.setViewName("/WEB-INF/hello.jsp");
//        view.setViewName("index.jsp");
        User user = new User();
        user.setUsername("小熊");
        user.setPassword("123");
        user.setAge(18);
        user.setCreatetime(new Date());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(new Date().getTime());
        System.out.println(user.getCreatetime());

        return user;
    }

    @RequestMapping("/second")
    public ModelAndView demo2(HttpServletRequest request,@RequestParam("username") String name, String password) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username);
//        byte[] bytes = name.getBytes("ISO-8859-1");
//        String name2 = new String(bytes,"utf-8");
        System.out.println(name+":"+password);

        return null;
    }

    @RequestMapping("/thd")
    public ModelAndView demo3(HttpServletRequest request) throws Exception {

        ModelAndView view = new ModelAndView();
        view.addObject("msg","and you");
        view.setViewName("/WEB-INF/hello.jsp");
        return view;
    }
    @RequestMapping("/thu")
    public String demo4(HttpServletRequest request, Model model) throws Exception {
//        request.setAttribute("msg","hello wa wa wa...");
        model.addAttribute("msg","笑嘻嘻222");
        return "hello";
    }
}
