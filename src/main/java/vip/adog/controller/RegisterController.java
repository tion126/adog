package vip.adog.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.adog.common.BaseController;
import vip.adog.model.User;
import vip.adog.model.UserDao;
import vip.adog.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class RegisterController extends BaseController{
    private Logger log = Logger.getLogger(RegisterController.class);

    @Resource
    private UserDao userDao;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/toRegister")
    public String toregister(Model model,HttpServletRequest request) {
        log.info("注册页面");
        super.init(request);
        super.createToken("register_token");
        return "to_register";
    }

    @PostMapping(value = "/register")
    public String register(Model model,HttpServletRequest request) {
        User u = new User();
        super.init(request);
        u.setName(getPara("username"));
        u.setMobile(getPara("mobile"));
        u.setPassword(getPara("userpwd"));
        u.setStatus(1);
        u.setEmail(getPara("email"));
        u.setCreateTime(new Date());
        synchronized (u){
            userService.save(u);
        }
        request.getSession().setAttribute("user_info", u);
        model.addAttribute("user_info",u);
        return "/index";
    }
}
