package com.nikola.spring.service;

import com.nikola.spring.dto.TeamDto;

import java.util.List;

public interface TeamService {

    TeamDto createTeam(TeamDto teamDto);

    List<TeamDto> listAllTeams();

    TeamDto getTeamById(Integer teamId);

    TeamDto updateTeamById(Integer teamId);

    void deleteTeamById(Integer teamId);

    TeamDto addPlayersToTeam(List<Integer> playersIds);

    TeamDto addCorrectAnsweredQuestions(List<Integer> questionsIds);






}
