package com.example.yoga.service;

import com.example.yoga.model.YogaPose;
import com.example.yoga.repository.YogaPoseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class YogaPoseServiceTest {

    @Mock
    private YogaPoseRepository repository;

    @InjectMocks
    private YogaPoseService service;

    private YogaPose samplePose;

    @BeforeEach
    void setUp() {
        samplePose = new YogaPose(
                "Tadasana",
                "Mountain Pose",
                "Standing pose",
                1,
                "Improves posture",
                "legs"
        );
        samplePose.setId(1L);
    }

    @Test
    @DisplayName("findAll() should return all poses")
    void findAll_ShouldReturnAllPoses() {
        YogaPose pose2 = new YogaPose("Balasana", "Child's Pose", "Rest", 1, "Relaxation", "back");
        List<YogaPose> poses = Arrays.asList(samplePose, pose2);

        when(repository.findAll()).thenReturn(poses);

        List<YogaPose> result = service.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEnglishName()).isEqualTo("Mountain Pose");
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById() should return pose when exists")
    void findById_WhenExists_ShouldReturnPose() {
        when(repository.findById(1L)).thenReturn(Optional.of(samplePose));

        Optional<YogaPose> result = service.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getSanskritName()).isEqualTo("Tadasana");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("findById() should return empty when not exists")
    void findById_WhenNotExists_ShouldReturnEmpty() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<YogaPose> result = service.findById(99L);

        assertThat(result).isEmpty();
        verify(repository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("save() should save and return pose")
    void save_ShouldSaveAndReturnPose() {
        YogaPose newPose = new YogaPose("Vrksasana", "Tree Pose", "Balance", 2, "Balance", "legs");
        YogaPose savedPose = new YogaPose("Vrksasana", "Tree Pose", "Balance", 2, "Balance", "legs");
        savedPose.setId(2L);

        when(repository.save(any(YogaPose.class))).thenReturn(savedPose);

        YogaPose result = service.save(newPose);

        assertThat(result.getId()).isEqualTo(2L);
        assertThat(result.getEnglishName()).isEqualTo("Tree Pose");
        verify(repository, times(1)).save(newPose);
    }

    @Test
    @DisplayName("update() should update existing pose")
    void update_WhenExists_ShouldUpdateAndReturn() {
        YogaPose updatedData = new YogaPose("Tadasana", "Mountain Pose Updated", "Stand tall", 1, "Posture", "legs");

        when(repository.findById(1L)).thenReturn(Optional.of(samplePose));
        when(repository.save(any(YogaPose.class))).thenReturn(samplePose);

        Optional<YogaPose> result = service.update(1L, updatedData);

        assertThat(result).isPresent();
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(YogaPose.class));
    }

    @Test
    @DisplayName("update() should return empty when not exists")
    void update_WhenNotExists_ShouldReturnEmpty() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<YogaPose> result = service.update(99L, samplePose);

        assertThat(result).isEmpty();
        verify(repository, times(1)).findById(99L);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("delete() should return true when pose exists")
    void delete_WhenExists_ShouldReturnTrue() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean result = service.delete(1L);

        assertThat(result).isTrue();
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("delete() should return false when pose not exists")
    void delete_WhenNotExists_ShouldReturnFalse() {
        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.delete(99L);

        assertThat(result).isFalse();
        verify(repository, times(1)).existsById(99L);
        verify(repository, never()).deleteById(any());
    }

    @Test
    @DisplayName("findByDifficulty() should return filtered poses")
    void findByDifficulty_ShouldReturnFilteredPoses() {
        List<YogaPose> easyPoses = Arrays.asList(samplePose);

        when(repository.findByDifficulty(1)).thenReturn(easyPoses);

        List<YogaPose> result = service.findByDifficulty(1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDifficulty()).isEqualTo(1);
        verify(repository, times(1)).findByDifficulty(1);
    }

    @Test
    @DisplayName("findByBodyPart() should return filtered poses")
    void findByBodyPart_ShouldReturnFilteredPoses() {
        List<YogaPose> legPoses = Arrays.asList(samplePose);

        when(repository.findByBodyPart("legs")).thenReturn(legPoses);

        List<YogaPose> result = service.findByBodyPart("legs");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBodyPart()).isEqualTo("legs");
        verify(repository, times(1)).findByBodyPart("legs");
    }

    @Test
    @DisplayName("searchByName() should return matching poses")
    void searchByName_ShouldReturnMatchingPoses() {
        List<YogaPose> results = Arrays.asList(samplePose);

        when(repository.findByEnglishNameContainingIgnoreCase("mountain")).thenReturn(results);

        List<YogaPose> result = service.searchByName("mountain");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEnglishName()).containsIgnoringCase("mountain");
        verify(repository, times(1)).findByEnglishNameContainingIgnoreCase("mountain");
    }

    @Test
    @DisplayName("findForBeginners() should return easy poses")
    void findForBeginners_ShouldReturnEasyPoses() {
        List<YogaPose> beginnerPoses = Arrays.asList(samplePose);

        when(repository.findByDifficultyLessThanEqual(2)).thenReturn(beginnerPoses);

        List<YogaPose> result = service.findForBeginners(2);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDifficulty()).isLessThanOrEqualTo(2);
        verify(repository, times(1)).findByDifficultyLessThanEqual(2);
    }
}
