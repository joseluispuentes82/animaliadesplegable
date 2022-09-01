package es.puentes.repositorios;

import java.util.List;

import es.puentes.entidades.PrestacionConId;

public interface PrestacionDAOCustom {

	List<PrestacionConId> getPrestacionesPagadas();
	List<PrestacionConId> getPrestacionesNoPagadas();

}
