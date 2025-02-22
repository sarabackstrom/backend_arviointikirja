package k25.arviointikirja;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner arviointikirjaDemo(PupilRepository pupilRepository, PupilClassRepository pupilClassRepository, SportRepository sportRepository){
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
		};
	}

}
