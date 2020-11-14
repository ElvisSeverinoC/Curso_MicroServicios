package pe.edu.galaxy.training.java.ms.gestion.talleres.msgestiontallerestallergradleyml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) 
public class MsGestionTalleresTallerGradleYmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionTalleresTallerGradleYmlApplication.class, args);
	}

}
