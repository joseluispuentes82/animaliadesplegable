package es.puentes.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.puentes.entidades.Usuario;

@RepositoryRestResource(path="usuarios", collectionResourceRel="usuarios", itemResourceRel="usuario") 
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

}
