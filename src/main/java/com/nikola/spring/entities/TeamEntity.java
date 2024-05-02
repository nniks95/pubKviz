package com.nikola.spring.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teams")
public class TeamEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "quiz_id")
    private QuizEntity quiz;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "team")
    private List<PlayerEntity> players;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teams_question", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<QuestionEntity> correctQuestions;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public List<QuestionEntity> getQuestions() {
        return correctQuestions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.correctQuestions = questions;
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
