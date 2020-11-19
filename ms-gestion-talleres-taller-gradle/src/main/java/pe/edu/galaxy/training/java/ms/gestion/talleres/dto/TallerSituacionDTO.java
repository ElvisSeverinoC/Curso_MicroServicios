package pe.edu.galaxy.training.java.ms.gestion.talleres.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallerSituacionDTO extends GenericDTO {

	private static final long serialVersionUID = -7091778346409780318L;

	private String nombre;

}
