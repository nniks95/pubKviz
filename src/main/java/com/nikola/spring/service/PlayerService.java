package com.nikola.spring.service;

import com.nikola.spring.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto addPlayer(PlayerDto playerDto);

    List<PlayerDto> listAllPlayers();

    PlayerDto updatePlayerById(Integer playerId);

    PlayerDto getPlayerInfoById(Integer playerId);

    void deletePlayerById(Integer playerId);
}
