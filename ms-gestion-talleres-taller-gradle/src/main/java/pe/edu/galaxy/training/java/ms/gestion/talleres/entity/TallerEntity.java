package pe.edu.galaxy.training.java.ms.gestion.talleres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "TALLER")
@Entity(name = "TallerEntity")
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallerEntity extends GenericEntity {

	private static final long serialVersionUID = 7599950845886929386L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTaller")
	@SequenceGenerator(name = "seqTaller", allocationSize = 1, sequenceName = "SEQ_TALLER")
	@Column(name = "ID")
	private Long id;

	@Size(min = 5, max = 280, message = "El nombre es requerido y debe tener como minimo 5 y como maximo 280 caracteres")
	@Column(name = "NOMBRE")
	private String nombre;

	@Size(min = 20, max = 400, message = "La  descripcion es requerida y debe tener como minimo 20 y como maximo 400 caracteres")
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Positive(message = "La duracion debe ser mayor a 0")
	@Column(name = "DURACION")
	private Integer duracion;
	
	//@JsonIgnoreProperties
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ID_TALLER_SITUACION")
	private TallerSituacionEntity tallerSituacion; 
}
