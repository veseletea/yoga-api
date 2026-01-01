# Yoga API

REST API pentru gestionarea unui catalog de poziții yoga, construit cu **Java** și **Spring Boot**.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green?style=flat-square&logo=springboot)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

## Despre proiect

Acest proiect este o aplicație simplă pentru a învăța conceptele fundamentale ale dezvoltării backend cu Spring Boot:

- **Spring Web** - REST API
- **Spring Data JPA** - Persistență date
- **H2 Database** - Bază de date în memorie
- **Bean Validation** - Validare input

## Rulare

### Cerințe
- Java 17+
- Maven (sau folosește wrapper-ul inclus)

### Pornire aplicație

```bash
# Cu Maven
mvn spring-boot:run

# Sau cu wrapper-ul
./mvnw spring-boot:run
```

Aplicația pornește la: **http://localhost:8080**

## API Endpoints

| Metodă | Endpoint | Descriere |
|--------|----------|-----------|
| `GET` | `/api/poses` | Toate pozițiile |
| `GET` | `/api/poses/{id}` | O poziție după ID |
| `POST` | `/api/poses` | Creează poziție nouă |
| `PUT` | `/api/poses/{id}` | Actualizează poziție |
| `DELETE` | `/api/poses/{id}` | Șterge poziție |
| `GET` | `/api/poses/difficulty/{level}` | Filtrează după dificultate (1-5) |
| `GET` | `/api/poses/bodypart/{part}` | Filtrează după zona corpului |
| `GET` | `/api/poses/search?name=...` | Caută după nume |
| `GET` | `/api/poses/beginners?maxLevel=2` | Poziții pentru începători |

## Exemple de utilizare

### Obține toate pozițiile
```bash
curl http://localhost:8080/api/poses
```

### Adaugă o poziție nouă
```bash
curl -X POST http://localhost:8080/api/poses \
  -H "Content-Type: application/json" \
  -d '{
    "sanskritName": "Padmasana",
    "englishName": "Lotus Pose",
    "description": "Poziție clasică de meditație",
    "difficulty": 3,
    "benefits": "Flexibilitate șolduri, calm mental",
    "bodyPart": "picioare"
  }'
```

### Caută poziții
```bash
curl "http://localhost:8080/api/poses/search?name=warrior"
```

### Actualizează o poziție
```bash
curl -X PUT http://localhost:8080/api/poses/1 \
  -H "Content-Type: application/json" \
  -d '{
    "sanskritName": "Tadasana",
    "englishName": "Mountain Pose Updated",
    "difficulty": 1,
    "benefits": "Îmbunătățește postura",
    "bodyPart": "picioare"
  }'
```

## Model de date

```java
YogaPose {
    Long id;              // Auto-generat
    String sanskritName;  // Nume în sanscrită (obligatoriu)
    String englishName;   // Nume în engleză (obligatoriu)
    String description;   // Descriere
    int difficulty;       // Nivel 1-5
    String benefits;      // Beneficii pentru sănătate
    String bodyPart;      // Partea corpului vizată
}
```

## Structura proiectului

```
src/main/java/com/example/yoga/
├── YogaApiApplication.java      # Clasa principală
├── DataLoader.java              # Date demo la pornire
├── controller/
│   ├── HomeController.java      # Pagina de start
│   └── YogaPoseController.java  # REST endpoints
├── model/
│   └── YogaPose.java            # Entitatea JPA
├── repository/
│   └── YogaPoseRepository.java  # Acces la date
└── service/
    └── YogaPoseService.java     # Logica de business
```

## H2 Console

Baza de date H2 are o consolă web accesibilă la: **http://localhost:8080/h2-console**

- **JDBC URL:** `jdbc:h2:mem:yogadb`
- **Username:** `sa`
- **Password:** *(gol)*

## Date demo

Aplicația vine cu 10 poziții de yoga pre-încărcate:

| Poziție | Dificultate | Zona |
|---------|-------------|------|
| Mountain Pose (Tadasana) | 1 | picioare |
| Downward Dog | 2 | corp întreg |
| Warrior I & II | 2 | picioare |
| Child's Pose | 1 | spate |
| Cobra Pose | 2 | spate |
| Boat Pose | 3 | core |
| Tree Pose | 2 | picioare |
| Headstand | 5 | corp întreg |
| Corpse Pose | 1 | corp întreg |

---

Creat cu Spring Boot pentru a învăța dezvoltarea de aplicații Java.

