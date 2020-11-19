package pe.edu.galaxy.training.java.ms.gestion.talleres.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerSituacionDTO;

@RunWith(JUnitPlatform.class)
@SpringBootTest
public class TallerServiceTest {
	
	
	@Autowired
	private TallerService tallerService;

	private TallerDTO defaultTallerDTO;

	@BeforeEach
	public void setup() {
		defaultTallerDTO = TallerDTO
				.builder()
				.nombre("Tx")
				.descripcion("Taller Test Descripción")
				.duracion(10)
				.idInstructor(1)
				.tallerSituacion(TallerSituacionDTO.builder().id(1L).build())
				//.tallerSituacion(TallerSituacionDTO.builder().id(1L).build())
				.estado("1")
				.build();
	}

	@Test
	public void testShouldInsertTaller() {
		System.out.println(defaultTallerDTO);
		try {
			TallerDTO insertTallerDTO = tallerService.insert(defaultTallerDTO);
			System.out.println("insertTallerDTO" + insertTallerDTO);
			assertThat(insertTallerDTO.getId()).isNotNull();
		} catch (Exception e) {
			// e.printStackTrace();
			assertNull(e.getMessage());
		}

	}

	// @Test
	public void testFindByIdShouldReturnEmptyWhenTallerNotFound() {
		Long idNotValid = 100L;
		TallerDTO prmTallerDTO = TallerDTO.builder().id(idNotValid).build();
		try {
			assertThat(tallerService.findById(prmTallerDTO)).isNull();
		} catch (Exception e) {
			assertNull(e.getMessage());
		}
	}

	// @Test
	public void testFindByIdShouldReturnNotEmptyWhenTallerFound() {
		Long idNotValid = -100L;
		TallerDTO prmTallerDTO = TallerDTO.builder().id(idNotValid).build();
		try {
			assertThat(tallerService.findById(prmTallerDTO)).isNull();
		} catch (Exception e) {
			assertNull(e.getMessage());
		}
	}

}
