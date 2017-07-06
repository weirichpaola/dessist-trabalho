package br.pucrs.segmanager.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	private String ultimoBackupEm;

	@PostConstruct
	public void init() {
		mail = new MailBuilder();
		dao = new AdminDAO();
		getUltimoBackupEm();

	}

	public String efetuarBackup() {

		StringBuilder inserts = new StringBuilder();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String dataDoBackup = timestamp.toString() + "\n";

		inserts.append(dataDoBackup).append(dao.gerarBackup());
//		MailBuilder mail = new MailBuilder();
//		mail.addFrom("ssegmanager@gmail.com").addAssunto("Backup do sistema.").addMensagem(inserts.toString())
//				.addTo("nelsoncardosoo@gmail.com").enviarEmail();

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

		// dao.efetuarRestore(list);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getFlash().setKeepMessages(true);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Backup efetuado com sucesso!");
		RequestContext.getCurrentInstance().showMessageInDialog(message);

		return "admin";
	}

	@SuppressWarnings("unchecked")
	public String efetuarRestore() {
		List<String> inserts = null;
		try (Stream<String> stream = Files.lines(Paths.get(BACKUP_FILE))) {

			inserts = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (inserts == null || inserts.size() == 0) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.getFlash().setKeepMessages(true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
					"Não há arquivo de backup ou o mesmo é inválido!");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}

		inserts.remove(0);
		inserts.remove(inserts.size() - 1);
		dao.efetuarRestore(inserts);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getFlash().setKeepMessages(true);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Restore efetuado com sucesso!");
		RequestContext.getCurrentInstance().showMessageInDialog(message);

		getUltimoBackupEm();
		return "admin";
	}

	@SuppressWarnings("resource")
	public String getUltimoBackupEm() {
		BufferedReader brTest = null;
		try {
			brTest = new BufferedReader(new FileReader(BACKUP_FILE));
			ultimoBackupEm = brTest.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "Nenhum arquivo de backup localizado!";
		}

		return ("Último backup efetuado em " + ultimoBackupEm.substring(8, 10) + "/" + ultimoBackupEm.substring(5, 7)
				+ "/" + ultimoBackupEm.substring(0, 4) + " as " + ultimoBackupEm.substring(11, 19));
	}

	public void setUltimoBackupEm(String ultimoBackupEm) {
		this.ultimoBackupEm = ultimoBackupEm;
	}

}
