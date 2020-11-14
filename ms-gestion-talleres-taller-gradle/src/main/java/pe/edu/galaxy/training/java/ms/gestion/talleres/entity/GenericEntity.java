package pe.edu.galaxy.training.java.ms.gestion.talleres.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class GenericEntity implements Serializable{

	private static final long serialVersionUID = 6596624776820438702L;
	@Column(name="ESTADO")
	@Default
	private String estado="1";
}