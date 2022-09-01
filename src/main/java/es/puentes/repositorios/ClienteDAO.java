package es.puentes.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import es.puentes.entidades.ClienteConId;

@RepositoryRestResource(path="clientes", collectionResourceRel="clientes", itemResourceRel="cliente") 
public interface ClienteDAO extends JpaRepository<ClienteConId, Long> {

	@RestResource(path="nombre")
	List<ClienteConId> findByNombreIgnoreCaseContaining(@Param("nombre") String txt);
}
