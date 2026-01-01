package com.example.yoga.controller;

import com.example.yoga.model.YogaPose;
import com.example.yoga.service.YogaPoseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(YogaPoseController.class)
class YogaPoseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YogaPoseService service;

    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("GET /api/poses - Should return all poses")
    void getAllPoses_ShouldReturnListOfPoses() throws Exception {
        YogaPose pose2 = new YogaPose("Balasana", "Child's Pose", "Resting pose", 1, "Relaxation", "back");
        pose2.setId(2L);
        List<YogaPose> poses = Arrays.asList(samplePose, pose2);

        when(service.findAll()).thenReturn(poses);

        mockMvc.perform(get("/api/poses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].englishName", is("Mountain Pose")))
                .andExpect(jsonPath("$[1].englishName", is("Child's Pose")));

        verify(service, times(1)).findAll();
    }

    @Test
    @DisplayName("GET /api/poses/{id} - Should return pose when exists")
    void getPoseById_WhenExists_ShouldReturnPose() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(samplePose));

        mockMvc.perform(get("/api/poses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.sanskritName", is("Tadasana")))
                .andExpect(jsonPath("$.englishName", is("Mountain Pose")));

        verify(service, times(1)).findById(1L);
    }

    @Test
    @DisplayName("GET /api/poses/{id} - Should return 404 when not exists")
    void getPoseById_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/poses/99"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(99L);
    }

    @Test
    @DisplayName("POST /api/poses - Should create new pose")
    void createPose_ShouldReturnCreatedPose() throws Exception {
        YogaPose newPose = new YogaPose("Vrksasana", "Tree Pose", "Balance pose", 2, "Balance", "legs");
        YogaPose savedPose = new YogaPose("Vrksasana", "Tree Pose", "Balance pose", 2, "Balance", "legs");
        savedPose.setId(3L);

        when(service.save(any(YogaPose.class))).thenReturn(savedPose);

        mockMvc.perform(post("/api/poses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPose)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.englishName", is("Tree Pose")));

        verify(service, times(1)).save(any(YogaPose.class));
    }

    @Test
    @DisplayName("PUT /api/poses/{id} - Should update existing pose")
    void updatePose_WhenExists_ShouldReturnUpdatedPose() throws Exception {
        YogaPose updatedPose = new YogaPose("Tadasana", "Mountain Pose Updated", "Standing tall", 1, "Better posture", "legs");
        updatedPose.setId(1L);

        when(service.update(eq(1L), any(YogaPose.class))).thenReturn(Optional.of(updatedPose));

        mockMvc.perform(put("/api/poses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPose)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.englishName", is("Mountain Pose Updated")));

        verify(service, times(1)).update(eq(1L), any(YogaPose.class));
    }

    @Test
    @DisplayName("PUT /api/poses/{id} - Should return 404 when not exists")
    void updatePose_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.update(eq(99L), any(YogaPose.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/poses/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePose)))
                .andExpect(status().isNotFound());

        verify(service, times(1)).update(eq(99L), any(YogaPose.class));
    }

    @Test
    @DisplayName("DELETE /api/poses/{id} - Should delete existing pose")
    void deletePose_WhenExists_ShouldReturn204() throws Exception {
        when(service.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/poses/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1L);
    }

    @Test
    @DisplayName("DELETE /api/poses/{id} - Should return 404 when not exists")
    void deletePose_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.delete(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/poses/99"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).delete(99L);
    }

    @Test
    @DisplayName("GET /api/poses/difficulty/{level} - Should filter by difficulty")
    void getPosesByDifficulty_ShouldReturnFilteredPoses() throws Exception {
        List<YogaPose> easyPoses = Arrays.asList(samplePose);

        when(service.findByDifficulty(1)).thenReturn(easyPoses);

        mockMvc.perform(get("/api/poses/difficulty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].difficulty", is(1)));

        verify(service, times(1)).findByDifficulty(1);
    }

    @Test
    @DisplayName("GET /api/poses/search?name=mountain - Should search by name")
    void searchPoses_ShouldReturnMatchingPoses() throws Exception {
        List<YogaPose> results = Arrays.asList(samplePose);

        when(service.searchByName("mountain")).thenReturn(results);

        mockMvc.perform(get("/api/poses/search").param("name", "mountain"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].englishName", containsString("Mountain")));

        verify(service, times(1)).searchByName("mountain");
    }

    @Test
    @DisplayName("GET /api/poses/beginners - Should return beginner poses")
    void getBeginnerPoses_ShouldReturnEasyPoses() throws Exception {
        List<YogaPose> beginnerPoses = Arrays.asList(samplePose);

        when(service.findForBeginners(2)).thenReturn(beginnerPoses);

        mockMvc.perform(get("/api/poses/beginners").param("maxLevel", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(service, times(1)).findForBeginners(2);
    }
}
