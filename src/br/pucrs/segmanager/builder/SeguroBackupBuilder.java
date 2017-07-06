package br.pucrs.segmanager.builder;

import java.text.SimpleDateFormat;

import br.pucrs.segmanager.model.Seguro;

public class SeguroBackupBuilder {

	private StringBuilder insert;
	private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

	public SeguroBackupBuilder(Seguro s) {
		insert = new StringBuilder("INSERT INTO SEG_SEGURO VALUES (");
		saveId(s).saveSegurado(s).saveSeguradora(s).saveBem(s).saveVlTotal(s).saveApolice(s).saveDtInicio(s)
				.saveDtFim(s).saveEstado(s).saveDtEmissao(s).saveVlComissao(s).saveVlFranquia(s).saveStNotificado(s);
	}

	private SeguroBackupBuilder saveId(Seguro s) {
		insert.append(s.getId() + ", ");
		return this;
	}

	private SeguroBackupBuilder saveSegurado(Seguro s) {
		insert.append(s.getSegurado().getId() + ", ");
		return this;
	}

	private SeguroBackupBuilder saveSeguradora(Seguro s) {
		insert.append(s.getSeguradora().getId() + ", ");
		return this;
	}

	private SeguroBackupBuilder saveBem(Seguro s) {
		insert.append("'" + s.getBem() + "', ");
		return this;
	}

	private SeguroBackupBuilder saveVlTotal(Seguro s) {
		insert.append(s.getVlTotal() + ", ");
		return this;
	}

	private SeguroBackupBuilder saveApolice(Seguro s) {
		if (s.getTxApolice() == null || s.getTxApolice().equals("")) {
			insert.append("null, ");
		} else {
			insert.append("'" + s.getTxApolice() + "', ");
		}

		return this;
	}

	private SeguroBackupBuilder saveDtInicio(Seguro s) {
		insert.append("'" + fmt.format(s.getDtInicioVigencia()).toString() + "', ");
		return this;
	}

	private SeguroBackupBuilder saveDtFim(Seguro s) {
		insert.append("'" + fmt.format(s.getDtFimVigencia()).toString() + "', ");
		return this;
	}

	private SeguroBackupBuilder saveEstado(Seguro s) {
		if (s.getTxEstado() == null || s.getTxEstado().equals("")) {
			insert.append("null, ");
		} else {
			insert.append("'" + s.getTxEstado() + "', ");
		}
		return this;
	}

	private SeguroBackupBuilder saveDtEmissao(Seguro s) {
		insert.append("'" + fmt.format(s.getDtEmissao()).toString() + "', ");
		return this;
	}

	private SeguroBackupBuilder saveVlComissao(Seguro s) {
		insert.append(s.getVlComissao() + ", ");
		return this;
	}

	private SeguroBackupBuilder saveVlFranquia(Seguro s) {
		insert.append(s.getVlFranquia() + ", ");
		return this;
	}
	
	private SeguroBackupBuilder saveStNotificado(Seguro s) {
		insert.append("'" + s.getStNotificado() + "'); \n ");
		return this;
	}

	public String retornaRegistro() {
		return insert.toString();
	}

}
