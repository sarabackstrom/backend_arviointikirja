package k25.arviointikirja;



import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.arviointikirja.domain.Lesson;
import k25.arviointikirja.domain.LessonRepository;
import k25.arviointikirja.domain.Performance;
import k25.arviointikirja.domain.PerformanceRepository;
import k25.arviointikirja.domain.Pupil;
import k25.arviointikirja.domain.PupilClass;
import k25.arviointikirja.domain.PupilClassRepository;
import k25.arviointikirja.domain.PupilRepository;
import k25.arviointikirja.domain.Sport;
import k25.arviointikirja.domain.SportRepository;

@SpringBootApplication
public class ArviointikirjaApplication {

	private static final Logger log = LoggerFactory.getLogger(ArviointikirjaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ArviointikirjaApplication.class, args);
	}

	@Bean
	public CommandLineRunner arviointikirjaDemo(PupilRepository pupilRepository, PupilClassRepository pupilClassRepository, SportRepository sportRepository, LessonRepository lessonRepository, PerformanceRepository performanceRepository){
		return (args) -> {
			log.info("Save a couple of pupils and classes");

			PupilClass pupilClass1 = new PupilClass(2024, "A", "8A");
			PupilClass pupilClass2 = new PupilClass(2024, "B", "8B");
			pupilClassRepository.save(pupilClass1);
			pupilClassRepository.save(pupilClass2);

			Sport sport1 = new Sport("Koripallo");
			Sport sport2 = new Sport("Lentopallo");
			sportRepository.save(sport1);
			sportRepository.save(sport2);

			Pupil pupil1 = new Pupil("Sara", "Bäckström", pupilClass1);
			pupilRepository.save(pupil1);
			Pupil pupil2 = new Pupil("Pekka", "Puupää", pupilClass2);
			pupilRepository.save(pupil2);

			Lesson lesson1 = new Lesson("Pienpelit ja -harjoitteet", LocalDate.of(2025, 2, 11), pupilClass1, sport2);
			lessonRepository.save(lesson1);

			Performance performance1 = new Performance(10, 9, "Hyvin pelasi",true, lesson1, pupil1);
			performanceRepository.save(performance1);

		};
	}

}
