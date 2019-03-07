package com.bcu.mapper;
import org.apache.ibatis.annotations.Param;

import com.bcu.pojo.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

     int deleteBYMessageReceiverId(@Param("messageReceiverId")String messageReceiverId);

     int deletebyMessageSeatId(@Param("messageSeatId")String messageSeatId);



}