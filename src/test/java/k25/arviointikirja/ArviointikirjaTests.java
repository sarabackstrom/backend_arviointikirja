package k25.arviointikirja;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClass;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;
import k25.arviointikirja.domain.Sport;
import k25.arviointikirja.domain.SportRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ArviointikirjaTests {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    PupilClassRepository pupilClassRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Test
    public void findPerformancesShouldReturnAtLeastOnePerformance() throws Exception {
        assertThat(performanceRepository.count()).isGreaterThan(0);
    }

    @Test
    public void saveLesson() throws Exception {
        Sport sport = new Sport("Pesäpallo");
        sportRepository.save(sport);
        PupilClass pupilClass = new PupilClass(2025, "A", "7A");
        pupilClassRepository.save(pupilClass);
        Lesson lesson = new Lesson("Ryhmäytyminen", LocalDate.of(2025, 3, 18), pupilClass, sport);
        lessonRepository.save(lesson);
        assertThat(lesson.getLessonId()).isNotNull();
    }

    @Test
    public void getCorrectData(){
        Optional<Pupil> pupil = pupilRepository.findById((long) 802);
        assertThat(pupil).isPresent();
        assertThat(pupil.get().getLastName()).isEqualTo("Porkkana");
        assertThat(pupil.get().getFirstName()).isEqualTo("Pirjo");
    }


}
