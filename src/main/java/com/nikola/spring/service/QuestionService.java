package com.nikola.spring.service;

import com.nikola.spring.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto addQuestion(QuestionDto questionDto);
    List<QuestionDto> listAllQuestions();

    QuestionDto updateQuestionById(Integer questionId);

    QuestionDto getQuestionById(Integer questionId);

    void  deleteQuestionById(Integer questionId);




}
