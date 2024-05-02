package com.nikola.spring.repositories;

import com.nikola.spring.entities.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity,Integer> {
}
