package es.puentes.servicios;

import javax.mail.MessagingException;

public interface MailService {

	 String sendArchivo(String to, String subject, String body, String ruta) throws MessagingException;
}
