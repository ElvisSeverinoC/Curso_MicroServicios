package pe.edu.galaxy.training.java.ms.gestion.talleres.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.gestion.talleres.controller.common.Response;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerSituacionDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.service.TallerService;

@Slf4j
@RunWith(JUnitPlatform.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TallerClientTest {
	
	 @Autowired
	    private TestRestTemplate restTemplate;
	 
		@Autowired
		private TallerService tallerService;
		
		private TallerDTO defaultTallerDTO;
		
		@BeforeEach
	    public void setup() {
			defaultTallerDTO = TallerDTO
					.builder()
					.nombre("Taller Test Clientex")
					.descripcion("Taller Test Descripción")
					.duracion(10)
					.idInstructor(1)
					.tallerSituacion((TallerSituacionDTO) TallerSituacionDTO.builder().id(1L).build())
					//.tallerSituacion(TallerSituacionDTO.builder().id(1L).build())
					.estado("1")
					.build();
	    }
	 
		 @Test
		    public void testShouldReturnTaller() {
		    	
		        ResponseEntity<Response> tallerResponseEntity = 
		        		this.restTemplate.getForEntity("/talleres/v1", Response.class);
		        //System.out.println("tallerResponseEntity" +tallerResponseEntity);
		       log.info("tallerResponseEntity" +tallerResponseEntity);
		       log.info("tallerResponseEntity.getStatusCode()" +tallerResponseEntity.getStatusCode());
		        assertThat(tallerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		        assertThat(tallerResponseEntity.getBody().getData()).isNotNull();
		    }

		
	    //@Test
	    public void testShouldReturnCreatedWhenValidTaller() {
	    	
	        ResponseEntity<Response> tallerResponseEntity = 
	        		this.restTemplate.postForEntity("/talleres/v1", defaultTallerDTO, Response.class);
	       //log.info("tallerResponseEntity" +tallerResponseEntity);
	       //log.info("tallerResponseEntity.getStatusCode()" +tallerResponseEntity.getStatusCode());
	        assertThat(tallerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	        assertThat(tallerResponseEntity.getBody().getCodigo()).isEqualTo(1);
	    }
	 
	    //@Test
	    public void testShouldFindTallerWhenExists() throws Exception {
	    	TallerDTO savedTallerDTO = tallerService.insert(defaultTallerDTO);
	 
	        ResponseEntity<TallerDTO> tallerResponseEntity = 
	        		this.restTemplate.getForEntity("/talleres/v1/" + savedTallerDTO.getId(), TallerDTO.class);
	 
	        assertThat(tallerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	        assertThat(tallerResponseEntity.getBody().getId()).isEqualTo(savedTallerDTO.getId());
	    }
	    
	    //@Test
	    public void testShouldReturn404WhenTallerMissing() throws Exception {
	        Long idNotValid = 100L;
	        ResponseEntity<TallerDTO> tallerResponseEntity = 
	        		this.restTemplate.getForEntity("/talleres/v1/" + idNotValid, TallerDTO.class);
	 
	        assertThat(tallerResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	    }
}
