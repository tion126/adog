package vip.adog.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.adog.model.User;
import vip.adog.service.UserService;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class DemoController {
    private final Logger log = LoggerFactory.getLogger(DemoController.class);
    @Resource
    private UserService userService;
    @Resource
    private JdbcTemplate jdbcTemplate;


  @RequestMapping("getUser")
  public User getUser(long id){
      log.info("getUser-----------------------");
      return userService.getUser(id);
  }
  @RequestMapping("getUser2")
  public User getUser2(long id){
      log.info("getUser222-----------------------");
      return userService.getUser2(id);
  }
    @RequestMapping("getUserfj")
    public String getUserFastJson(){
      User u = new User();
      u.setName("jekay");
      u.setMobile("13788245896");
      u.setPassword("jekay");
      u.setStatus(1);
      u.setCreateTime(new Date());
        return JSONObject.toJSONString(u);
    }


    @RequestMapping("save")
    public User saveUser(){
        User u = new User();
        u.setName("jekay");
        u.setPassword("jekay");
        u.setMobile("13788245896");
        u.setStatus(1);
        u.setCreateTime(new Date());
        userService.save(u);
        return u;
    }

    @RequestMapping("save2")
    public String saveUser2(){
        String sql = "insert into user(name,moble,status,create_time) values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"克拉克森","131000000000",100,new Date()});
        return "add user success ...";
    }

}
