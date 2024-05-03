package com.nikola.spring.service.impl;

import com.nikola.spring.dto.PlayerDto;
import com.nikola.spring.service.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PlayerServiceImpl implements PlayerService {

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {
        return null;
    }

    @Override
    public List<PlayerDto> listAllPlayers() {
        return null;
    }

    @Override
    public PlayerDto updatePlayerById(Integer playerId) {
        return null;
    }

    @Override
    public PlayerDto getPlayerInfoById(Integer playerId) {
        return null;
    }

    @Override
    public void deletePlayerById(Integer playerId) {

    }
}
