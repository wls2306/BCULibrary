package com.bcu.service;

import com.bcu.mapper.StudyMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.bcu.pojo.Study;

@Service("StudyService")
public class StudyService {

    @Autowired
    private StudyMapper studyDao;


    /**
     * 插入学习记录至数据库
     * @param pojo
     * @return Boolean result
     */
    public boolean insert(Study pojo){

        boolean result=studyDao.insert(pojo)>0?true:false;

        return result;
    }

    public List<Study> select(Study pojo){

      //  return studyDao.select(pojo);
        return null;
    }


    public int update(Study pojo){

        //return studyDao.update(pojo);
        return -1;
    }

}
