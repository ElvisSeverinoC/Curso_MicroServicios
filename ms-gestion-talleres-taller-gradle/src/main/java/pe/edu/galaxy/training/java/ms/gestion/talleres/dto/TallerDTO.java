package pe.edu.galaxy.training.java.ms.gestion.talleres.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallerDTO extends GenericDTO {

	private static final long serialVersionUID = -3738445785205143607L;

	private String nombre;

	private String descripcion;
	
	private Integer idInstructor;

	private Integer duracion;

	private TallerSituacionDTO tallerSituacion;
	
	@JsonGetter
	private String getTallerResumen() {
		String situacion="";
		if (tallerSituacion!=null) {
			situacion=tallerSituacion.getNombre();
			situacion=(situacion==null)?"":situacion;
			return this.getNombre().concat("(").concat(situacion).concat(")");
		}
		return this.getNombre();
	}
}
