package vip.adog.service;

import org.springframework.stereotype.Service;
import vip.adog.model.User;
import vip.adog.model.UserDao;
import vip.adog.model.UserRepository;

import javax.annotation.Resource;
import java.beans.Transient;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDao userDao;

    @Transient
    public void save(User user){
        userRepository.save(user);
    }

    public User getUser2(long id){
        return userDao.getUserById(id);
    }
    public User getUser(long id){
        return userRepository.findOne(id);
    }
}
