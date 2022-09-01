package es.puentes.entidades;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.puentes.repositorios.ClienteListener;
import es.puentes.residencia.Cliente;
import es.puentes.residencia.Mascota;


@Entity
@EntityListeners(ClienteListener.class)
@Table(name="CLIENTES")
public class ClienteConId extends Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private Long id;
	
	public ClienteConId() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@OneToMany(targetEntity = MascotaConId.class)
	public Collection<Mascota> getMascotas() {
		return super.getMascotas();
	}
	
	// Establece la relacion en los dos sentidos
	public void addMascotaConId(MascotaConId mascota) {
		super.getMascotas().add(mascota);
		mascota.setCliente(this);
	}
}
