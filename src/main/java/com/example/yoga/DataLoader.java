package com.example.yoga;

import com.example.yoga.model.YogaPose;
import com.example.yoga.repository.YogaPoseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final YogaPoseRepository repository;

    public DataLoader(YogaPoseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        // Adaugă câteva poziții de yoga pentru testare
        repository.save(new YogaPose(
                "Tadasana",
                "Mountain Pose",
                "Poziția de bază în care stai drept cu picioarele unite și brațele pe lângă corp.",
                1,
                "Îmbunătățește postura, întărește coapsele și gleznele",
                "picioare"
        ));

        repository.save(new YogaPose(
                "Adho Mukha Svanasana",
                "Downward Dog",
                "Poziție în formă de V inversat, cu mâinile și picioarele pe podea.",
                2,
                "Întărește brațele și picioarele, calmează mintea",
                "corp întreg"
        ));

        repository.save(new YogaPose(
                "Virabhadrasana I",
                "Warrior I",
                "Poziție de luptător cu un picior înainte, genunchiul îndoit, brațele ridicate.",
                2,
                "Întărește picioarele, deschide șoldurile și pieptul",
                "picioare"
        ));

        repository.save(new YogaPose(
                "Virabhadrasana II",
                "Warrior II",
                "Poziție de luptător cu brațele întinse lateral, privirea peste mâna din față.",
                2,
                "Întărește picioarele, îmbunătățește echilibrul și concentrarea",
                "picioare"
        ));

        repository.save(new YogaPose(
                "Balasana",
                "Child's Pose",
                "Poziție de odihnă cu genunchii îndoiți, fruntea pe podea, brațele întinse.",
                1,
                "Relaxează spatele, calmează sistemul nervos",
                "spate"
        ));

        repository.save(new YogaPose(
                "Bhujangasana",
                "Cobra Pose",
                "Întindere a spatelui cu abdomenul pe podea și pieptul ridicat.",
                2,
                "Întărește coloana, deschide pieptul și plămânii",
                "spate"
        ));

        repository.save(new YogaPose(
                "Navasana",
                "Boat Pose",
                "Echilibru pe ischioane cu picioarele și trunchiul ridicate în formă de V.",
                3,
                "Întărește mușchii abdominali și flexorii șoldului",
                "core"
        ));

        repository.save(new YogaPose(
                "Vrksasana",
                "Tree Pose",
                "Echilibru pe un picior cu celălalt sprijinit pe coapsa interioară.",
                2,
                "Îmbunătățește echilibrul și concentrarea",
                "picioare"
        ));

        repository.save(new YogaPose(
                "Sirsasana",
                "Headstand",
                "Inversiune cu echilibru pe cap și antebrațe, picioarele în sus.",
                5,
                "Îmbunătățește circulația, întărește core-ul și umerii",
                "corp întreg"
        ));

        repository.save(new YogaPose(
                "Savasana",
                "Corpse Pose",
                "Poziție de relaxare completă, întins pe spate.",
                1,
                "Relaxare profundă, reduce stresul",
                "corp întreg"
        ));

        System.out.println("✓ Am încărcat 10 poziții de yoga în baza de date!");
    }
}
