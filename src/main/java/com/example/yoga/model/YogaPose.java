package com.example.yoga.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "yoga_poses")
@Schema(description = "Reprezintă o poziție de yoga")
public class YogaPose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID-ul unic al poziției", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Numele în sanscrită este obligatoriu")
    @Column(name = "sanskrit_name")
    @Schema(description = "Numele poziției în sanscrită", example = "Tadasana", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sanskritName;

    @NotBlank(message = "Numele în engleză este obligatoriu")
    @Column(name = "english_name")
    @Schema(description = "Numele poziției în engleză", example = "Mountain Pose", requiredMode = Schema.RequiredMode.REQUIRED)
    private String englishName;

    @Column(length = 1000)
    @Schema(description = "Descrierea detaliată a poziției", example = "Poziție de bază în care stai drept cu greutatea distribuită uniform")
    private String description;

    @Min(value = 1, message = "Nivelul minim este 1")
    @Max(value = 5, message = "Nivelul maxim este 5")
    @Schema(description = "Nivel de dificultate (1=ușor, 5=avansat)", example = "1", minimum = "1", maximum = "5")
    private int difficulty;

    @Column(length = 500)
    @Schema(description = "Beneficiile practicării acestei poziții", example = "Îmbunătățește postura și echilibrul")
    private String benefits;

    @Column(name = "body_part")
    @Schema(description = "Zona principală a corpului vizată", example = "picioare")
    private String bodyPart;

    // Constructori
    public YogaPose() {}

    public YogaPose(String sanskritName, String englishName, String description,
                    int difficulty, String benefits, String bodyPart) {
        this.sanskritName = sanskritName;
        this.englishName = englishName;
        this.description = description;
        this.difficulty = difficulty;
        this.benefits = benefits;
        this.bodyPart = bodyPart;
    }

    // Getters și Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSanskritName() {
        return sanskritName;
    }

    public void setSanskritName(String sanskritName) {
        this.sanskritName = sanskritName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
}
