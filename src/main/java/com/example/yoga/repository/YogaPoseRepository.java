package com.example.yoga.repository;

import com.example.yoga.model.YogaPose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YogaPoseRepository extends JpaRepository<YogaPose, Long> {

    // Metode custom de căutare - Spring Data JPA le implementează automat!
    List<YogaPose> findByDifficulty(int difficulty);

    List<YogaPose> findByBodyPart(String bodyPart);

    List<YogaPose> findByEnglishNameContainingIgnoreCase(String name);

    List<YogaPose> findByDifficultyLessThanEqual(int maxDifficulty);
}
