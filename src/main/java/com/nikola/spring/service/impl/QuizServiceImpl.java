package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.QuestionDto;
import com.nikola.spring.dto.QuizDto;
import com.nikola.spring.dto.RewardDto;
import com.nikola.spring.dto.TeamDto;
import com.nikola.spring.entities.QuestionEntity;
import com.nikola.spring.entities.QuizEntity;
import com.nikola.spring.entities.RewardEntity;
import com.nikola.spring.entities.TeamEntity;
import com.nikola.spring.exceptions.DuplicateNotAllowed;
import com.nikola.spring.exceptions.InstanceUndefinedException;
import com.nikola.spring.repositories.QuizRepository;
import com.nikola.spring.repositories.TeamRepository;
import com.nikola.spring.service.QuestionService;
import com.nikola.spring.service.QuizService;
import com.nikola.spring.service.RewardService;
import com.nikola.spring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired private TempConverter tempConverter;
    @Autowired private QuizRepository quizRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private TeamService teamService;
    @Autowired private QuestionService questionService;
    @Autowired private RewardService rewardService;


    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Optional<QuizEntity> quizEntityOptional = quizRepository.findByName(quizDto.getName());
        if(quizEntityOptional.isPresent()){
            throw new DuplicateNotAllowed(new Error("Quiz with this name already exists"));
        }
        QuizEntity quizEntity = tempConverter.dtoToEntity(quizDto);
        QuizEntity storedQuiz = quizRepository.save(quizEntity);
        return tempConverter.entityToDto(storedQuiz);
    }

    @Override
    public List<QuizDto> listAllQuizes() {
        List<QuizDto> returnValue = new ArrayList<>();
        List<QuizEntity> allQuizes = quizRepository.findAll();
        for(QuizEntity quiz:allQuizes){
            returnValue.add(tempConverter.entityToDto(quiz));
        }
        return returnValue;
    }

    @Override
    public QuizDto updateQuizById(Integer quizId, QuizDto quizDto) {
        QuizEntity currentQuiz = quizRepository.findById(quizId).orElseThrow(()->new InstanceUndefinedException(new Error("Quiz has not been found.")));
        QuizEntity quiz = tempConverter.dtoToEntity(quizDto);
        quiz.setName(currentQuiz.getName());
        quiz.setId(currentQuiz.getId());
        quiz.setCreateTime(currentQuiz.getCreateTime());
        quiz.setSlots(currentQuiz.getSlots());
        QuizEntity updateQuiz = quizRepository.saveAndFlush(quiz);
        return tempConverter.entityToDto(updateQuiz);
    }

    @Override
    public QuizDto getQuizInfoById(Integer quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId).orElseThrow(()->new InstanceUndefinedException(new Error("Quiz does not exist.")));
        return tempConverter.entityToDto(quizEntity);
    }

    @Override
    public void deleteQuiz(Integer quizId) {
        QuizDto quizDto = getQuizInfoById(quizId);
        quizRepository.deleteById(quizDto.getId());
        quizRepository.flush();
    }

    @Override
    public QuizDto addTeamsToQuiz(List<Integer> teamsIds, QuizDto quizDto) {
        QuizEntity currentQuiz = quizRepository.findById(quizDto.getId()).orElseThrow(()->new InstanceUndefinedException(new Error("Quiz does not exist.")));
        QuizEntity quiz  = tempConverter.dtoToEntity(quizDto);
        List<TeamEntity> teams = new ArrayList<>();
        for(Integer teamId: teamsIds){
            TeamDto teamDto = teamService.getTeamById(teamId);
            if(teamDto != null){
                teams.add(tempConverter.dtoToEntity(teamDto));
                
            }
        }
        quiz.setTeams(teams);
        quizRepository.save(quiz);
        return tempConverter.entityToDto(quiz);
    }

    @Override
    public QuizDto addQuestionsToQuiz(List<Integer> questionsIds, QuizDto quizDto) {
        QuizEntity currentQuiz = quizRepository.findById(quizDto.getId()).orElseThrow(()->new InstanceUndefinedException(new Error("Quiz does not exist.")));
        QuizEntity quiz  = tempConverter.dtoToEntity(quizDto);
        List<QuestionEntity> questions = new ArrayList<>();
        for(Integer questionId: questionsIds){
            QuestionDto questionDto = questionService.getQuestionById(questionId);
            if(questionDto != null){
                questions.add(tempConverter.dtoToEntity(questionDto));
            }
        }
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return tempConverter.entityToDto(quiz);
    }

    @Override
    public QuizDto addRewardsToQuiz(List<Integer> rewardsIds, QuizDto quizDto) {
        QuizEntity currentQuiz = quizRepository.findById(quizDto.getId()).orElseThrow(()->new InstanceUndefinedException(new Error("Quiz does not exist.")));
        QuizEntity quiz  = tempConverter.dtoToEntity(quizDto);
        List<RewardEntity> rewards = new ArrayList<>();
        for(Integer rewardId : rewardsIds){
            RewardDto rewardDto = rewardService.getRewardById(rewardId);
            if(rewardDto != null){
                rewards.add(tempConverter.dtoToEntity(rewardDto));
            }
        }
        quiz.setRewards(rewards);
        quizRepository.save(quiz);
        return tempConverter.entityToDto(quiz);
    }
}
