package pe.edu.galaxy.training.java.ms.gestion.talleres.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TallerDTO extends GenericDTO {

	private static final long serialVersionUID = -3738445785205143607L;

	private String nombre;

	private String descripcion;

	private Integer duracion;

	private TallerSituacionDTO tallerSituacion;

	private String getTallerResumen() {
		String situacion = "";
		if (tallerSituacion != null) {
			situacion = this.getNombre().concat("(").concat(tallerSituacion.getNombre()).concat(")");
		}
		return this.getNombre();
	}
}
