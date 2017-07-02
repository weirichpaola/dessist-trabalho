package br.pucrs.segmanager.resources;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.pucrs.segmanager.dao.SeguradoDAO;
import br.pucrs.segmanager.dao.SeguroDAO;
import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Seguro;

public class MailSenderJob implements Job {

	private SeguradoDAO seguradoDAO;
	private SeguroDAO seguroDAO;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		seguradoDAO = new SeguradoDAO();
		seguroDAO = new SeguroDAO();

		Segurado filter = new Segurado();

		List<Segurado> segurados = null;
		try {
			segurados = seguradoDAO.findAll(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Segurado s : segurados) {
			List<Seguro> seguros = seguroDAO.findSegurosBySegurado(s);
			for (Seguro seg : seguros) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 11);
				System.out.println(cal.getTime());
				if (seg.getDtFimVigencia().before(cal.getTime())) {
					MailBuilder mb = new MailBuilder();
					mb.addAssunto("Vencimento do seguro " + seg.getId()).addFrom("ssegmanager@gmail.com")
						.addMensagem("Prezado(a) " + seg.getSegurado().getNome() + ", \n"
								+ "Informamos que o seu seguro vence nos próximos 10 dias. \n"
								+ "Entre em contato com seu corretor para maiores informações. \n")
						.addTo(seg.getSegurado().getEmail()).enviarEmail();
				}
			}
		}
	}

}
