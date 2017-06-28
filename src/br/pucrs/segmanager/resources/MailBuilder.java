package br.pucrs.segmanager.resources;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailBuilder {
	private String mensagem;
	private String assunto;
	private String filelocation;
	private String from;
	private String to;
	
	private Properties props = new Properties();
	
	private Session session;
	
	private Message message;
	
	private boolean temAnexo;
	
	
	public MailBuilder() {
		configuraProperties();
		message = new MimeMessage(session);
		temAnexo = false;
	}

	
	/**
	 * Adiciona um remetente para o email
	 * @param from
	 * @return
	 */
	public MailBuilder addFrom(String from) {
		this.from = from;
		return this;
	}
	
	public MailBuilder addTo(String to) {
		this.to = to;
		return this;
	}
	
	public MailBuilder addMensagem(String mensagem) {
		this.mensagem = mensagem;
		return this;
	}
	
	public MailBuilder addFile(String fileLocation) {
		this.filelocation = fileLocation;
		temAnexo = true;
		return this;
	}
	
	public MailBuilder addAssunto(String assunto) {
		this.assunto = assunto;
		return this;
	}
	/**
	 * Envia email com os parâmetros
	 */
	public void enviarEmail() {

		try {
			Multipart multipart = null;
			if(temAnexo) {
				BodyPart messageBodyPart = new MimeBodyPart();
				message.setFrom(new InternetAddress(from));
				DataSource source = new FileDataSource(filelocation);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filelocation);
				multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
			}
			
			Address[] toUser = InternetAddress.parse(to);
			message.setRecipients(Message.RecipientType.TO, toUser);
			
			message.setSubject(assunto);
			message.setText(mensagem);
			
			if(temAnexo) {
				message.setContent(multipart);
			}

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}


	private void configuraProperties() {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ssegmanager@gmail.com", "ssegmanager@@");
			}
		});
		
		session.setDebug(true);
	}
}
