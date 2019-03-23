package com.bcu.mapper;
import java.util.List;

import com.bcu.pojo.Message;import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    Message  selectByMessageReceiverId(@Param("messageReceiverId")String messageReceiverId);



    int  deleteBYMessageReceiverId(@Param("messageReceiverId")String messageReceiverId);



    int  deletebyMessageSeatId(@Param("messageSeatId")String messageSeatId);


}