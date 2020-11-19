package pe.edu.galaxy.training.java.ms.gestion.talleres.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.gestion.talleres.controller.common.Response;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.service.TallerService;
import pe.edu.galaxy.training.java.ms.gestion.talleres.service.exception.ExceptionService;

@EqualsAndHashCode(callSuper = false)
@Data
@Slf4j
@RestController
@RequestMapping("/talleres/v1")
public class TallerController extends GenericController {

	private static final long serialVersionUID = -5234347835982496753L;

	@Autowired
	private TallerService tallerService;

	@GetMapping
	public ResponseEntity<Response> getAll() {

		try {

			List<TallerDTO> listTallerDTO = this.getTallerService().findAll();

			if (listTallerDTO == null || listTallerDTO.size() == 0) {
				Response response = Response.builder().codigo(0).mensaje("No existe talleres a consultar").build();
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
			} else {
				Response response = Response.builder().codigo(1).mensaje("Exito a consultar talleres ")
						.data(listTallerDTO).build();
				return ResponseEntity.ok(response);
			}

		} catch (ExceptionService e) {
			log.error(e.getMessage());
			Response responsebody = Response.builder().codigo(-1).mensaje("Error al consultar Talleres").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responsebody);
		}
	}

	
	@PostMapping
	public ResponseEntity<Response> insert(@RequestBody TallerDTO tallerDTO) {

		try {

			TallerDTO oTallerDTO = this.getTallerService().insert(tallerDTO);

			if (oTallerDTO == null) {
				Response response = Response.builder().codigo(0).mensaje("Error al registrar").build();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			Response response = Response.builder().codigo(1).mensaje("Exito al regitrar").data(oTallerDTO).build();
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (ExceptionService e) {

			log.error(e.getMessage());

			Response response = Response.builder().codigo(-1).mensaje("Error al registrar").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> findById(@PathVariable("id") Long id) {

		try {

			TallerDTO listTallerDTO = this.getTallerService().findById(TallerDTO.builder().id(id).build()).orElse(null);

			if (listTallerDTO == null) {
				Response response = Response
						.builder()
						.codigo(0)
						.mensaje("No existe talleres a consultar con el ID : "+id)
						.build();
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
			} else {
				Response response = Response
						.builder()
						.codigo(1)
						.mensaje("Exito a consultar talleres ")
						.data(listTallerDTO).build();
				return ResponseEntity.ok(response);
			}

		} catch (ExceptionService e) {
			log.error(e.getMessage());
			Response responsebody = Response.builder().codigo(-1).mensaje("Error al consultar Talleres").build();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responsebody);
		}

	}

}
