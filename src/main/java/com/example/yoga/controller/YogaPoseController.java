package com.example.yoga.controller;

import com.example.yoga.model.YogaPose;
import com.example.yoga.service.YogaPoseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poses")
public class YogaPoseController {

    private final YogaPoseService service;

    public YogaPoseController(YogaPoseService service) {
        this.service = service;
    }

    // GET /api/poses - returnează toate pozițiile
    @GetMapping
    public List<YogaPose> getAllPoses() {
        return service.findAll();
    }

    // GET /api/poses/{id} - returnează o poziție după ID
    @GetMapping("/{id}")
    public ResponseEntity<YogaPose> getPoseById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/poses - creează o nouă poziție
    @PostMapping
    public ResponseEntity<YogaPose> createPose(@Valid @RequestBody YogaPose pose) {
        YogaPose savedPose = service.save(pose);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPose);
    }

    // PUT /api/poses/{id} - actualizează o poziție
    @PutMapping("/{id}")
    public ResponseEntity<YogaPose> updatePose(@PathVariable Long id,
                                                @Valid @RequestBody YogaPose pose) {
        return service.update(id, pose)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/poses/{id} - șterge o poziție
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePose(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/poses/difficulty/{level} - filtrează după dificultate
    @GetMapping("/difficulty/{level}")
    public List<YogaPose> getPosesByDifficulty(@PathVariable int level) {
        return service.findByDifficulty(level);
    }

    // GET /api/poses/bodypart/{part} - filtrează după partea corpului
    @GetMapping("/bodypart/{part}")
    public List<YogaPose> getPosesByBodyPart(@PathVariable String part) {
        return service.findByBodyPart(part);
    }

    // GET /api/poses/search?name=warrior - caută după nume
    @GetMapping("/search")
    public List<YogaPose> searchPoses(@RequestParam String name) {
        return service.searchByName(name);
    }

    // GET /api/poses/beginners?maxLevel=2 - poziții pentru începători
    @GetMapping("/beginners")
    public List<YogaPose> getBeginnerssPoses(
            @RequestParam(defaultValue = "2") int maxLevel) {
        return service.findForBeginners(maxLevel);
    }
}
