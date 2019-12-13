package com.zl.controller;

import com.zl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 登录注册控制层
 *
 * @author 徐浩杰
 * @version 1.0 2019-12-12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService us;

    /**
     * 注册(卡号)
     *
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(String accNo, String password) {
        System.out.println("进入注册控制层");
        System.out.println("accNo： "+accNo);
        System.out.println("password： "+password);
        ModelAndView mv = new ModelAndView();
        if (accNo != null && !accNo.equals("") && password != null && !password.equals("")) {
            us.register(accNo, password);
            mv.setViewName("/user/toLogin");
        } else {
            mv.addObject("error", "请填写注册信息");
            mv.setViewName("/user/toRegister");
        }
        return mv;
    }

    /**
     *ajax后台检测是否重复注册
     * @return false 重复 true 合法
     */
    @ResponseBody
    @RequestMapping("/regName")
    public Map<String, Object> regName(String accNo) {
        System.out.println("regName");
        return us.regName(accNo);
    }

    /**
     *登录
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String accNo, @RequestParam String password){
        System.out.println("登录控制层:" +accNo+"...."+password);
        UsernamePasswordToken token = new UsernamePasswordToken(accNo, password);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("......登录验证");
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            System.out.println("ice异常......");
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error", "用户名或密码错误");
            return mv;
        } catch (UnknownAccountException uae) {
            System.out.println("uae异常......");
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error", "用户名或密码错误");
            return mv;
        } catch (Exception e) {
            System.out.println("e异常......");
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("error", "请输入正确用户名或密码");
            return mv;
        }
        return new ModelAndView("index");
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

}
