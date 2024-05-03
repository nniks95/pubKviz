package com.nikola.spring.service.impl;

import com.nikola.spring.dto.TeamDto;
import com.nikola.spring.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TeamServiceImpl implements TeamService {

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        return null;
    }

    @Override
    public List<TeamDto> listAllTeams() {
        return null;
    }

    @Override
    public TeamDto getTeamById(Integer teamId) {
        return null;
    }

    @Override
    public TeamDto updateTeamById(Integer teamId) {
        return null;
    }

    @Override
    public void deleteTeamById(Integer teamId) {

    }

    @Override
    public TeamDto addPlayersToTeam(List<Integer> playersIds) {
        return null;
    }

    @Override
    public TeamDto addCorrectAnsweredQuestions(List<Integer> questionsIds) {
        return null;
    }
}
