package es.puentes.entidades;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.puentes.repositorios.MascotaListener;
import es.puentes.residencia.Mascota;
import es.puentes.residencia.Prestacion;

@Entity
@EntityListeners(MascotaListener.class)
@Table(name="MASCOTAS")
public class MascotaConId extends Mascota {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true)
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE", nullable = false)
	private ClienteConId cliente;
	
	public MascotaConId() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ClienteConId getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteConId cliente) {
		this.cliente = cliente;
	}
	
	@Override
	@OneToMany(targetEntity = PrestacionConId.class)
	public Collection<Prestacion> getPrestaciones() {
		return super.getPrestaciones();
	}

	public void addPrestacionConId(PrestacionConId prestacion) {
		super.getPrestaciones().add(prestacion);
		prestacion.setMascota(this);
	}
}
