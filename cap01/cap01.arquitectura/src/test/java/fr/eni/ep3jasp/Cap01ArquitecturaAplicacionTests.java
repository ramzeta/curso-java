package fr.eni.ep3jasp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:applicationContext-test.xml"})
class Cap01ArquitecturaAplicacionTests {

	private static final Logger logger = LoggerFactory.getLogger(Cap01ArquitecturaAplicacionTests.class);

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		logger.info("log en info");
		logger.debug("log en debug");
		logger.warn("log en warning");
		logger.error("log en error");
		Cap01ArquitecturaAplicacion.main(new String[] {});
	}
}
