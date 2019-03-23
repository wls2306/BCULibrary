package com.bcu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.bcu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@Mapper
public interface UserMapper {

     int insertUser(User user);
     /**
      * @param user
      * @return
      */
     int updateUser(User user);
     /**
      * @param userId
      * @return
      */
     int deleteUser(String userId);
     /**
      * @param userId
      * @return
      */

     User selectUserById(String userId);

     /**
      * @param user
      * @return
      */

      List<User> selectUser(User user) ;
     /**
      *
      */

     User findByUserId(@Param("userId") String userId);


     String findUserNameByUserId(@Param("userId") String userId);

     int findUserStudyTimeByUserId(@Param("userId") String userId);

     User findByUserOpenId(@Param("userOpenId")String userOpenId);

     int getUserRankByOpenId(@Param("userOpenId")String userOpenId);

     String selectUserOpenIdByUserId(@Param("userId")String userId);




}
