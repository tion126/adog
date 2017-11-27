package vip.adog.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.adog.model.Photo;
import vip.adog.service.PhotoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    private Logger log = Logger.getLogger(IndexController.class);
    @Resource
    private PhotoService photoService;


    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request){
        log.info("--首页--");
        HttpSession session = request.getSession();
        model.addAttribute("user_info", session.getAttribute("user_info"));
        List<Photo> photos = photoService.getPhotoAll();
        model.addAttribute("photos", photos);
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request){
        log.info("--首页--");
        HttpSession session = request.getSession();
        model.addAttribute("user_info", session.getAttribute("user_info"));
        List<Photo> photos = photoService.getPhotoAll();
        model.addAttribute("photos", photos);
        return "index";
    }

}
