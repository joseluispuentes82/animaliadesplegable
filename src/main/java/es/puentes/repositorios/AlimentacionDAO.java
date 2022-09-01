package es.puentes.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.puentes.entidades.AlimentacionConId;

@RepositoryRestResource(path="alimentaciones", collectionResourceRel="alimentaciones", itemResourceRel="alimentacion")
public interface AlimentacionDAO extends JpaRepository<AlimentacionConId, Long> {

}
