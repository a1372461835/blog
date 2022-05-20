package com.cr.controller.admin;

import com.cr.dao.UserDao;
import com.cr.pojo.User;
import com.cr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserDao (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginPage(HttpSession session){
        if(session.getAttribute("user") != null)
            return "admin/index";

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes attributes){

        User user = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            System.out.println("1");

            return "admin/index";
        }
        else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            System.out.println("2");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
