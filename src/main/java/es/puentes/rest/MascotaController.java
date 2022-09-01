package es.puentes.rest;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.puentes.entidades.MascotaConId;
import es.puentes.entidades.PrestacionConId;
import es.puentes.facturas.Descargar;
import es.puentes.repositorios.MascotaDAO;
import es.puentes.residencia.Prestacion;

@RepositoryRestController
@RequestMapping(path = "/mascotas/{id}/prestaciones")
@Configuration
public class MascotaController {

	private MascotaDAO mascotaDAO;

	public MascotaController(MascotaDAO mascotaDAO) {
		this.mascotaDAO = mascotaDAO;
	}
	
	@GetMapping("/pagadas")
	@ResponseBody
	public CollectionModel<PersistentEntityResource> getPrestacionesPagadasDeMascota(@PathVariable Long id,
			PersistentEntityResourceAssembler assembler) {

		List<Prestacion> prestaciones = mascotaDAO.getPrestacionesPagadasDeMascota(id);

		return assembler.toCollectionModel(prestaciones);
	}

	@GetMapping("/no-pagadas")
	@ResponseBody
	public CollectionModel<PersistentEntityResource> getPrestacionesNoPagadasDeMascota(@PathVariable Long id,
			PersistentEntityResourceAssembler assembler) {

		List<Prestacion> prestaciones = mascotaDAO.getPrestacionesNoPagadasDeMascota(id);

		return assembler.toCollectionModel(prestaciones);
	}
	
	@GetMapping("/no-pagadas/factura")
	@ResponseBody
	public CollectionModel<PersistentEntityResource> generarFacturas(@PathVariable Long id,
			PersistentEntityResourceAssembler assembler) {

		List<PrestacionConId> prestaciones = Descargar.generarFacturas(id);
		
		return assembler.toCollectionModel(prestaciones);
	}
		
		
	
}
