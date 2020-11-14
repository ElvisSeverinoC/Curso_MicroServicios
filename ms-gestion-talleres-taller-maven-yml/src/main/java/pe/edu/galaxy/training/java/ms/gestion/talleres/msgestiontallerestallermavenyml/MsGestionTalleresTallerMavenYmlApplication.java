package pe.edu.galaxy.training.java.ms.gestion.talleres.msgestiontallerestallermavenyml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MsGestionTalleresTallerMavenYmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionTalleresTallerMavenYmlApplication.class, args);
	}

}
