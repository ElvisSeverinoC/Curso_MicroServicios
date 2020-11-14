package pe.edu.galaxy.training.java.ms.gestion.talleres.msgestiontallerestallermavenyml;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talleres/v1")
public class TallerApi {

	@GetMapping
	public String demo() {

		return "Hola Mundo";
	}
}
