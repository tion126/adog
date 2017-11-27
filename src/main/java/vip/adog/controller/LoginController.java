package vip.adog.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vip.adog.model.User;
import vip.adog.model.UserDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private Logger log = Logger.getLogger(LoginController.class);

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/toLogin")
    public String tologin() {
        log.info("登录页面");
        return "to_login";
    }

    @RequestMapping(value = "/forgotPwd")
    public String forgotPwd() {
        log.info("修改密码页面");
        return "forget_pwd";
    }

    @PostMapping(value = "/login")
    public String login(Model model,HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDao.getUser(username, password);
        if (null == user) {
            return "redirect:/toLogin";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user_info", user);
            model.addAttribute("user_info", user);
            return "redirect:/index";
        }
    }

    @GetMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
