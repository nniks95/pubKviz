package com.nikola.spring.repositories;


import com.nikola.spring.entities.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity,Integer> {

    Optional<QuizEntity> findByName(String name);
}
