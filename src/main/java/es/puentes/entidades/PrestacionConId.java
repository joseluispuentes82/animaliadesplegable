package es.puentes.entidades;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.puentes.residencia.PrestacionImpl;


@Entity
@Table(name="PRESTACIONES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
@DiscriminatorValue("P")
public abstract class PrestacionConId extends PrestacionImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MASCOTA")
	private MascotaConId mascota;
	
	public PrestacionConId() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public MascotaConId getMascota() {
		return mascota;
	}
	
	public void setMascota(MascotaConId mascota) {
		this.mascota = mascota;
	}

	@Override
	public abstract float getPrecioDia();
	
}
