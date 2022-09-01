package es.puentes.repositorios;

import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.puentes.entidades.ClienteConId;
import es.puentes.entidades.Usuario;

@Component
public class UsuarioListener {

	private Logger log = LoggerFactory.getLogger(UsuarioListener.class);
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	public void init(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
//	@PrePersist
//	public void preGuardar(Usuario usuario) {
//		System.err.println("Se va a guardar un usuario: " + usuario.getNombre());
//	}
	
	@PostRemove
	public void postBorrar(Usuario usuario) {
		System.err.println("Se ha borrado al usuario: " + usuario.getNombre());
	}
	
	@PostUpdate
	public void postActualizar(Usuario usuario) {
		System.err.println("Se ha actualizado al usuario: " + usuario.getNombre());
	}
	
//	@PostLoad
//	public void postGuardar(Usuario usuario) {
//		log.warn("has guardado un usuario: " + usuario.getNombre());
//	}
}



