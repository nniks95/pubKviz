package com.nikola.spring.service;

import com.nikola.spring.dto.RewardDto;

import java.util.List;

public interface RewardService {

    RewardDto addReward(RewardDto rewardDto);
    List<RewardDto> listAllRewards();

    RewardDto updateRewardById(Integer rewardId);

    RewardDto getRewardById(Integer rewardId);

    void deleteRewardById(Integer rewardId);







}
