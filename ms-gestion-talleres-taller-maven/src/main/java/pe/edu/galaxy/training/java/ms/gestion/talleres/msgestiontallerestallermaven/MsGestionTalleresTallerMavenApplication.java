package pe.edu.galaxy.training.java.ms.gestion.talleres.msgestiontallerestallermaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) 
public class MsGestionTalleresTallerMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionTalleresTallerMavenApplication.class, args);
	}

}
