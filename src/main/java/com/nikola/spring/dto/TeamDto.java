package com.nikola.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class TeamDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    @Size(min = 2,max = 60)
    private String name;
    @NotNull
    private Integer points;
    @NotNull
    private Integer quizId;
    private List<Integer> playersIds;
    private List<Integer> questionsIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public List<Integer> getPlayersIds() {
        return playersIds;
    }

    public void setPlayersIds(List<Integer> playersIds) {
        this.playersIds = playersIds;
    }

    public List<Integer> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<Integer> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
