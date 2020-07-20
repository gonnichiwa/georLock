package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Test;
import com.springboot.georlock.svc.LoginService;
import com.springboot.georlock.svc.TestService;
import org.apache.coyote.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class LoginController {
    @Autowired
    LoginService loginService;




    @RequestMapping("/loginform")
    public ModelAndView loginform(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        ModelAndView modelAndView = new ModelAndView("login");
        if(cookies!=null){
            String empNo="";
            String userPw="";
            for(int i=0; i<cookies.length; i++){
                if(cookies[i].getName().equals("empNo")){
                    empNo= cookies[i].getValue();
                }
                else  if(cookies[i].getName().equals("userPw")){
                    userPw= cookies[i].getValue();
                }
            }
            if(loginService.Login(empNo,userPw)){
                modelAndView.setViewName("access");
            }
        }
        System.out.println("loginForm");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(String empNo,String userPw,boolean autoLogin,HttpServletResponse response) throws Exception{
        System.out.println("login strat");
        ModelAndView modelAndView = new ModelAndView("access");
        if(!loginService.Login(empNo,userPw)){
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "로그인 실패");
        }
        if(autoLogin){
            loginService.autoLogin(empNo,userPw,response);
        }

        System.out.println("login end");
        return modelAndView;
    }



    @RequestMapping("/access")
    public String access() {
        System.out.println("access");
        return "access";
    }

    @RequestMapping("/record")
    public String record() {
        System.out.println("record");
        return "record";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("logout");
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(int i=0; i<cookies.length; i++){
                cookies[i].setMaxAge(0);
                cookies[i].setPath("/");
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:/loginform";

    }



}
