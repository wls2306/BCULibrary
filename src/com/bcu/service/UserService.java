package com.bcu.service;
import com.bcu.mapper.UserMapper;
import com.bcu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * 添加用户
     * @param user
     * @return boolean Result
     */
    public boolean insertUser(User user) {

            boolean rs= userMapper.insertUser(user)>0?true:false;
            return rs;
    }

    /**
     * 通过学号查找唯一用户
     * @param userId
     * @return User
     */
    public User selectUserById(String userId)
    {

            User user= userMapper.selectUserById(userId);
            return user;
    }

    /**
     * 通过条件查找一个或多个用户
     * @param user
     * @return List<User>
     */
    public List<User> selectUser(User user)
    {
        List<User> list=null;
        list= userMapper.selectUser(user);
        return list;
    }

    public boolean updateUser(User user)
    {
        boolean rs= userMapper.updateUser(user)>0?true:false;
        return rs;
    }


    public boolean addUserStudyTime(String userId, int hours)
    {
        int time= userMapper.findUserStudyTimeByUserId(userId)+hours;
        User u=new User();
        u.setUserId(userId);
        u.setUserStudyTime(time);
        boolean rs= userMapper.updateUser(u)>0?true:false;
        return rs;
    }

    public User findUserByOpenId(String openid)
    {
        return userMapper.findByUserOpenId(openid);
    }

    public int findUserStudyTimeByUserId(String userId)
    {
        return userMapper.findUserStudyTimeByUserId(userId);
    }

    public String getUserRankByOpenId(String openid) {  return  userMapper.getUserRankByOpenId(openid); }

    public String selectUserOpenIdByUserId(String userId){ return userMapper.selectUserOpenIdByUserId(userId) ;}

    public User findByUserId(String userId){  return userMapper.findByUserId(userId);  }


}
