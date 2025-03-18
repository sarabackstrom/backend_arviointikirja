package k25.arviointikirja;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import k25.arviointikirja.domain.PupilRepository;


@SpringBootTest
class ArviointikirjaApplicationTests {

	@Autowired
	private PupilRepository pRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testDatabaseConnection(){
		assertThat(pRepository).isNotNull();
		assertThat(pRepository.count()).isNotNull();
	}

}
