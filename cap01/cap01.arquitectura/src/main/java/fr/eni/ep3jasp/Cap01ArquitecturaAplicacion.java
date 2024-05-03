package fr.eni.ep3jasp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class Cap01ArquitecturaAplicacion {

	/**
	 * El logger jcl-over-slf4j
	 */
	private static final Logger logger = LoggerFactory.getLogger(Cap01ArquitecturaAplicacion.class);

    public static void main(String[] args) {
        SpringApplication.run(Cap01ArquitecturaAplicacion.class, args);
    }

}
