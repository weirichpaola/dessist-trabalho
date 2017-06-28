package br.pucrs.segmanager.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
		StringBuilder inserts = dao.gerarBackup();

		//mail.addFrom("ssegmanager@gmail.com").addAssunto("Backup do sistema").addMensagem(inserts.toString()).addTo("nelsoncardosoo@gmail.com").enviarEmail();
		
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
		
		dao.efetuarRestore(list);
		
		return "admin";
	}

}
