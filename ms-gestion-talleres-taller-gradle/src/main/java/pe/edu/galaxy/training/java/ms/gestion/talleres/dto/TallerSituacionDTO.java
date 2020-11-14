package pe.edu.galaxy.training.java.ms.gestion.talleres.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class TallerSituacionDTO extends GenericDTO {

	private static final long serialVersionUID = -7091778346409780318L;

	private String nombre;

}
