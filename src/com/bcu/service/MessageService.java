package com.bcu.service;


import com.bcu.mapper.MessageMapper;
import com.bcu.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("MessageService")
public class MessageService {
@Autowired
    private MessageMapper messageMapper;

public boolean createMessage(String initiatorId,String receiverId,String seatId) {
    Message m=new Message();
    m.setMessageInitiatorId(initiatorId);
    m.setMessageReceiverId(receiverId);
    m.setMessageSeatId(seatId);
    m.setMessageStatus("1");
    m.setMessageCreteTime(new Date());
    return  messageMapper.insert(m)>0?true:false;
}


public boolean deleteMessageByReceiverId(String receiverId)
{
    return messageMapper.deleteBYMessageReceiverId(receiverId)>0?true:false;
}


public boolean deletebyMessageSeatId(String seatId)
{
    return messageMapper.deletebyMessageSeatId(seatId)>0?true:false;
}

}
