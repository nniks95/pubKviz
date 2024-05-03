package com.nikola.spring.service;

import com.nikola.spring.dto.QuestionDto;
import com.nikola.spring.dto.QuizDto;
import com.nikola.spring.dto.RewardDto;

import java.util.List;

public interface QuizService {

    QuizDto createQuiz(QuizDto quizDto);

    List<QuizDto> listAllQuizes();

    QuizDto updateQuizById(Integer quizId, QuizDto quizDto);

    QuizDto getQuizInfoById(Integer quizId);

    void deleteQuiz(Integer quizId);

    QuizDto addTeamsToQuiz(List<Integer> teamsIds, QuizDto quizDto);

    QuizDto addQuestionsToQuiz(List<Integer> questionsIds, QuizDto quizDto);

    QuizDto addRewardsToQuiz(List<Integer> rewardsIds, QuizDto quizDto);

}
