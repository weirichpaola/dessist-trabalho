package br.pucrs.segmanager.builder;

import br.pucrs.segmanager.model.Seguradora;

public class SeguradoraBackupBuilder {

private StringBuilder insert;
	
	public SeguradoraBackupBuilder(Seguradora s) {
		insert = new StringBuilder("INSERT INTO SEG_SEGURADORA VALUES (");
		saveId(s).saveSeguradora(s);
	}

	private SeguradoraBackupBuilder saveId(Seguradora s) {
		insert.append(s.getId() + ", ");
		return this;
	}
	
	private SeguradoraBackupBuilder saveSeguradora(Seguradora s) {
		insert.append("'" + s.getNome() + "'); \n ");
		return this;
	}

	public String retornaRegistro() {
		return insert.toString();
	}
	
}
