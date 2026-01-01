package com.example.yoga.controller;

import com.example.yoga.model.YogaPose;
import com.example.yoga.service.YogaPoseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poses")
@Tag(name = "Yoga Poses", description = "API pentru gestionarea pozițiilor de yoga")
public class YogaPoseController {

    private final YogaPoseService service;

    public YogaPoseController(YogaPoseService service) {
        this.service = service;
    }

    @Operation(summary = "Obține toate pozițiile", description = "Returnează lista completă de poziții yoga din catalog")
    @ApiResponse(responseCode = "200", description = "Lista de poziții returnată cu succes")
    @GetMapping
    public List<YogaPose> getAllPoses() {
        return service.findAll();
    }

    @Operation(summary = "Obține o poziție după ID", description = "Returnează detaliile unei poziții specifice")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Poziție găsită"),
            @ApiResponse(responseCode = "404", description = "Poziție negăsită")
    })
    @GetMapping("/{id}")
    public ResponseEntity<YogaPose> getPoseById(
            @Parameter(description = "ID-ul poziției") @PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Creează o poziție nouă", description = "Adaugă o nouă poziție yoga în catalog")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Poziție creată cu succes"),
            @ApiResponse(responseCode = "400", description = "Date invalide")
    })
    @PostMapping
    public ResponseEntity<YogaPose> createPose(
            @Parameter(description = "Datele poziției") @Valid @RequestBody YogaPose pose) {
        YogaPose savedPose = service.save(pose);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPose);
    }

    @Operation(summary = "Actualizează o poziție", description = "Modifică datele unei poziții existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Poziție actualizată cu succes"),
            @ApiResponse(responseCode = "404", description = "Poziție negăsită"),
            @ApiResponse(responseCode = "400", description = "Date invalide")
    })
    @PutMapping("/{id}")
    public ResponseEntity<YogaPose> updatePose(
            @Parameter(description = "ID-ul poziției") @PathVariable Long id,
            @Parameter(description = "Datele actualizate") @Valid @RequestBody YogaPose pose) {
        return service.update(id, pose)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Șterge o poziție", description = "Elimină o poziție din catalog")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Poziție ștearsă cu succes"),
            @ApiResponse(responseCode = "404", description = "Poziție negăsită")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePose(
            @Parameter(description = "ID-ul poziției") @PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Filtrează după dificultate", description = "Returnează pozițiile cu un anumit nivel de dificultate (1-5)")
    @ApiResponse(responseCode = "200", description = "Lista de poziții filtrată")
    @GetMapping("/difficulty/{level}")
    public List<YogaPose> getPosesByDifficulty(
            @Parameter(description = "Nivel dificultate (1=ușor, 5=avansat)") @PathVariable int level) {
        return service.findByDifficulty(level);
    }

    @Operation(summary = "Filtrează după zona corpului", description = "Returnează pozițiile care vizează o anumită zonă corporală")
    @ApiResponse(responseCode = "200", description = "Lista de poziții filtrată")
    @GetMapping("/bodypart/{part}")
    public List<YogaPose> getPosesByBodyPart(
            @Parameter(description = "Zona corpului (ex: picioare, spate, core)") @PathVariable String part) {
        return service.findByBodyPart(part);
    }

    @Operation(summary = "Caută după nume", description = "Caută poziții după numele în engleză (căutare parțială)")
    @ApiResponse(responseCode = "200", description = "Lista de poziții găsite")
    @GetMapping("/search")
    public List<YogaPose> searchPoses(
            @Parameter(description = "Termen de căutare") @RequestParam String name) {
        return service.searchByName(name);
    }

    @Operation(summary = "Poziții pentru începători", description = "Returnează pozițiile cu dificultate sub nivelul specificat")
    @ApiResponse(responseCode = "200", description = "Lista de poziții pentru începători")
    @GetMapping("/beginners")
    public List<YogaPose> getBeginnersPoses(
            @Parameter(description = "Nivel maxim de dificultate") @RequestParam(defaultValue = "2") int maxLevel) {
        return service.findForBeginners(maxLevel);
    }
}
