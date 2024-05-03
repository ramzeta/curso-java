package fr.eni.ep3jasp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpingBootSupportApplication implements CommandLineRunner {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SpingBootSupportApplication.class);

	@Autowired
	private ConfigurationYml miConfiguracion;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpingBootSupportApplication.class);
		app.run();
	}


	public void run(String... args) throws Exception {
		log.info("Hello");
		log.info("Instance: {}", miConfiguracion.getInstance());
		log.info("Environment: {}", miConfiguracion.getEnvironment());
	}

}
