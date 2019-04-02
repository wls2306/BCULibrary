package com.bcu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bcu.pojo.Study;

@Mapper
public interface StudyMapper {
    int insert(@Param("study") Study study);

    int insertSelective(@Param("study") Study study);

    int insertList(@Param("studys") List<Study> studys);

    int updateByPrimaryKeySelective(@Param("study") Study study);

    List<Study> selectStudyRecordByTime(String timeStart,String timeEnd);

    List<Study> selectbyStudySeatId(@Param("studySeatId")String studySeatId);

    int selectStudyTimeByUserPart(String studyUserPart);

    String selectSeatInfoByStudentId(String userId);

    List<Study> selectByStudyUserId(@Param("studyUserId")String studyUserId);



}
