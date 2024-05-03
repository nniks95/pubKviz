package com.nikola.spring.service.impl;

import com.nikola.spring.dto.RewardDto;
import com.nikola.spring.service.RewardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RewardServiceImpl implements RewardService {

    @Override
    public RewardDto addReward(RewardDto rewardDto) {
        return null;
    }

    @Override
    public List<RewardDto> listAllRewards() {
        return null;
    }

    @Override
    public RewardDto updateRewardById(Integer rewarId) {
        return null;
    }

    @Override
    public RewardDto getRewardById(Integer rewardId) {
        return null;
    }

    @Override
    public void deleteRewardById(Integer rewardId) {

    }
}
