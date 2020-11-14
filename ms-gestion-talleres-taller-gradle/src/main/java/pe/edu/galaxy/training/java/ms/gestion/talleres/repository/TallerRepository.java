package pe.edu.galaxy.training.java.ms.gestion.talleres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.edu.galaxy.training.java.ms.gestion.talleres.entity.TallerEntity;

public interface TallerRepository
		extends JpaRepository<TallerEntity, Long>, PagingAndSortingRepository<TallerEntity, Long> {

	@Query("select p from TallerEntity p wehere p.estado='1'")
	public List<TallerEntity> findAllCustom();
}
