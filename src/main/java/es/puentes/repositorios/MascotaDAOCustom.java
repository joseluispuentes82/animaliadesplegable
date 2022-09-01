package es.puentes.repositorios;

import java.util.List;

import es.puentes.residencia.Prestacion;


public interface MascotaDAOCustom {

	List<Prestacion> getPrestacionesPagadasDeMascota(Long id);
	List<Prestacion> getPrestacionesNoPagadasDeMascota(Long id);

}
