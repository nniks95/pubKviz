package com.nikola.spring.converter;


import com.nikola.spring.dto.*;
import com.nikola.spring.entities.*;
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

    public QuizEntity dtoToEntity(QuizDto quizDto){
        QuizEntity returnValue = mapper.map(quizDto, QuizEntity.class);
        Optional<List<Integer>> questionIdsOptional = Optional.ofNullable(quizDto.getQuestionsIds());
        List<QuestionEntity> questions = new ArrayList<>();
        if(questionIdsOptional.isPresent()){
            for(Integer questionId:questionIdsOptional.get()){
                QuestionEntity questionEntity = questionRepository.findById(questionId).orElse(null);
                if(questionEntity != null){
                    questions.add(questionEntity);
                }
            }
        }
        returnValue.setQuestions(questions);

        Optional<List<Integer>> rewardsIdsOptional = Optional.ofNullable(quizDto.getRewardsIds());
        List<RewardEntity> rewards = new ArrayList<>();
        if(rewardsIdsOptional.isPresent()){
            for(Integer rewardId:rewardsIdsOptional.get()){
                RewardEntity rewardEntity = rewardRepository.findById(rewardId).orElse(null);
                if(rewardEntity!= null){
                    rewards.add(rewardEntity);
                }
            }
        }
        returnValue.setRewards(rewards);

        Optional<List<Integer>> teamIdsOptional = Optional.ofNullable(quizDto.getTeamIds());
        List<TeamEntity> teams = new ArrayList<>();
        if(teamIdsOptional.isPresent()){
            for(Integer team:teamIdsOptional.get()){
                TeamEntity teamEntity = teamRepository.findById(team).orElse(null);
                if(teamEntity != null){
                    teams.add(teamEntity);
                }
            }
        }
        returnValue.setTeams(teams);

        return returnValue;
    }

    public TeamDto entityToDto(TeamEntity teamEntity){
        TeamDto returnValue = mapper.map(teamEntity, TeamDto.class);
        Optional<QuizEntity> quizEntityOptional = Optional.ofNullable(teamEntity.getQuiz());
        if(quizEntityOptional.isPresent()){
            returnValue.setQuizId(quizEntityOptional.get().getId());
        }
        Optional<List<PlayerEntity>> playersEntitiesOptional = Optional.ofNullable(teamEntity.getPlayers());
        List<Integer> playersIds = new ArrayList<>();
        if(playersEntitiesOptional.isPresent()){
            for(PlayerEntity player:playersEntitiesOptional.get()){
                playersIds.add(player.getId());
            }
        }
        returnValue.setPlayersIds(playersIds);
        Optional<List<QuestionEntity>> questionEntitiesOptional = Optional.ofNullable(teamEntity.getQuestions());
        List<Integer> questionsIds = new ArrayList<>();
        if(questionEntitiesOptional.isPresent()){
            for(QuestionEntity question:questionEntitiesOptional.get()){
                questionsIds.add(question.getId());
            }
        }
        returnValue.setQuestionsIds(questionsIds);
        return returnValue;
    }
    public TeamEntity dtoToEntity(TeamDto teamDto){
        TeamEntity returnValue = mapper.map(teamDto, TeamEntity.class);
        Optional<Integer> quizIdOptional = Optional.ofNullable(teamDto.getQuizId());
        if(quizIdOptional.isPresent()){
            QuizEntity quizEntity = quizRepository.findById(quizIdOptional.get()).orElse(null);
            if(quizEntity != null){
                returnValue.setQuiz(quizEntity);
            }
        }
        Optional<List<Integer>> playersIdsOptional = Optional.ofNullable(teamDto.getPlayersIds());
        List<PlayerEntity> players = new ArrayList<>();
        if(playersIdsOptional.isPresent()){
            for(Integer playerId: playersIdsOptional.get()){
                PlayerEntity playerEntity = playerRepository.findById(playerId).orElse(null);
                if(playerEntity != null){
                    players.add(playerEntity);
                }
            }
        }
        returnValue.setPlayers(players);
        Optional<List<Integer>> questionsIdsOptional = Optional.ofNullable(teamDto.getQuestionsIds());
        List<QuestionEntity> questions = new ArrayList<>();
        if(questionsIdsOptional.isPresent()){
            for(Integer questionId : questionsIdsOptional.get()){
                QuestionEntity question = questionRepository.findById(questionId).orElse(null);
                if(question != null){
                    questions.add(question);
                }
            }
        }
        returnValue.setQuestions(questions);
        return returnValue;
    }

    public PlayerDto entityToDto(PlayerEntity playerEntity){
        PlayerDto returnValue = mapper.map(playerEntity,PlayerDto.class);
        Optional<TeamEntity> teamEntityOptional = Optional.ofNullable(playerEntity.getTeam());
        if(teamEntityOptional.isPresent()){
            returnValue.setTeamId(teamEntityOptional.get().getId());
        }
        return returnValue;
    }

    public PlayerEntity dtoToEntity(PlayerDto playerDto){
        PlayerEntity returnValue = mapper.map(playerDto, PlayerEntity.class);
        Optional<Integer> teamIdOptional = Optional.ofNullable(playerDto.getTeamId());
        if(teamIdOptional.isPresent()){
            TeamEntity teamEntity = teamRepository.findById(teamIdOptional.get()).orElse(null);
            if(teamEntity != null){
                returnValue.setTeam(teamEntity);
            }
        }
        return returnValue;
    }

    public RewardDto entityToDto(RewardEntity rewardEntity){
        RewardDto returnValue = mapper.map(rewardEntity,RewardDto.class);
        Optional<QuizEntity> quizEntityOptional = Optional.ofNullable(rewardEntity.getQuiz());
        if(quizEntityOptional.isPresent()){
            returnValue.setQuizId(quizEntityOptional.get().getId());
        }
        return returnValue;
    }

    public RewardEntity dtoToEntity(RewardDto rewardDto){
        RewardEntity returnValue = mapper.map(rewardDto,RewardEntity.class);
        Optional<Integer> quizIdOptional = Optional.ofNullable(rewardDto.getQuizId());
        if(quizIdOptional.isPresent()){
            QuizEntity quiz = quizRepository.findById(quizIdOptional.get()).orElse(null);
            if(quiz != null){
                returnValue.setQuiz(quiz);
            }
        }
        return  returnValue;
    }

    public QuestionDto entityToDto(QuestionEntity questionEntity){
        QuestionDto returnValue = mapper.map(questionEntity,QuestionDto.class);
        Optional<QuizEntity> quizEntityOptional = Optional.ofNullable(questionEntity.getQuiz());
        if(quizEntityOptional.isPresent()){
            returnValue.setQuizId(quizEntityOptional.get().getId());
        }
        Optional<List<TeamEntity>> teamEntitiesOptional = Optional.ofNullable(questionEntity.getTeams());
        List<Integer> teamsIds = new ArrayList<>();
        if(teamEntitiesOptional.isPresent()){
            for(TeamEntity team:teamEntitiesOptional.get()){
                teamsIds.add(team.getId());
            }
        }
        returnValue.setTeamsIds(teamsIds);
        return returnValue;
    }

    public QuestionEntity dtoToEntity(QuestionDto questionDto){
        QuestionEntity returnValue = mapper.map(questionDto,QuestionEntity.class);
        Optional<Integer> quizIdOptional = Optional.ofNullable(questionDto.getQuizId());
        if(quizIdOptional.isPresent()){
            QuizEntity quiz = quizRepository.findById(quizIdOptional.get()).orElse(null);
            if(quiz != null){
                returnValue.setQuiz(quiz);
            }
        }
        Optional<List<Integer>> teamsIdsOptional = Optional.ofNullable(questionDto.getTeamsIds());
        List<TeamEntity> teams = new ArrayList<>();
        if(teamsIdsOptional.isPresent()){
            for(Integer teamId: teamsIdsOptional.get()){
                TeamEntity team = teamRepository.findById(teamId).orElse(null);
                if(team != null){
                    teams.add(team);
                }
            }
        }
        returnValue.setTeams(teams);
        return returnValue;
    }

}
