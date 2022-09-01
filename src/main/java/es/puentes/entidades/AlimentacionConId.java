package es.puentes.entidades;

import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import es.puentes.repositorios.AlimentacionListener;
import es.puentes.residencia.Alimentacion;
import net.bytebuddy.asm.Advice.This;

@Entity
@EntityListeners(AlimentacionListener.class)
@DiscriminatorValue("AM")
@Component
public class AlimentacionConId extends PrestacionConId implements Alimentacion {

	@Column(name="TIPO_COMIDA")
	private String tipoComida;//será "NORMAL" o "PREMIUM
	@Column(name="CANTIDAD_COMIDA_DIA")
	private float cantidadComidaDiaria;//será multiplo de 50 gramos, en función de la talla de la mascota
	private static float precioNormalCincuenta = 2.5f;
	private static float precioPremiumCincuenta = 3.5f;
	
	public AlimentacionConId() {
		super();
	}

	@Autowired
	public AlimentacionConId(@Qualifier("precioAlimentacionNormal") float precioNormalCincuenta, @Qualifier("precioAlimentacionPremium") float precioPremiumCincuenta) {
		AlimentacionConId.precioNormalCincuenta = precioNormalCincuenta;
		AlimentacionConId.precioPremiumCincuenta = precioPremiumCincuenta;
	}	
	
	public String getTipoComida() {
		return tipoComida;
	}
	
	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}
	
	@Override
	public float getCantidadComidaDiaria() {
		return cantidadComidaDiaria;
	}
	
	public void setCantidadComidaDiaria(float cantidadComidaDiaria) {
		this.cantidadComidaDiaria = cantidadComidaDiaria;
	}
	
	public static float getPrecioNormalCincuenta() {
		return precioNormalCincuenta;
	}
	
	public static float getPrecioPremiumCincuenta() {
		return precioPremiumCincuenta;
	}

	@Override
	public float getCantidadComidaTotal() {
		return getFechaEntrada().until(getFechaSalida(), ChronoUnit.DAYS) * getCantidadComidaDiaria();
	}

	@Override
	public float getPrecioDia() {
		float resultado = 0; 
		if (getTipoComida().equals("NORMAL")) {
			resultado = getCantidadComidaDiaria()/50 * getPrecioNormalCincuenta(); //si la cantidad fueran 100 quiero que el precio sea 2 x PRECIOCINCUENTA
		}
		else if (getTipoComida().equals("PREMIUM")) {
			resultado = getCantidadComidaDiaria()/50 * getPrecioPremiumCincuenta(); //si la cantidad fueran 100 quiero que el precio sea 2 x PRECIOCINCUENTA
		}
		return resultado;	
	}
	
	
	


}
