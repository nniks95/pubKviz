package com.nikola.spring.tempConverter;


import com.nikola.spring.dto.QuizDto;
import com.nikola.spring.entities.QuestionEntity;
import com.nikola.spring.entities.QuizEntity;
import com.nikola.spring.entities.RewardEntity;
import com.nikola.spring.entities.TeamEntity;
import com.nikola.spring.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TempConverter {

    @Autowired private ModelMapper mapper;
    @Autowired private QuizRepository quizRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private PlayerRepository playerRepository;
    @Autowired private RewardRepository rewardRepository;
    @Autowired private QuestionRepository questionRepository;

    private DecimalFormat decForm = new DecimalFormat("0.00");

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public QuizDto entityToDto(QuizEntity quizEntity){
        QuizDto returnValue = mapper.map(quizEntity, QuizDto.class);

        Optional<List<TeamEntity>> teamEntitiesOptional = Optional.ofNullable(quizEntity.getTeams());
        List<Integer> teamIds = new ArrayList<>();
        if(teamEntitiesOptional.isPresent()){
            for(TeamEntity team: teamEntitiesOptional.get()){
                teamIds.add(team.getId());
            }
        }
        returnValue.setTeamIds(teamIds);

        Optional<List<RewardEntity>> rewardEntitiesOptional = Optional.ofNullable(quizEntity.getRewards());
        List<Integer> rewardsIds = new ArrayList<>();
        if(rewardEntitiesOptional.isPresent()){
            for(RewardEntity reward: rewardEntitiesOptional.get()){
                rewardsIds.add(reward.getId());
            }
        }
        returnValue.setRewardsIds(rewardsIds);

        Optional<List<QuestionEntity>> questionEntitiesOptional = Optional.ofNullable(quizEntity.getQuestions());
        List<Integer> questionIds = new ArrayList<>();
        if(questionEntitiesOptional.isPresent()){
            for(QuestionEntity question:questionEntitiesOptional.get()){
                questionIds.add(question.getId());
            }
        }
        returnValue.setQuestionsIds(questionIds);

        Optional<Timestamp> createTimeOptional = Optional.ofNullable(quizEntity.getCreateTime());
        if(createTimeOptional.isPresent()){
            LocalDateTime createTime = createTimeOptional.get().toLocalDateTime();
            String createTimeStr = createTime.format(dateTimeFormatter);
            returnValue.setCreateTime(createTimeStr);
        }

        Optional<Timestamp> updateTimeOptional = Optional.ofNullable(quizEntity.getUpdateTime());
        if(updateTimeOptional.isPresent()){
            LocalDateTime updateTime = updateTimeOptional.get().toLocalDateTime();
            String updateTimeStr = updateTime.format(dateTimeFormatter);
            returnValue.setUpdateTime(updateTimeStr);
        }

        return  returnValue;
    }





}
