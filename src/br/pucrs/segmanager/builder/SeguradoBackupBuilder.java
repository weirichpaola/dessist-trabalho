package br.pucrs.segmanager.builder;

import br.pucrs.segmanager.model.Segurado;

public class SeguradoBackupBuilder {

	private StringBuilder insert;
	
	public SeguradoBackupBuilder(Segurado s) {
		insert = new StringBuilder("INSERT INTO SEG_SEGURADO VALUES (");
		saveId(s).saveNome(s).saveEmail(s).saveTelefone(s).saveBonus(s).saveCpf(s);
		
	}

	private SeguradoBackupBuilder saveId(Segurado s) {
		insert.append(s.getId() + ", ");
		return this;
	}
	
	private SeguradoBackupBuilder saveNome(Segurado s) {
		insert.append("'" + s.getNome() + "', ");
		return this;
	}
	
	private SeguradoBackupBuilder saveEmail(Segurado s) {
		insert.append("'" + s.getEmail() + "', ");
		return this;
	}
	
	private SeguradoBackupBuilder saveTelefone(Segurado s) {
		if(s.getTelefone() != null) {
			insert.append("'" + s.getTelefone() + "', ");
		} else {
			insert.append("null" + ", ");
		}
		return this;
	}
	
	private SeguradoBackupBuilder saveBonus(Segurado s) {
		if(s.getBonus() != null) {
			insert.append(s.getBonus() + ", ");
		} else {
			insert.append("null" + ", ");
		}
		return this;
	}
	
	private SeguradoBackupBuilder saveCpf(Segurado s) {
		insert.append(s.getCpf() + "); \n ");
		return this;
	}
	
	public String retornaRegistro() {
		return insert.toString();
	}
}
