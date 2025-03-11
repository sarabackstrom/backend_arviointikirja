package k25.arviointikirja;



import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.arviointikirja.domain.AppUser;
import k25.arviointikirja.domain.AppUserRepository;
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
	public CommandLineRunner arviointikirjaDemo(PupilRepository pupilRepository, PupilClassRepository pupilClassRepository, SportRepository sportRepository, LessonRepository lessonRepository, PerformanceRepository performanceRepository, AppUserRepository aRepository){
		return (args) -> {
			log.info("Save a couple of pupils and classes");

			PupilClass pupilClass1 = new PupilClass(2024, "A", "8A");
			PupilClass pupilClass2 = new PupilClass(2024, "B", "8B");
			if(pupilClassRepository.count() == 0){
			pupilClassRepository.save(pupilClass1);
			pupilClassRepository.save(pupilClass2);}

			Sport sport1 = new Sport("Koripallo");
			Sport sport2 = new Sport("Lentopallo");
			Sport sport3 = new Sport("Uinti");
			Sport sport4 = new Sport("Luistelu");
			Sport sport5 = new Sport("Jääpelit");
			if(sportRepository.count() == 0){
			sportRepository.save(sport1);
			sportRepository.save(sport2);
			sportRepository.save(sport3);
			sportRepository.save(sport4);
			sportRepository.save(sport5);}

			Pupil pupil1 = new Pupil("Sara", "Bäckström", pupilClass1);
			Pupil pupil2 = new Pupil("Pekka", "Puupää", pupilClass2);
			if(pupilRepository.count() == 0){
			pupilRepository.save(pupil1);
			pupilRepository.save(pupil2);}

			Lesson lesson1 = new Lesson("Pienpelit ja -harjoitteet", LocalDate.of(2025, 2, 11), pupilClass1, sport2);
			Lesson lesson2 = new Lesson("Koripallo", LocalDate.of(2025, 3, 10), pupilClass2, sport1);
			if(lessonRepository.count() == 0){
			lessonRepository.save(lesson1);
			lessonRepository.save(lesson2);}

			Performance performance1 = new Performance(10, 9, "Hyvin pelasi",true, lesson1, pupil1);
			Performance performance2 = new Performance(7, 6, "Laiskaa",true, lesson2, pupil1);
			if(performanceRepository.count() == 0){
			performanceRepository.save(performance1);
			performanceRepository.save(performance2);}

			AppUser user1 = new AppUser("user", "$2a$10$eHim7bV7DQWPo56u61UiQ.Mnygwm3M1EpSrW/WWLPDypjLKb7TKji", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$EoyrFUzDQ9ldC158NyNAo..bzj2E/FsdY/fzviP.kfL0tnYvmJP4G", "ADMIN");
			if(aRepository.count() == 0){
			aRepository.save(user1);
			aRepository.save(user2);}
		};
	}

}
