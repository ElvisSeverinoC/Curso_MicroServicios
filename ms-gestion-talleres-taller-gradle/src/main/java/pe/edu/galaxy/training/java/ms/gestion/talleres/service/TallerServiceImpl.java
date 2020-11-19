package pe.edu.galaxy.training.java.ms.gestion.talleres.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerSituacionDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.entity.TallerEntity;
import pe.edu.galaxy.training.java.ms.gestion.talleres.entity.TallerSituacionEntity;
import pe.edu.galaxy.training.java.ms.gestion.talleres.repository.TallerRepository;
import pe.edu.galaxy.training.java.ms.gestion.talleres.service.exception.ExceptionService;

@Slf4j
@Service
@Data
@EqualsAndHashCode(callSuper = false)
public class TallerServiceImpl extends GenericServiceImpl implements TallerService {

	@Autowired
	private TallerRepository tallerRepository;

	@Override
	public List<TallerDTO> findAll() throws ExceptionService {

		try {

			List<TallerEntity> lstTallerEntity = this.getTallerRepository().findAllCustom();

			return lstTallerEntity.stream().map(tallerEntity -> this.getTallerDTO(tallerEntity))
					.collect(Collectors.toList());

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService(e);
		}
	}

	@Override
	public Optional<TallerDTO> findById(TallerDTO tallerDTO) throws ExceptionService {

		try {

			Optional<TallerEntity> optTallerEntity = this.getTallerRepository().findById(tallerDTO.getId());

			if (optTallerEntity == null) {
				return Optional.empty();
			}
			
			if (optTallerEntity.isPresent()) {
				TallerDTO oTallerDTO = this.getTallerDTO(optTallerEntity.get());
				Optional<TallerDTO> optTallerDTO = Optional.of(oTallerDTO);

				return optTallerDTO;
			}
			return Optional.empty();

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService(e);
		}
	}

	@Override
	public TallerDTO insert(TallerDTO tallerDTO) throws ExceptionService {

		try {

			TallerEntity tallerEntity = this.getTallerRepository().save(getTallerEntity(tallerDTO));

			return this.getTallerDTO(tallerEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService(e);
		}
	}

	@Override
	public TallerDTO update(TallerDTO tallerDTO) throws ExceptionService {
		try {

			return this.getTallerDTO(this.getTallerRepository().save(getTallerEntity(tallerDTO)));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService(e);
		}
	}

	@Override
	public TallerDTO delete(TallerDTO tallerDTO) throws ExceptionService {

		try {

			Optional<TallerEntity> optTallerEntity = this.getTallerRepository().findById(tallerDTO.getId());

			if (optTallerEntity == null) {
				return null;
			}
			TallerEntity tallerEntity = optTallerEntity.get();
			tallerEntity.setEstado("0");
			return getTallerDTO(this.getTallerRepository().save(tallerEntity));

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ExceptionService(e);
		}
	}

	private TallerDTO getTallerDTO(TallerEntity tallerEntity) {
		TallerDTO tallerDTO = new TallerDTO();
		
		BeanUtils.copyProperties(tallerEntity, tallerDTO);
		
		TallerSituacionEntity tallerSituacionEntity=  tallerEntity.getTallerSituacion();	
		TallerSituacionDTO tallerSituacionDTO = new TallerSituacionDTO();
		BeanUtils.copyProperties(tallerSituacionEntity, tallerSituacionDTO);
		tallerDTO.setTallerSituacion(tallerSituacionDTO);
		
		
		return tallerDTO;
	}

	private TallerEntity getTallerEntity(TallerDTO tallerDTO) {
		TallerEntity tallerEntity = new TallerEntity();
		BeanUtils.copyProperties(tallerDTO, tallerEntity);
		
		TallerSituacionDTO tallerSituacionDTO =  tallerDTO.getTallerSituacion();	
		TallerSituacionEntity tallerSituacionEntity = new TallerSituacionEntity();
		BeanUtils.copyProperties(tallerSituacionDTO, tallerSituacionEntity);
		tallerEntity.setTallerSituacion(tallerSituacionEntity);
		
		
		return tallerEntity;
	}

}
