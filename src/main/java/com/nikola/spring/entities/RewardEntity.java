package com.nikola.spring.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "rewards")
public class RewardEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reward_name", length = 60, nullable = false)
    private String rewardName;

    @Column(name = "reward_place", nullable = false)
    private Integer rewardPlace;

    @ManyToOne
    @JoinColumn(nullable = false, name = "quiz_id")
    private QuizEntity quiz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public Integer getRewardPlace() {
        return rewardPlace;
    }

    public void setRewardPlace(Integer rewardPlace) {
        this.rewardPlace = rewardPlace;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }
}
