package es.puentes.residenciaanimalesapi;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.puentes.entidades.ClienteConId;
import es.puentes.entidades.MascotaConId;
import es.puentes.rest.MixIns;

@Configuration
@PropertySource({ "classpath:config/rest.properties", "classpath:config/jackson.properties",
"classpath:config/mail.properties", "classpath:config/precios.properties" })
@ComponentScan({"es.puentes"})
public class ConfiguracionPorJava {

	@Value("${alojamiento.precio-dia}")
	private float precioDia;
	
	@Value("${alimentacion.precio-normal-cincuenta}")
	private float precioNormalCincuenta;
	
	@Value("${alimentacion.precio-premium-cincuenta}")
	private float precioPremiumCincuenta;
	
	@Bean("precioAlojamiento")
	public float getPrecioDia() {

		return precioDia;
	}
	
	@Bean("precioAlimentacionNormal")
	public float getPrecioNormalCincuenta() {

		return precioNormalCincuenta;
	}
	
	@Bean("precioAlimentacionPremium")
	public float getPrecioPremiumCincuenta() {

		return precioPremiumCincuenta;
	}
	
	@Bean
	public ObjectMapper getObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(MascotaConId.class, MixIns.Mascotas.class);
		mapper.addMixIn(ClienteConId.class, MixIns.Clientes.class);
//		mapper.addMixIn(PrestacionConId.class, MixIns.Prestaciones.class);


		return mapper;
	}

	@Bean
	public JavaMailSender getJavaMailSender(@Value("${spring.mail.password}") String password,
			@Value("${spring.mail.username}") String direccion, @Value("${spring.mail.host}") String host,
			@Value("${spring.mail.port}") int port) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(direccion);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}