package pe.edu.galaxy.training.java.ms.gestion.talleres.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.gestion.talleres.entity.TallerEntity;
import pe.edu.galaxy.training.java.ms.gestion.talleres.entity.TallerSituacionEntity;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(JUnitPlatform.class)
@DataJpaTest
public class TallerRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private TallerRepository tallerRepository;
	
	private TallerEntity defaultTallerEntity;
	
	@BeforeEach
    public void setup() {
		defaultTallerEntity = TallerEntity
								.builder()
								.nombre("Taller Test")
								.descripcion("Taller Test Descripción")
								.duracion(10)
								.idInstructor(1)
								.tallerSituacion(TallerSituacionEntity.builder().id(1L).build())
								.estado("1")
								.build();
    }
	
	@Test
	@Commit
    public void testSaveTaller() {
		
		System.out.println(defaultTallerEntity);
		
		TallerEntity savedTallerEntity = tallerRepository.save(defaultTallerEntity);
 
        assertThat(savedTallerEntity.getId()).isNotNull();
        
        assertThat(entityManager.find(TallerEntity.class, savedTallerEntity.getId())).isNotNull();
    }

	//@Test
    public void testShouldFindByIdWhenTallerExists() {
		TallerEntity savedTallerEntity = entityManager.persistAndFlush(defaultTallerEntity);
		//assertThat(true);
        assertThat(tallerRepository.findById(savedTallerEntity.getId())).isEqualTo(Optional.of(savedTallerEntity));
    }
	
	@Test
    public void testFindByIdShouldReturnEmptyWhenTallerNotFound() {
        long idNotValid = -1;
        assertThat(tallerRepository.findById(idNotValid)).isEqualTo(Optional.empty());
    }
	
	@Test
    public void testFindByIdShouldReturnNotEmptyWhenTallerFound() {
        long idValid = 1;
        assertThat(tallerRepository.findById(idValid)).isNotNull();
    }
	
}
