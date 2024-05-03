package com.nikola.spring.service.impl;

import com.nikola.spring.dto.QuestionDto;
import com.nikola.spring.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {
        return null;
    }

    @Override
    public List<QuestionDto> listAllQuestions() {
        return null;
    }

    @Override
    public QuestionDto updateQuestionById(Integer questionId) {
        return null;
    }

    @Override
    public QuestionDto getQuestionById(Integer questionId) {
        return null;
    }

    @Override
    public void deleteQuestionById(Integer questionId) {

    }
}
