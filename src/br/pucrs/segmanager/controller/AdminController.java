package br.pucrs.segmanager.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.pucrs.segmanager.dao.AdminDAO;
import br.pucrs.segmanager.resources.MailBuilder;

@ManagedBean(name = "adminController")
@RequestScoped
public class AdminController {

	private final String BACKUP_FILE = "C:\\development\\workspace\\segmanager\\backup.txt";
	
	private AdminDAO dao;
	private MailBuilder mail;

	@PostConstruct
	public void init() {
		mail = new MailBuilder();
		dao = new AdminDAO();
	}

	public String efetuarBackup() {
		
		StringBuilder inserts = new StringBuilder();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String dataDoBackup = timestamp.toString() + "\n";
		
		inserts.append(dataDoBackup).append(dao.gerarBackup());

		//mail.addFrom("ssegmanager@gmail.com").addAssunto("Backup do sistema").addMensagem(inserts.toString()).addTo("nelsoncardosoo@gmail.com").enviarEmail();
		
		System.out.println(dataDoBackup);
		
		try {
			Files.write(Paths.get(BACKUP_FILE), inserts.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(BACKUP_FILE))) {
			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		list.forEach(System.out::println);
		
//		dao.efetuarRestore(list);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getFlash().setKeepMessages(true); 
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Backup efetuado com sucesso!" );
		RequestContext.getCurrentInstance().showMessageInDialog(message);
		
		return "admin";
	}

}
