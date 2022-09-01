package es.puentes.residenciaanimalesapi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.puentes.entidades.ClienteConId;
import es.puentes.entidades.MascotaConId;
import es.puentes.repositorios.ClienteDAO;
import es.puentes.repositorios.MascotaDAO;
import es.puentes.rest.ConfiguracionRest;

@SpringBootApplication
@ImportResource({ "classpath:config/jpa-config.xml" })
@Import({ ConfiguracionPorJava.class, ConfiguracionRest.class })
public class ResidenciaanimalesapiApplication {

	private static final Logger log = LoggerFactory.getLogger(ResidenciaanimalesapiApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ResidenciaanimalesapiApplication.class, args);
		log.debug("Está funcionando la aplicación ANIMALIA para la Residencia de Animales CAN RESORT");
		System.err.println("Está funcionando la aplicación ANIMALIA para la Residencia de Animales CAN RESORT");

		// Dejo esto por si quisiera cargarse otra vez los clientes y mascotas
//				ObjectMapper mapper = context.getBean(ObjectMapper.class);
//				ClienteDAO clienteDAO = context.getBean(ClienteDAO.class);
//				cargarClientesDesdeArchivo("src/main/resources/clientes.json", mapper, clienteDAO);
//				List<ClienteConId> clientes = clienteDAO.findAll();
//				clientes.stream().map(ClienteConId::toString).forEach(log::trace);
//				
//				ObjectMapper mapper2 = context.getBean(ObjectMapper.class);
//				MascotaDAO mascotaDAO = context.getBean(MascotaDAO.class);
//				cargarMascotasDesdeArchivo("src/main/resources/mascotas.json", mapper2, mascotaDAO);
//				List<MascotaConId> mascotas = mascotaDAO.findAll();
//				mascotas.stream().map(MascotaConId::toString).forEach(log::trace);
		
	}

	static void cargarClientesDesdeArchivo(String ruta, ObjectMapper mapper, ClienteDAO clienteDAO) {
		String linea = null;
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {
			ClienteConId cliente;
			while ((linea = buffer.readLine()) != null) {
				if (linea.startsWith("{") && linea.endsWith("}")) {
					cliente = mapper.readValue(linea, ClienteConId.class);
					clienteDAO.save(cliente);
					log.trace("Cargado {}", cliente);
				}
			}
		} catch (Exception e) {
			log.error("Error leyendo: {}", linea);
		}
	}

	static void cargarMascotasDesdeArchivo(String ruta, ObjectMapper mapper, MascotaDAO mascotaDAO) {
		String linea = null;
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {
			MascotaConId mascota;
			while ((linea = buffer.readLine()) != null) {
				if (linea.startsWith("{") && linea.endsWith("}")) {
					mascota = mapper.readValue(linea, MascotaConId.class);
					mascotaDAO.save(mascota);
					log.trace("Cargado {}", mascota);
				}
			}
		} catch (Exception e) {
			log.error("Error leyendo: {}", linea);
		}
	}

}
