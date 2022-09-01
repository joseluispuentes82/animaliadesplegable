package es.puentes.entidades;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.puentes.repositorios.AlojamientoListener;
import es.puentes.residencia.Alojamiento;
import net.bytebuddy.asm.Advice.This;

@Entity
@EntityListeners(AlojamientoListener.class)
@DiscriminatorValue("AJ")
@Component
public class AlojamientoConId extends PrestacionConId implements Alojamiento {

	private String jaula;
	
	private static float precioDia = 15;
	
	public AlojamientoConId() {
		super();
	}

	@Autowired
	public AlojamientoConId(@Qualifier("precioAlojamiento") float precioDia) {
		AlojamientoConId.precioDia = precioDia;
	}
	
	@Override
	public String getJaula() {
		return jaula;
	}
	
	public void setIdJaula(String jaula) {
		this.jaula = jaula;
	}

	@Override
	public float getPrecioDia() {
		return precioDia;
	}
}
