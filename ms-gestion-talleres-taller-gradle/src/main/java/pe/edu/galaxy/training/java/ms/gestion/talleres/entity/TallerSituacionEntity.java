package pe.edu.galaxy.training.java.ms.gestion.talleres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "TALLER_SITUACION")
@Entity(name = "TallerSituacionEntity")
@EqualsAndHashCode(callSuper = false)
@Data
public class TallerSituacionEntity extends GenericEntity {

	private static final long serialVersionUID = 7104614957659217246L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTallerSituacion")
	@SequenceGenerator(name = "seqTallerSituacion", allocationSize = 1, sequenceName = "SEQ_TALLER_SITUACION")
	@Column(name = "ID_TALLER_SITUACION")
	private Long id;

	@Size(min = 5, max = 20, message = "El nombre es requerido y debe tener como minimo 5 y como maximo 20 caracteres")
	@Column(name = "NOMBRE")
	private String nombre;
}
