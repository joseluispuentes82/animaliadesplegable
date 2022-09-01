package es.puentes.repositorios;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.puentes.entidades.PrestacionConId;

@Transactional(readOnly = true)
public class PrestacionDAOImpl implements PrestacionDAOCustom {

	@Autowired
	PrestacionDAO prestacionDAO;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<PrestacionConId> getPrestacionesPagadas() {

		List<PrestacionConId> prestaciones = prestacionDAO.findAll().stream().filter(j -> j.isPagada() == true)
				.collect(Collectors.toList());

		return prestaciones;
	}

	@Override
	public List<PrestacionConId> getPrestacionesNoPagadas() {
		List<PrestacionConId> prestaciones = prestacionDAO.findAll().stream().filter(j -> j.isPagada() == false)
				.collect(Collectors.toList());

		return prestaciones;
	}
}
