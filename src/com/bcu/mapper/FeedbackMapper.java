package com.bcu.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.bcu.pojo.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer fbId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer fbId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKeyWithBLOBs(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> select();


}