package com.example.yoga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "yoga_poses")
public class YogaPose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele în sanscrită este obligatoriu")
    @Column(name = "sanskrit_name")
    private String sanskritName;

    @NotBlank(message = "Numele în engleză este obligatoriu")
    @Column(name = "english_name")
    private String englishName;

    @Column(length = 1000)
    private String description;

    @Min(value = 1, message = "Nivelul minim este 1")
    @Max(value = 5, message = "Nivelul maxim este 5")
    private int difficulty; // 1-5

    @Column(length = 500)
    private String benefits;

    @Column(name = "body_part")
    private String bodyPart; // ex: "spate", "picioare", "core"

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
