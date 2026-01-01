package com.example.yoga.service;

import com.example.yoga.model.YogaPose;
import com.example.yoga.repository.YogaPoseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YogaPoseService {

    private final YogaPoseRepository repository;

    // Constructor injection (recomandat în Spring)
    public YogaPoseService(YogaPoseRepository repository) {
        this.repository = repository;
    }

    // Returnează toate pozițiile
    public List<YogaPose> findAll() {
        return repository.findAll();
    }

    // Găsește o poziție după ID
    public Optional<YogaPose> findById(Long id) {
        return repository.findById(id);
    }

    // Salvează o nouă poziție
    public YogaPose save(YogaPose pose) {
        return repository.save(pose);
    }

    // Actualizează o poziție existentă
    public Optional<YogaPose> update(Long id, YogaPose updatedPose) {
        return repository.findById(id)
                .map(existingPose -> {
                    existingPose.setSanskritName(updatedPose.getSanskritName());
                    existingPose.setEnglishName(updatedPose.getEnglishName());
                    existingPose.setDescription(updatedPose.getDescription());
                    existingPose.setDifficulty(updatedPose.getDifficulty());
                    existingPose.setBenefits(updatedPose.getBenefits());
                    existingPose.setBodyPart(updatedPose.getBodyPart());
                    return repository.save(existingPose);
                });
    }

    // Șterge o poziție
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Caută după nivel de dificultate
    public List<YogaPose> findByDifficulty(int difficulty) {
        return repository.findByDifficulty(difficulty);
    }

    // Caută după partea corpului
    public List<YogaPose> findByBodyPart(String bodyPart) {
        return repository.findByBodyPart(bodyPart);
    }

    // Caută după nume
    public List<YogaPose> searchByName(String name) {
        return repository.findByEnglishNameContainingIgnoreCase(name);
    }

    // Găsește poziții pentru începători (nivel <= maxDifficulty)
    public List<YogaPose> findForBeginners(int maxDifficulty) {
        return repository.findByDifficultyLessThanEqual(maxDifficulty);
    }
}
