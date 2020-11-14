package pe.edu.galaxy.training.java.ms.gestion.talleres.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO implements Serializable {

	private static final long serialVersionUID = -6297031996809907471L;

	private Long id;
	
	@Default
	private String estado = "1";

}
