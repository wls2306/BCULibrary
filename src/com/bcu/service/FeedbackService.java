package com.bcu.service;

import com.bcu.mapper.FeedbackMapper;
import com.bcu.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public boolean insert(Feedback f)
    {
        return feedbackMapper.insert(f)>0?true:false;
    }

    public List<Feedback> getFeedback()
    {
        return feedbackMapper.select();
    }
}
